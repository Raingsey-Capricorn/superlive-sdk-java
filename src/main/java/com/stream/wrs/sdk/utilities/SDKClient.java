package com.stream.wrs.sdk.utilities;

import com.stream.wrs.sdk.config.ConfigurationProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.function.Function;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 15/6/23
 * Project : superlive-sdk-java
 */
@Log
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SDKClient extends ConfigurationProperties implements SDKWebClientAction {

    private static final SDKClient INSTANCE = new SDKClient();

    /**
     * Initialize default values for the fields
     * All these field can be customized as preference
     *
     * @return a singleton instance
     */
    public static SDKClient builder() {

        INSTANCE.merchantHostId = null;
        INSTANCE.accessAuthorization = null;
        INSTANCE.endpointHosts = "/hosts";
        INSTANCE.defaultWaitingDuration = "30";
        INSTANCE.defaultFailsafeDuration = "60";
        INSTANCE.endpointParticipants = "/participants";
        INSTANCE.accessAuthorizationKey = "Authorization";
        INSTANCE.endpointCounting = INSTANCE.endpointHosts + "/count";
        INSTANCE.endpointPathVariable = INSTANCE.endpointHosts + "/{id}";
        INSTANCE.superLiveHost = "http://merch.sp.tv/api/server-sdk";
        return INSTANCE;
    }

    /**
     * Instantiate SDKClient object
     *
     * @return
     */
    public SDKClient build() {
        if (superLiveHost != null && !superLiveHost.isEmpty() &&
                endpointHosts != null && !endpointHosts.isEmpty() &&
                endpointCounting != null && !endpointCounting.isEmpty() &&
                endpointPathVariable != null && !endpointPathVariable.isEmpty() &&
                accessAuthorization != null && !accessAuthorization.isEmpty() &&
                accessAuthorizationKey != null && !accessAuthorizationKey.isEmpty() &&
                merchantHostId != null && !merchantHostId.isEmpty() &&
                defaultWaitingDuration != null && !defaultWaitingDuration.isEmpty() &&
                defaultFailsafeDuration != null && !defaultFailsafeDuration.isEmpty()
        ) {
            return this;
        } else {
            log.info("Some field is not correctly configured. Checking is required!");
            return this;
        }
    }

    /**
     * Set superlive's host
     *
     * @param superLiveHost : merchant's superlive URL
     * @return
     */
    public SDKClient superLiveHost(String superLiveHost) {
        this.superLiveHost = superLiveHost;
        return this;
    }

    /**
     * @param endpointHosts
     * @return
     */
    private SDKClient endpointHosts(String endpointHosts) {
        this.endpointHosts = endpointHosts;
        return this;
    }

    /**
     * @param endpointCounting
     * @return
     */
    public SDKClient endpointCounting(String endpointCounting) {
        this.endpointCounting = endpointCounting;
        return this;
    }

    /**
     * @param endpointPathVariable
     * @return
     */
    public SDKClient endpointPathVariable(String endpointPathVariable) {
        this.endpointPathVariable = endpointPathVariable;
        return this;
    }

    /**
     * @param endpointParticipants
     * @return
     */
    private SDKClient endpointParticipants(String endpointParticipants) {
        this.endpointParticipants = endpointParticipants;
        return this;
    }


    /**
     * @param accessAuthorization
     * @return
     */
    public SDKClient accessAuthorization(String accessAuthorization) {
        this.accessAuthorization = accessAuthorization;
        return this;
    }

    /**
     * @param accessAuthorizationKey
     * @return
     */
    public SDKClient accessAuthorizationKey(String accessAuthorizationKey) {
        this.accessAuthorizationKey = accessAuthorizationKey;
        return this;
    }

    /**
     * Required
     *
     * @param merchantHostId
     * @return
     */
    public SDKClient merchantHostId(String merchantHostId) {
        this.merchantHostId = merchantHostId;
        return this;
    }

    /**
     * Optional setting
     *
     * @param defaultWaitingDuration
     * @return
     */
    public SDKClient defaultWaitingDuration(String defaultWaitingDuration) {
        this.defaultWaitingDuration = defaultWaitingDuration;
        return this;
    }

    /**
     * Optional setting
     *
     * @param defaultFailsafeDuration
     * @return
     */
    public SDKClient defaultFailsafeDuration(String defaultFailsafeDuration) {
        this.defaultFailsafeDuration = defaultFailsafeDuration;
        return this;
    }

    @Override
    public HashMap<?, ?> doGetRequest(SDKClient sdkClient, String requestURI) {
        return (HashMap) WebClient.create(sdkClient.getSuperLiveHost()).get().uri((uriBuilder) -> {
                    uriBuilder.path(requestURI);
                    log.info("GET Requesting through url => :" + uriBuilder.build());
                    return uriBuilder.build();
                }).headers((httpHeaders) -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(sdkClient.getAccessAuthorizationKey(), sdkClient.getAccessAuthorization());
                }).retrieve()
                .bodyToMono(HashMap.class)
                .block();

    }

    @Override
    public HashMap<?, ?> doGetRequest(SDKClient sdkClient, Function<UriBuilder, URI> uriBuilderFunction) {

        return (HashMap) WebClient.create(sdkClient.getSuperLiveHost()).get().uri((uriBuilder) -> {
                    URI uri = uriBuilderFunction.apply(uriBuilder);
                    log.info("GET Requesting through url => :" + uri);
                    return uri;
                }).headers((httpHeaders) -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(sdkClient.getAccessAuthorizationKey(), sdkClient.getAccessAuthorization());
                }).retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }

    @Override
    public HashMap<?, ?> doPostRequest(SDKClient sdkClient,
                                       String uri,
                                       HashMap requestDataMap) {
        return WebClient.create(sdkClient.getSuperLiveHost())
                .post()
                .uri(uriBuilder -> {
                    uriBuilder.path(uri);
                    log.info("POST Requesting through url => :" + uriBuilder.build());
                    return uriBuilder.build();
                })
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(sdkClient.getAccessAuthorizationKey(), sdkClient.getAccessAuthorization());
                })
                .body(BodyInserters.fromFormData(SDKWebClientBuilder.buildRequestBody(requestDataMap)))
                .retrieve()
                .bodyToMono(HashMap.class)
                .block(Duration.ofSeconds(Long.parseLong(sdkClient.getDefaultWaitingDuration())));
    }

    @Override
    public HashMap<?, ?> doPutRequest(SDKClient sdkClient, URI uri) {
        return null;
    }

    @Override
    public HashMap<?, ?> doDeleteRequest(SDKClient sdkClient, URI uri) {
        return null;
    }
}
