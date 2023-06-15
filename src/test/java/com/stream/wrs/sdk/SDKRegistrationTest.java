package com.stream.wrs.sdk;

import com.stream.wrs.sdk.config.EnvValueProvider;
import com.stream.wrs.sdk.config.FileValueProvider;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.Objects;

@Log
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SDKRegistrationTest {

    @Autowired
    private EnvValueProvider valueProvider;

    @Autowired
    private FileValueProvider fileProvider;

    @Test
    public void testPostAUserRegistration() {

        final LinkedMultiValueMap sampleUser = fileProvider.readJsonRequestBody("src/test/resources/sample-user-request-body.json");
        HashMap<?, ?> result = WebClient.create(valueProvider.getSuperLiveHost())
                .post()
                .uri(uriBuilder -> {
                    URI uri = uriBuilder
                            .path(valueProvider.getEndpointParticipants())
                            .build();
                    log.info("PUT update to a host using host-id using URL => :" + uri);
                    return uri;
                })
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.add(valueProvider.getAccessAuthorizationKey(), valueProvider.getAccessAuthorization());
                })
                .body(BodyInserters.fromFormData(sampleUser))
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