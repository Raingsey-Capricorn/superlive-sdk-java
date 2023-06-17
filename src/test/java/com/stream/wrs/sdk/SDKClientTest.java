package com.stream.wrs.sdk;

import com.stream.wrs.sdk.utilities.SDKClient;
import com.stream.wrs.sdk.utilities.SDKWebClientBuilder;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@Log
@WebFluxTest
class SDKClientTest {


    /**
     *
     */
    @Test
    public void testCountingHostUsingSDKClientSimple() {
        SDKClient client = SDKClient.builder()
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build();
        HashMap<?, ?> result = client.doGetRequest(client, client.getEndpointCounting());
        Assertions.assertTrue(!Objects.requireNonNull(result).isEmpty()
                && result.containsKey("data")
                && result.get("data") != null
        );
        log.info(result.toString());
    }


    /**
     * OK
     */
    @Disabled
    @Test
    public void testCountingHostUsingSDKClientSimpleWithQueryParams() {
        SDKClient client = SDKClient.builder()
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build();
        HashMap<?, ?> result = client
                .doGetRequest(
                        client,
                        SDKWebClientBuilder.buildURI(
                                Optional.of(client.getEndpointHosts()),
                                new HashMap() {{
                                    put("page", "1");
                                    put("limit", "10");
                                    put("search", "");
                                }})
                );

        Assertions.assertTrue(!Objects.requireNonNull(result).isEmpty()
                && result.containsKey("data")
                && result.get("data") != null
        );
    }

    /**
     * OK
     */
    @Disabled
    @Test
    public void testGetAHostUsingSDKClientWithPathVariableHostId() {
        SDKClient client = SDKClient.builder()
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build();
        HashMap<?, ?> result = client
                .doGetRequest(
                        client,
                        SDKWebClientBuilder.buildURI(Optional.of(
                                        client.getEndpointPathVariable()),
                                "648693da5a508510f60625fb")
                );

        Assertions.assertTrue(!Objects.requireNonNull(result).isEmpty()
                && result.containsKey("data")
                && result.get("data") != null
        );
    }

    /**
     *
     */
    @Test
    public void testCreatingParticipant() {
        SDKClient client = SDKClient.builder()
                .superLiveHost("http://192.168.31.143:5000/api/server-sdk/")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .merchantHostId("648693da5a508510f60625fb")
                .build();
        HashMap<?, ?> result = client
                .doPostRequest(
                        client,
                        client.getEndpointParticipants(),
                        new HashMap() {{
                            put("name", Collections.singletonList("viewer001"));
                            put("description", Collections.singletonList("Description - viewer001"));
                        }}
                );

        Assertions.assertTrue(!Objects.requireNonNull(result).isEmpty()
                && result.containsKey("data")
                && result.get("data") != null
        );
    }
}