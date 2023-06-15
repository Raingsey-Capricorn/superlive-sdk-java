package com.stream.wrs.sdk;

import com.stream.wrs.sdk.config.EnvValueProvider;
import com.stream.wrs.sdk.config.FileValueProvider;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Log
@SpringBootTest
class StreamSDKApplicationTest {

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
                && !valueProvider.getEndpointPathVariable().isEmpty();
        Assertions.assertTrue(assertion);
        log.info("All configuration loaded successfully");

    }

    @Test
    public void testGetAvailableHostUsingMerchantAuthorizationKey() {

        HashMap<?, ?> result = WebClient.create(valueProvider.getEndpointHosts())
                .get()
                .uri(uriBuilder -> {
                            URI uri = uriBuilder
                                    .queryParamIfPresent("page", Optional.of(2))
                                    .queryParamIfPresent("limit", Optional.of(2))
                                    .queryParamIfPresent("search", Optional.of("Host1"))
                                    .build();
                            log.info("GET available host of merchant using URL => :" + uri);
                            return uri;
                        }
                )
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(valueProvider.getAccessAuthorizationKey(), valueProvider.getAccessAuthorization());
                })
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(HashMap.class);
                    } else {
                        return response.createException().flatMap(Mono::error);
                    }
                })
                .block(Duration.ofSeconds(Long.parseLong(valueProvider.getDefaultWaitingDuration())));
        Assertions.assertTrue(!Objects.requireNonNull(result).isEmpty()
                && result.containsKey("data")
                && result.get("data") != null
        );
        log.info(result.toString());
    }

    @Test
    public void testGetOneHostInfoUsingMerchantAuthorizationKeyWithHostId() {

        HashMap<?, ?> result = WebClient.create(valueProvider.getSuperLiveHost())
                .get()
                .uri(uriBuilder -> {
                            URI uri = uriBuilder
                                    .path(valueProvider.getEndpointPathVariable())
                                    .build(valueProvider.getMerchantHostId());
                            log.info("GET a host using host-id from merchant using URL => :" + uri);
                            return uri;
                        }
                )
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(valueProvider.getAccessAuthorizationKey(), valueProvider.getAccessAuthorization());
                })
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(HashMap.class);
                    } else {
                        return response.createException().flatMap(Mono::error);
                    }
                })
                .block(Duration.ofSeconds(Long.parseLong(valueProvider.getDefaultWaitingDuration())));

        Assertions.assertTrue(
                !Objects.requireNonNull(result).isEmpty()
                        && result.containsKey("data")
                        && result.get("data") != null
                        && new HashMap() {{
                    putAll((Map) result.get("data"));
                }}.containsKey("name")
        );

        log.info(result.toString());
    }

    @Test
    public void testGetHostCountingAvailableUsingMerchantAuthorizationKey() {

        HashMap<?, ?> result = WebClient.create(valueProvider.getEndpointHosts())
                .get()
                .uri(uriBuilder -> {
                            URI uri = uriBuilder.path(valueProvider.getEndpointCounting()).build();
                            log.info("GET total host of merchant using URL => :" + uri);
                            return uri;
                        }
                )
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(valueProvider.getAccessAuthorizationKey(), valueProvider.getAccessAuthorization());
                })
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(HashMap.class);
                    } else {
                        return response.createException().flatMap(Mono::error);
                    }
                })
                .block(Duration.ofSeconds(Long.parseLong(valueProvider.getDefaultWaitingDuration())));

        Assertions.assertTrue(!Objects.requireNonNull(result).isEmpty()
                && result.containsKey("data")
                && result.get("data") != null
        );
        log.info(result.toString());

    }

    @Disabled
    @Test
    public void testPostANewHostUsingMerchantAuthorizationKey() {

        final LinkedMultiValueMap valueMap = fileProvider.readJsonRequestBody("src/test/resources/sample-host-request-body.json");

        HashMap<?, ?> result = WebClient.create(valueProvider.getEndpointHosts())
                .post()
                .uri(uriBuilder -> {
                    log.info("CREATE a host for the merchant using URL => :" + uriBuilder.build());
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
        log.info(result.toString());
    }

    @Disabled
    @Test
    public void testPutAHostUsingHostIdMerchantAuthorizationKey() {

        final LinkedMultiValueMap valueMap = fileProvider.readJsonRequestBody("src/test/resources/sample-host-request-body.json");

        HashMap<?, ?> result = WebClient.create(valueProvider.getSuperLiveHost())
                .put()
                .uri(uriBuilder -> {
                    URI uri = uriBuilder
                            .path(valueProvider.getEndpointPathVariable())
                            .build(valueProvider.getMerchantHostId());
                    log.info("PUT update to a host using host-id using URL => :" + uri);
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
        log.info(result.toString());
    }

    @Disabled
    @Test
    public void testDeleteAHostUsingHostIdMerchantAuthorizationKey() {

        HashMap<?, ?> result = WebClient.create(valueProvider.getSuperLiveHost())
                .delete()
                .uri(uriBuilder -> {
                    URI uri = uriBuilder
                            .path(valueProvider.getEndpointPathVariable())
                            .build(valueProvider.getMerchantHostId());
                    log.info("PUT update to a host using host-id using URL => :" + uri);
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
        log.info(result.toString());
    }

}