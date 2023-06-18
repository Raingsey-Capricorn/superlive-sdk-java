package com.stream.wrs.sdk;

import com.stream.wrs.sdk.config.EnvValueProvider;
import com.stream.wrs.sdk.config.FileValueProvider;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebFluxTest
class SDKHostStreamingTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SDKHostStreamingTest.class);
    @Autowired
    private EnvValueProvider valueProvider;

    @Autowired
    private FileValueProvider fileProvider;

    @Test
    public void testContext() {
        boolean assertion = valueProvider != null
                && !valueProvider.getSuperLiveHost().isEmpty()
                && !valueProvider.getEndpointHosts().isEmpty()
                && !valueProvider.getEndpointCounting().isEmpty()
                && !valueProvider.getEndpointHostPathVariable().isEmpty();
        Assertions.assertTrue(assertion);

        LOGGER.debug("All configuration loaded successfully");

    }

    @Test
    public void testGetAvailableHostUsingMerchantAuthorizationKey() {

        HashMap<?, ?> result = WebClient.create(valueProvider.getSuperLiveHost())
                .get()
                .uri(uriBuilder -> {
                            URI uri = uriBuilder
                                    .path(valueProvider.getEndpointHosts())
                                    .queryParam("page", 0)
                                    .queryParam("limit", 10)
                                    .queryParam("search", "Host1")
                                    .build();
                            System.out.println(uri);
                            LOGGER.debug("GET available host of merchant using URL => :" + uri);
                            return uri;
                        }
                )
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(valueProvider.getAccessAuthorizationKey(), valueProvider.getAccessAuthorization());
                })
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();

        Assertions.assertTrue(!Objects.requireNonNull(result).isEmpty()
                && result.containsKey("data")
                && result.get("data") != null
        );
        LOGGER.debug(result.toString());
    }

    @Disabled
    @Test
    public void testGetOneHostInfoUsingMerchantAuthorizationKeyWithHostId() {

        HashMap<?, ?> result = (HashMap<?, ?>) WebClient.create(valueProvider.getSuperLiveHost())
                .get()
                .uri(uriBuilder -> {
                            URI uri = uriBuilder
                                    .path(valueProvider.getEndpointHostPathVariable())
                                    .build(valueProvider.getMerchantHostId());
                            LOGGER.debug("GET a host using host-id from merchant using URL => :" + uri);
                            return uri;
                        }
                )
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(valueProvider.getAccessAuthorizationKey(), valueProvider.getAccessAuthorization());
                })
                .exchange()
                .doOnSuccess(clientResponse -> clientResponse.bodyToMono(HashMap.class))
                .doOnError(throwable -> LOGGER.error(throwable.getCause().getMessage()))
                .block(Duration.ofSeconds(Long.parseLong(valueProvider.getDefaultWaitingDuration())));

        Assertions.assertTrue(
                !Objects.requireNonNull(result).isEmpty()
                        && result.containsKey("data")
                        && result.get("data") != null
                        && new HashMap() {{
                    putAll((Map) result.get("data"));
                }}.containsKey("name")
        );

        LOGGER.debug(result.toString());
    }

    @Disabled
    @Test
    public void testGetHostCountingAvailableUsingMerchantAuthorizationKey() {

        HashMap<?, ?> result = (HashMap<?, ?>) WebClient.create(valueProvider.getEndpointHosts())
                .get()
                .uri(uriBuilder -> {
                            URI uri = uriBuilder.path(valueProvider.getEndpointCounting()).build();
                            LOGGER.debug("GET total host of merchant using URL => :" + uri);
                            return uri;
                        }
                )
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(valueProvider.getAccessAuthorizationKey(), valueProvider.getAccessAuthorization());
                })
                .exchange()
                .doOnSuccess(clientResponse -> clientResponse.bodyToMono(HashMap.class))
                .doOnError(throwable -> LOGGER.error(throwable.getCause().getMessage()))
                .block(Duration.ofSeconds(Long.parseLong(valueProvider.getDefaultWaitingDuration())));

        Assertions.assertTrue(!Objects.requireNonNull(result).isEmpty()
                && result.containsKey("data")
                && result.get("data") != null
        );
        LOGGER.debug(result.toString());

    }

    @Disabled
    @Test
    public void testPostANewHostUsingMerchantAuthorizationKey() {

        final LinkedMultiValueMap valueMap = fileProvider.readJsonRequestBody("src/test/resources/sample-host-request-body.json");

        HashMap<?, ?> result = WebClient.create(valueProvider.getEndpointHosts())
                .post()
                .uri(uriBuilder -> {
                    LOGGER.debug("CREATE a host for the merchant using URL => :" + uriBuilder.build());
                    return uriBuilder.build();
                })
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(valueProvider.getAccessAuthorizationKey(), valueProvider.getAccessAuthorization());
                })
                .body(BodyInserters.fromFormData(valueMap))
                .retrieve()
                .bodyToMono(HashMap.class)
                .block(Duration.ofSeconds(Long.parseLong(valueProvider.getDefaultWaitingDuration())));

        Assertions.assertTrue(!Objects.requireNonNull(result).isEmpty()
                && result.containsKey("data")
                && result.get("data") != null
        );
        LOGGER.debug(result.toString());
    }

    @Disabled
    @Test
    public void testPutAHostUsingHostIdMerchantAuthorizationKey() {

        final LinkedMultiValueMap valueMap = fileProvider.readJsonRequestBody("src/test/resources/sample-host-request-body.json");

        HashMap<?, ?> result = WebClient.create(valueProvider.getSuperLiveHost())
                .put()
                .uri(uriBuilder -> {
                    URI uri = uriBuilder
                            .path(valueProvider.getEndpointHostPathVariable())
                            .build(valueProvider.getMerchantHostId());
                    LOGGER.debug("PUT update to a host using host-id using URL => :" + uri);
                    return uri;
                })
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(valueProvider.getAccessAuthorizationKey(), valueProvider.getAccessAuthorization());
                })
                .body(BodyInserters.fromFormData(valueMap))
                .retrieve()
                .bodyToMono(HashMap.class)
                .block(Duration.ofSeconds(Long.parseLong(valueProvider.getDefaultWaitingDuration())));

        Assertions.assertTrue(!Objects.requireNonNull(result).isEmpty()
                && result.containsKey("data")
                && result.get("data") != null
        );
        LOGGER.debug(result.toString());
    }

    @Disabled
    @Test
    public void testDeleteAHostUsingHostIdMerchantAuthorizationKey() {

        HashMap<?, ?> result = WebClient.create(valueProvider.getSuperLiveHost())
                .delete()
                .uri(uriBuilder -> {
                    URI uri = uriBuilder
                            .path(valueProvider.getEndpointHostPathVariable())
                            .build(valueProvider.getMerchantHostId());
                    LOGGER.debug("PUT update to a host using host-id using URL => :" + uri);
                    return uri;
                })
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(valueProvider.getAccessAuthorizationKey(), valueProvider.getAccessAuthorization());
                })
                .retrieve()
                .bodyToMono(HashMap.class)
                .block(Duration.ofSeconds(Long.parseLong(valueProvider.getDefaultWaitingDuration())));

        Assertions.assertTrue(!Objects.requireNonNull(result).isEmpty()
                && result.containsKey("data")
                && result.get("data") != null
        );
        LOGGER.debug(result.toString());
    }

}