package com.stream.wrs.sdk.utilities;

import com.stream.wrs.sdk.config.ConfigurableProperties;
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
 * @see SDKClient
 * All webclient request action will be delegated to the subclass, which provide
 * fully applicable implementation without changing the original concept of SDKClient
 * @see SDKWebClientAction
 */
@Log
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SDKClient extends ConfigurableProperties implements SDKWebClientAction {
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
        INSTANCE.endpointParticipantsPathVariable = INSTANCE.endpointParticipants + "/{id}";
        INSTANCE.superLiveHost = "http://merch.sp.tv/api/server-sdk";

        Host.index = "/hosts";
        Host.count = "/hosts/count";
        Host.pathVariableId = "/hosts/{id}}";
        Participant.index = "/participants";
        Participant.pathVariableId = "/participants/{id}";
        Participant.giftPoints = "/participants/{id}/credit/gift-point";
        Gift.index = "/gifts";
        Gift.packs = "/gifts/packs";
        Gift.pathVariableId = "/gifts/{id}";
        Gift.pathVariablePacksId = "/gifts/packs/{id}";
        Sticker.index = "/stickers";
        Sticker.packs = "/stickers/packs";
        Sticker.pathVariableId = "/stickers/{id}";
        Sticker.pathVariablePacksId = "/stickers/packs/{id}";
        Upload.index = "/upload/file";
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

    public SDKClient buildApiHost() {
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

    public SDKClient buildApiParticipant() {
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

    public SDKClient buildApiSticker() {
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

    public SDKClient buildApiGift() {
        INSTANCE.setGift(true);
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

    public SDKClient buildApiUpload() {
        INSTANCE.setUpload(true);
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
     * @param superLiveApiUri : merchant's superlive api URI
     * @return
     */
    public SDKClient superLiveApiUri(final String superLiveApiUri) {
        this.superLiveApiUri = superLiveApiUri;
        return INSTANCE;
    }

    public SDKClient superLiveHost(final String superLiveHost) {
        this.superLiveHost = superLiveHost;
        return INSTANCE;
    }

    /**
     * @param endpointHosts
     * @return
     */
    private SDKClient endpointHosts(final String endpointHosts) {
        this.endpointHosts = endpointHosts;
        return INSTANCE;
    }

    /**
     * @param endpointCounting
     * @return
     */
    public SDKClient endpointCounting(final String endpointCounting) {
        this.endpointCounting = endpointCounting;
        return INSTANCE;
    }

    /**
     * @param endpointPathVariable
     * @return
     */
    public SDKClient endpointPathVariable(final String endpointPathVariable) {
        this.endpointHostPathVariable = endpointPathVariable;
        return INSTANCE;
    }

    /**
     * @param endpointParticipants
     * @return
     */
    private SDKClient endpointParticipants(final String endpointParticipants) {
        this.endpointParticipants = endpointParticipants;
        return INSTANCE;
    }

    /**
     * @param accessAuthorization
     * @return
     */
    public SDKClient accessAuthorization(final String accessAuthorization) {
        this.accessAuthorization = accessAuthorization;
        return INSTANCE;
    }

    /**
     * @param accessAuthorizationKey
     * @return
     */
    public SDKClient accessAuthorizationKey(final String accessAuthorizationKey) {
        this.accessAuthorizationKey = accessAuthorizationKey;
        return INSTANCE;
    }

    /**
     * Required
     *
     * @param merchantHostId
     * @return
     */
    public SDKClient merchantHostId(final String merchantHostId) {
        this.merchantHostId = merchantHostId;
        return INSTANCE;
    }

    /**
     * Optional setting
     *
     * @param defaultWaitingDuration
     * @return
     */
    public SDKClient defaultWaitingDuration(final String defaultWaitingDuration) {
        this.defaultWaitingDuration = defaultWaitingDuration;
        return INSTANCE;
    }

    /**
     * Optional setting
     *
     * @param defaultFailsafeDuration
     * @return
     */
    public SDKClient defaultFailsafeDuration(final String defaultFailsafeDuration) {
        INSTANCE.defaultFailsafeDuration = defaultFailsafeDuration;
        return INSTANCE;
    }

    @Override
    public HashMap<?, ?> doGetRequest(final SDKClient sdkClient,
                                      final String requestURI) {
        return WebClient.builder()
                .baseUrl(sdkClient.getSuperLiveHost())
                .filters(SDKWebClientBuilder.exchangeRequestFilters())
                .build()
                .get()
                .uri((uriBuilder) -> uriBuilder.path(requestURI).build()).headers((httpHeaders) -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(sdkClient.getAccessAuthorizationKey(), sdkClient.getAccessAuthorization());
                })
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(HashMap.class))
                .doOnError(throwable -> log.log(Level.FINER, throwable.getCause().getMessage()))
                .doOnSuccess(map -> {
                    log.log(Level.INFO, String.format("Response status : %s", map.get("statusCode")));
                    log.log(Level.INFO, String.format("Response data : %s", map.get("data")));
                })
                .block(Duration.ofSeconds(Long.parseLong(getDefaultFailsafeDuration())));
    }

    @Override
    public HashMap<?, ?> doGetRequest(final SDKClient sdkClient,
                                      final Function<UriBuilder, URI> uriBuilderFunction) {

        return WebClient.builder()
                .baseUrl(sdkClient.getSuperLiveHost())
                .filters(SDKWebClientBuilder.exchangeRequestFilters())
                .build()
                .get()
                .uri(uriBuilderFunction)
                .headers((httpHeaders) -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(sdkClient.getAccessAuthorizationKey(), sdkClient.getAccessAuthorization());
                })
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(HashMap.class))
                .doOnError(throwable -> log.log(Level.FINER, throwable.getCause().getMessage()))
                .doOnSuccess(map -> {
                    log.log(Level.INFO, String.format("Response status : %s", map.get("statusCode")));
                    log.log(Level.INFO, String.format("Response data : %s", map.get("data")));
                })
                .block(Duration.ofSeconds(Long.parseLong(getDefaultFailsafeDuration())));
    }

    @Override
    public HashMap<?, ?> doGetRequest(final String requestURI) {

        return SDKWebClientBuilder.buildAPIPathForHttpGet(requestURI, null, this.getAPIsPaths(), superLiveHost)
                .headers((httpHeaders) -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(this.getAccessAuthorizationKey(), this.getAccessAuthorization());
                })
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(HashMap.class))
                .doOnError(throwable -> log.log(Level.FINER, throwable.getCause().getMessage()))
                .doOnSuccess(map -> {
                    log.log(Level.INFO, String.format("Response status : %s", map.get("statusCode")));
                    log.log(Level.INFO, String.format("Response data : %s", map.get("data")));
                })
                .block(Duration.ofSeconds(Long.parseLong(getDefaultFailsafeDuration())));
    }

    @Override
    public HashMap<?, ?> doGetRequest(final String requestURI,
                                      final Function<UriBuilder, URI> uriBuilderFunction) {
        return SDKWebClientBuilder.buildAPIPathForHttpGet(requestURI, uriBuilderFunction, this.getAPIsPaths(), superLiveHost).headers((httpHeaders) -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(this.getAccessAuthorizationKey(), this.getAccessAuthorization());
                })
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(HashMap.class))
                .doOnError(throwable -> log.log(Level.FINER, throwable.getCause().getMessage()))
                .doOnSuccess(map -> {
                    log.log(Level.INFO, String.format("Response status : %s", map.get("statusCode")));
                    log.log(Level.INFO, String.format("Response data : %s", map.get("data")));
                })
                .block(Duration.ofSeconds(Long.parseLong(getDefaultFailsafeDuration())));
    }

    @Override
    @SneakyThrows
    public HashMap<?, ?> doPostRequest(final SDKClient sdkClient,
                                       final String uri,
                                       final HashMap requestDataMap) {

        return WebClient.builder()
                .baseUrl(sdkClient.getSuperLiveHost())
                .filters(SDKWebClientBuilder.exchangeRequestFilters())
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder.path(uri).build())
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(sdkClient.getAccessAuthorizationKey(), sdkClient.getAccessAuthorization());
                })
                .body(BodyInserters.fromObject(requestDataMap))
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(HashMap.class))
                .doOnError(throwable -> log.log(Level.FINER, throwable.getCause().getMessage()))
                .doOnSuccess(map -> {
                    log.log(Level.INFO, String.format("Response status : %s", map.get("statusCode")));
                    log.log(Level.INFO, String.format("Response data : %s", map.get("data")));
                })
                .block(Duration.ofSeconds(Long.parseLong(getDefaultFailsafeDuration())));
    }

    @Override
    public HashMap<?, ?> doPostRequest(final String requestURI,
                                       final HashMap requestDataMap) {

        return SDKWebClientBuilder.buildAPIPathForHttpPost(requestURI, this.getAPIsPaths(), superLiveHost)
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(this.getAccessAuthorizationKey(), this.getAccessAuthorization());
                })
                .body(BodyInserters.fromObject(requestDataMap))
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(HashMap.class))
                .doOnError(throwable -> log.log(Level.FINER, throwable.getCause().getMessage()))
                .doOnSuccess(map -> {
                    log.log(Level.INFO, String.format("Response status : %s", map.get("statusCode")));
                    log.log(Level.INFO, String.format("Response data : %s", map.get("data")));
                })
                .block(Duration.ofSeconds(Long.parseLong(getDefaultFailsafeDuration())));
    }

    @Override
    public HashMap<?, ?> doPutRequest(final SDKClient sdkClient,
                                      final String requestURI,
                                      final String id,
                                      final HashMap requestDataMap) {

        return WebClient.builder()
                .baseUrl(sdkClient.getSuperLiveHost())
                .filters(SDKWebClientBuilder.exchangeRequestFilters())
                .build()
                .put()
                .uri(uriBuilder -> uriBuilder
                        .path(requestURI)
                        .build(id)
                )
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(sdkClient.getAccessAuthorizationKey(), sdkClient.getAccessAuthorization());
                })
                .body(BodyInserters.fromObject(requestDataMap))
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(HashMap.class))
                .doOnError(throwable -> log.log(Level.FINER, throwable.getCause().getMessage()))
                .doOnSuccess(map -> {
                    log.log(Level.INFO, String.format("Response status : %s", map.get("statusCode")));
                    log.log(Level.INFO, String.format("Response data : %s", map.get("data")));
                })
                .block(Duration.ofSeconds(Long.parseLong(getDefaultFailsafeDuration())));
    }

    @Override
    public HashMap<?, ?> doPutRequest(final String requestURI,
                                      final String id,
                                      final HashMap requestDataMap) {

        return SDKWebClientBuilder.buildAPIPathForHttpPut(requestURI, id, getAPIsPaths(), superLiveHost)
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(this.getAccessAuthorizationKey(), this.getAccessAuthorization());
                })
                .body(BodyInserters.fromObject(requestDataMap))
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(HashMap.class))
                .doOnError(throwable -> log.log(Level.FINER, throwable.getCause().getMessage()))
                .doOnSuccess(map -> {
                    log.log(Level.INFO, String.format("Response status : %s", map.get("statusCode")));
                    log.log(Level.INFO, String.format("Response data : %s", map.get("data")));
                })
                .block(Duration.ofSeconds(Long.parseLong(getDefaultFailsafeDuration())));
    }

    @Override
    public HashMap<?, ?> doDeleteRequest(final SDKClient sdkClient,
                                         final String id,
                                         final String requestURI) {

        return WebClient.builder()
                .baseUrl(sdkClient.getSuperLiveHost())
                .filters(SDKWebClientBuilder.exchangeRequestFilters())
                .build()
                .delete()
                .uri(uriBuilder -> uriBuilder
                        .path(requestURI)
                        .build(id))
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(sdkClient.getAccessAuthorizationKey(), sdkClient.getAccessAuthorization());
                })
                .exchange()
                .doOnError(throwable -> log.log(Level.FINER, throwable.getCause().getMessage()))
                .flatMap(clientResponse -> clientResponse.bodyToMono(HashMap.class))
                .doOnSuccess(map -> {
                    log.log(Level.INFO, String.format("Response status : %s", map.get("statusCode")));
                    log.log(Level.INFO, String.format("Response data : %s", map.get("data")));
                })
                .block(Duration.ofSeconds(Long.parseLong(getDefaultFailsafeDuration())));
    }

    @Override
    public HashMap<?, ?> doDeleteRequest(String requestURI, String id) {

        return SDKWebClientBuilder.buildAPIPathForHttpDelete(requestURI, id, getAPIsPaths(), superLiveHost)
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(this.getAccessAuthorizationKey(), this.getAccessAuthorization());
                })
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(HashMap.class))
                .doOnError(throwable -> log.log(Level.FINER, throwable.getCause().getMessage()))
                .doOnSuccess(map -> {
                    log.log(Level.INFO, String.format("Response status : %s", map.get("statusCode")));
                    log.log(Level.INFO, String.format("Response data : %s", map.get("data")));
                })
                .block(Duration.ofSeconds(Long.parseLong(getDefaultFailsafeDuration())));
    }
}
