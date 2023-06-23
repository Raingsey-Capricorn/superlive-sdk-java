package com.stream.wrs.sdk;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.stream.wrs.sdk.utilities.SDKClient;
import com.stream.wrs.sdk.utilities.SDKWebClientBuilder;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@Log
@WebFluxTest
class SDKClientTest {

    @SneakyThrows
    @Test
    public void testCreatingAHostUsingSDKClientWithPathVariableHostId() {
        SDKClient client = SDKClient.builder()
                .superLiveHost("http://192.168.31.143:5000/api/server-sdk/")
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build();
        HashMap<?, ?> result = client
                .doGetRequest(
                        client,
                        SDKWebClientBuilder.buildHttpGetURI(Optional.of(
                                        client.getEndpointHostPathVariable()),
                                "648693da5a508510f60625fb")
                );

        Assertions.assertTrue(!Objects.requireNonNull(result).isEmpty()
                && result.containsKey("data")
                && result.get("data") != null
        );
        log.info(new JsonMapper().writeValueAsString(result));
    }

    @SneakyThrows
    @Test
    public void testCreatingParticipant() {

        /*
        Old version - still working
        SDKClient client = SDKClient.builder()
                .superLiveHost("http://192.168.31.143:5000/api/server-sdk/")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build();

        HashMap<?, ?> result = client
                .doPostRequest(
                        client,
                        client.getEndpointParticipants(),
                        new HashMap() {{
                            put("name", Collections.singletonList("viewer0033333"));
                            put("description", Collections.singletonList("Description - viewer0033333"));
                        }}
                );
        */

        HashMap<?, ?> result = SDKClient.builder()
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build()
                .doPostRequest("http://192.168.31.143:5000/api/server-sdk/participants", new HashMap() {{
                    put("name", Collections.singletonList("viewer0088888333"));
                    put("description", Collections.singletonList("Description - viewer0088888333"));
                }});

        Assertions.assertTrue(!Objects.requireNonNull(result).isEmpty()
                && result.containsKey("data")
                && result.get("data") != null
        );
        log.info(new JsonMapper().writeValueAsString(result));
    }

    @SneakyThrows
    @Test
    public void testPutSomeUpdateToTheParticipants() {

        HashMap<?, ?> result = SDKClient.builder()
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build()
                    .doPutRequest("http://192.168.31.143:5000/api/server-sdk/participants/{id}",
                        "648ea7279489fd81bef64d59",
                        new HashMap() {{
                            put("name", Collections.singletonList("viewe?????????????"));
                            put("description", Collections.singletonList("Description - viewe?????????????"));
                        }});

        String jsonResult = new JsonMapper().writeValueAsString(result);
        Assertions.assertTrue(!Objects.requireNonNull(result).isEmpty()
                && result.containsKey("data")
                && result.get("data") != null
                && result.containsKey("statusCode")
                && result.get("statusCode")
                .equals(HttpStatus.OK)
        );
        log.info(jsonResult);
    }

    @SneakyThrows
    @Test
    public void testDeleteAHostUsingHostIdMerchantAuthorizationKey() {

        HashMap<?, ?> result = SDKClient.builder()
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build()
                .doPutRequest("http://192.168.31.143:5000/api/server-sdk/participants/{id}",
                        "648ea7279489fd81bef64d59",
                        new HashMap() {{
                            put("name", Collections.singletonList("viewe?????????????"));
                            put("description", Collections.singletonList("Description - viewe?????????????"));
                        }});

        String jsonResult = new JsonMapper().writeValueAsString(result);
        Assertions.assertTrue(!Objects.requireNonNull(result).isEmpty()
                && result.containsKey("data")
                && result.get("data") != null
                && result.containsKey("statusCode")
                && result.get("statusCode")
                .equals(HttpStatus.OK)
        );
        log.info(jsonResult);
    }

    /**
     * OK now,
     */
    @SneakyThrows
    @Test
    public void testGetHostCount() {

    }
}