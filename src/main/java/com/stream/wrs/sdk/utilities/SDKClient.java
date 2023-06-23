package com.stream.wrs.sdk.utilities;

import com.stream.wrs.sdk.config.ConfigurationProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.function.Function;
import java.util.logging.Level;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 15/6/23
 * Project : superlive-sdk-java
 * INSTANCE class is mainly used to apply getter and setter for the configuration,
 * it's supposed to be the central of SDK calling point.
 *
 * @see ConfigurationProperties
 * All webclient request action will be delegated to the subclass, which provide
 * fully applicable implementation without changing the original concept of SDKClient
 * @see SDKWebClientAction
 */
@Log
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SDKClient extends ConfigurationProperties implements SDKWebClientAction {

    private static SDKClient INSTANCE;

    /**
     * Initialize default values for the fields
     * All these field can be customized as preference
     *
     * @return a singleton instance
     */
    public static SDKClient builder() {
        INSTANCE = new SDKClient();
        INSTANCE.merchantHostId = null;
        INSTANCE.accessAuthorization = null;
        INSTANCE.endpointHosts = "/hosts";
        INSTANCE.defaultWaitingDuration = "30";
        INSTANCE.defaultFailsafeDuration = "60";
        INSTANCE.endpointParticipants = "/participants";
        INSTANCE.accessAuthorizationKey = "Authorization";
        INSTANCE.endpointCounting = INSTANCE.endpointHosts + "/count";
        INSTANCE.endpointHostPathVariable = INSTANCE.endpointHosts + "/{id}";
        INSTANCE.superLiveHost = "http://merch.sp.tv/api/server-sdk";
        return INSTANCE;
    }

    /**
     * Instantiate SDKClient object
     *
     * @return
     */
    public SDKClient build() {
        if (INSTANCE.superLiveHost != null && !INSTANCE.superLiveHost.isEmpty() &&
                INSTANCE.endpointHosts != null && !INSTANCE.endpointHosts.isEmpty() &&
                INSTANCE.endpointCounting != null && !INSTANCE.endpointCounting.isEmpty() &&
                INSTANCE.endpointHostPathVariable != null && !INSTANCE.endpointHostPathVariable.isEmpty() &&
                INSTANCE.accessAuthorization != null && !INSTANCE.accessAuthorization.isEmpty() &&
                INSTANCE.accessAuthorizationKey != null && !INSTANCE.accessAuthorizationKey.isEmpty() &&
                /*INSTANCE.merchantHostId != null && !INSTANCE.merchantHostId.isEmpty() &&*/
                INSTANCE.defaultWaitingDuration != null && !INSTANCE.defaultWaitingDuration.isEmpty() &&
                INSTANCE.defaultFailsafeDuration != null && !INSTANCE.defaultFailsafeDuration.isEmpty()
        ) {
            return INSTANCE;
        } else {
            log.info("Some field is not correctly configured. Checking is required!");
            return INSTANCE;
        }
    }

    public SDKClient buildMerchantHostAPI() {
        INSTANCE.setHost(true);
        if (INSTANCE.superLiveHost != null && !INSTANCE.superLiveHost.isEmpty() &&
                INSTANCE.endpointHosts != null && !INSTANCE.endpointHosts.isEmpty() &&
                INSTANCE.accessAuthorization != null && !INSTANCE.accessAuthorization.isEmpty() &&
                INSTANCE.accessAuthorizationKey != null && !INSTANCE.accessAuthorizationKey.isEmpty() &&
                INSTANCE.merchantHostId != null && !INSTANCE.merchantHostId.isEmpty()
        ) {
            return INSTANCE;
        } else {
            log.info("Some field is not correctly configured for host-api. Checking is required!");
            return INSTANCE;
        }

    }

    public SDKClient buildParticipantAPI() {
        INSTANCE.setParticipants(true);
        if (INSTANCE.superLiveHost != null && !INSTANCE.superLiveHost.isEmpty() &&
                INSTANCE.accessAuthorization != null && !INSTANCE.accessAuthorization.isEmpty() &&
                INSTANCE.accessAuthorizationKey != null && !INSTANCE.accessAuthorizationKey.isEmpty()
        ) {
            return INSTANCE;
        } else {
            log.info("Some field is not correctly configured for host-api. Checking is required!");
            return INSTANCE;
        }
    }

    public SDKClient buildStickerAPI() {
        INSTANCE.setSticker(true);
        if (INSTANCE.superLiveHost != null && !INSTANCE.superLiveHost.isEmpty() &&
                INSTANCE.accessAuthorization != null && !INSTANCE.accessAuthorization.isEmpty() &&
                INSTANCE.accessAuthorizationKey != null && !INSTANCE.accessAuthorizationKey.isEmpty()
        ) {
            return INSTANCE;
        } else {
            log.info("Some field is not correctly configured for host-api. Checking is required!");
            return INSTANCE;
        }
    }

    public SDKClient buildGiftAPI() {
        INSTANCE.setSticker(true);
        if (INSTANCE.superLiveHost != null && !INSTANCE.superLiveHost.isEmpty() &&
                INSTANCE.accessAuthorization != null && !INSTANCE.accessAuthorization.isEmpty() &&
                INSTANCE.accessAuthorizationKey != null && !INSTANCE.accessAuthorizationKey.isEmpty()
        ) {
            return INSTANCE;
        } else {
            log.info("Some field is not correctly configured for host-api. Checking is required!");
            return INSTANCE;
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
        return INSTANCE;
    }

    /**
     * @param endpointHosts
     * @return
     */
    private SDKClient endpointHosts(String endpointHosts) {
        this.endpointHosts = endpointHosts;
        return INSTANCE;
    }

    /**
     * @param endpointCounting
     * @return
     */
    public SDKClient endpointCounting(String endpointCounting) {
        this.endpointCounting = endpointCounting;
        return INSTANCE;
    }

    /**
     * @param endpointPathVariable
     * @return
     */
    public SDKClient endpointPathVariable(String endpointPathVariable) {
        this.endpointHostPathVariable = endpointPathVariable;
        return INSTANCE;
    }

    /**
     * @param endpointParticipants
     * @return
     */
    private SDKClient endpointParticipants(String endpointParticipants) {
        this.endpointParticipants = endpointParticipants;
        return INSTANCE;
    }


    /**
     * @param accessAuthorization
     * @return
     */
    public SDKClient accessAuthorization(String accessAuthorization) {
        this.accessAuthorization = accessAuthorization;
        return INSTANCE;
    }

    /**
     * @param accessAuthorizationKey
     * @return
     */
    public SDKClient accessAuthorizationKey(String accessAuthorizationKey) {
        this.accessAuthorizationKey = accessAuthorizationKey;
        return INSTANCE;
    }

    /**
     * Required
     *
     * @param merchantHostId
     * @return
     */
    public SDKClient merchantHostId(String merchantHostId) {
        this.merchantHostId = merchantHostId;
        return INSTANCE;
    }

    /**
     * Optional setting
     *
     * @param defaultWaitingDuration
     * @return
     */
    public SDKClient defaultWaitingDuration(String defaultWaitingDuration) {
        this.defaultWaitingDuration = defaultWaitingDuration;
        return INSTANCE;
    }

    /**
     * Optional setting
     *
     * @param defaultFailsafeDuration
     * @return
     */
    public SDKClient defaultFailsafeDuration(String defaultFailsafeDuration) {
        INSTANCE.defaultFailsafeDuration = defaultFailsafeDuration;
        return INSTANCE;
    }

    @Override
    public HashMap<?, ?> doGetRequest(final SDKClient sdkClient, final String requestURI) {
        return WebClient.create(sdkClient.getSuperLiveHost()).get().uri((uriBuilder) -> {
                    uriBuilder.path(requestURI);
                    log.info("GET Requesting through url => :" + uriBuilder.build());
                    return uriBuilder.build();
                }).headers((httpHeaders) -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(sdkClient.getAccessAuthorizationKey(), sdkClient.getAccessAuthorization());
                })
                .exchange()
                .doOnError(throwable -> log.log(Level.FINER, throwable.getCause().getMessage()))
                .doOnSuccess(clientResponse -> log.log(Level.INFO, String.valueOf(clientResponse.statusCode().is2xxSuccessful())))
                .flatMap(clientResponse -> clientResponse.bodyToMono(HashMap.class))
                .block(Duration.ofSeconds(Long.parseLong(getDefaultFailsafeDuration())));
    }

    @Override
    public HashMap<?, ?> doGetRequest(SDKClient sdkClient, Function<UriBuilder, URI> uriBuilderFunction) {

        return WebClient.create(sdkClient.getSuperLiveHost()).get().uri((uriBuilder) -> {
                    URI uri = uriBuilderFunction.apply(uriBuilder);
                    log.info("GET Requesting through url => :" + uri);
                    return uri;
                }).headers((httpHeaders) -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(sdkClient.getAccessAuthorizationKey(), sdkClient.getAccessAuthorization());
                })
                .exchange()
                .doOnError(throwable -> log.log(Level.FINER, throwable.getCause().getMessage()))
                .doOnSuccess(clientResponse -> log.log(Level.INFO, String.valueOf(clientResponse.statusCode().is2xxSuccessful())))
                .flatMap(clientResponse -> clientResponse.bodyToMono(HashMap.class))
                .block(Duration.ofSeconds(Long.parseLong(getDefaultFailsafeDuration())));
    }

    @Override
    public HashMap<?, ?> doGetRequest(String requestURI) {

        return SDKWebClientBuilder.buildAPIPathForHttpGet(requestURI, null, this.getAPIsPaths(), superLiveHost)
                .headers((httpHeaders) -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(this.getAccessAuthorizationKey(), this.getAccessAuthorization());
                })
                .exchange()
                .doOnError(throwable -> log.log(Level.FINER, throwable.getCause().getMessage()))
                .doOnSuccess(clientResponse -> log.log(Level.INFO, String.valueOf(clientResponse.statusCode().is2xxSuccessful())))
                .flatMap(clientResponse -> clientResponse.bodyToMono(HashMap.class))
                .block(Duration.ofSeconds(Long.parseLong(getDefaultFailsafeDuration())));
    }

    @Override
    public HashMap<?, ?> doGetRequest(String requestURI, Function<UriBuilder, URI> uriBuilderFunction) {
        return SDKWebClientBuilder.buildAPIPathForHttpGet(requestURI, uriBuilderFunction, this.getAPIsPaths(), superLiveHost).headers((httpHeaders) -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(this.getAccessAuthorizationKey(), this.getAccessAuthorization());
                })
                .exchange()
                .doOnError(throwable -> log.log(Level.FINER, throwable.getCause().getMessage()))
                .doOnSuccess(clientResponse -> log.log(Level.INFO, String.valueOf(clientResponse.statusCode().is2xxSuccessful())))
                .flatMap(clientResponse -> clientResponse.bodyToMono(HashMap.class))
                .block(Duration.ofSeconds(Long.parseLong(getDefaultFailsafeDuration())));
    }

    @Override
    @SneakyThrows
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
                .body(BodyInserters.fromObject(requestDataMap))
                .exchange()
                .doOnError(throwable -> log.log(Level.FINER, throwable.getCause().getMessage()))
                .doOnSuccess(clientResponse -> log.log(Level.INFO, String.valueOf(clientResponse.statusCode().is2xxSuccessful())))
                .flatMap(clientResponse -> clientResponse.bodyToMono(HashMap.class))
                .block();
    }

    @Override
    public HashMap<?, ?> doPostRequest(String requestURI,
                                       HashMap requestDataMap) {

        return SDKWebClientBuilder.buildAPIPathForHttpPost(requestURI, this.getAPIsPaths(), superLiveHost)
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(this.getAccessAuthorizationKey(), this.getAccessAuthorization());
                })
                .body(BodyInserters.fromObject(requestDataMap))
                .exchange()
                .doOnError(throwable -> log.log(Level.FINER, throwable.getCause().getMessage()))
                .doOnSuccess(clientResponse -> log.log(Level.INFO, String.valueOf(clientResponse.statusCode().is2xxSuccessful())))
                .flatMap(clientResponse -> clientResponse.bodyToMono(HashMap.class))
                .block(Duration.ofSeconds(Long.parseLong(getDefaultFailsafeDuration())));
    }

    @Override
    public HashMap<?, ?> doPutRequest(SDKClient sdkClient, URI uri) {

        log.warning("execution is being constructed.... ");
        return null;
    }

    @Override
    public HashMap<?, ?> doPutRequest(String requestURI, String id, HashMap requestDataMap) {
        HashMap<Integer, String> fqdn = SDKWebClientBuilder.buildFQDN(requestURI);
        return WebClient.create(fqdn.get(SDKWebClientBuilder.FQDN))
                .put()
                .uri(uriBuilder -> {
                    URI uri = uriBuilder
                            .path(fqdn.get(SDKWebClientBuilder.PATH))
                            .build(id);
                    log.info("PUT Requesting through url => :" + uri);
                    return uri;
                })
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(this.getAccessAuthorizationKey(), this.getAccessAuthorization());
                })
                .body(BodyInserters.fromObject(SDKWebClientBuilder.buildRequestBody(requestDataMap)))
                .exchange()
                .doOnError(throwable -> log.log(Level.FINER, throwable.getCause().getMessage()))
                .doOnSuccess(clientResponse -> log.log(Level.INFO, String.valueOf(clientResponse.statusCode().is2xxSuccessful())))
                .flatMap(clientResponse -> clientResponse.bodyToMono(HashMap.class))
                .block(Duration.ofSeconds(Long.parseLong(getDefaultFailsafeDuration())));
    }

    @Override
    public HashMap<?, ?> doDeleteRequest(SDKClient sdkClient, URI uri) {
        log.warning("execution is being constructed.... ");
        return null;
    }


}
