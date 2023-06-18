package com.stream.wrs.sdk;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.stream.wrs.sdk.utilities.SDKClient;
import com.stream.wrs.sdk.utilities.SDKWebClientBuilder;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@Log
@WebFluxTest
class SDKClientTest {

    @SneakyThrows
    @Test
    public void testDomainExtractor() {

        URI uri = new URI("http://192.168.31.143:5000/api/server-sdk/");
        String host = uri.getHost();

        System.out.println(SDKWebClientBuilder.buildFQDN("http://192.168.31.143:5000/api/server-sdk/"));

    }

    /**
     *
     */
    @SneakyThrows
    @Test
    public void testCountingHostUsingSDKClientSimple() {

        /*
        HashMap<?, ?> result = SDKClient.builder()
                .superLiveHost("http://192.168.31.143:5000/api/server-sdk/")
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build()
                .doGetRequest(SDKClient.builder().getEndpointCounting());
        */

        /* or as API's url */
        HashMap<?, ?> result = SDKClient.builder()
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build()
                .doGetRequest("http://192.168.31.143:5000/api/server-sdk/hosts/count");

        Assertions.assertTrue(!Objects.requireNonNull(result).isEmpty()
                && result.containsKey("data")
                && result.get("data") != null
        );
        log.info(new JsonMapper().writeValueAsString(result));
    }

    @SneakyThrows
    @Test
    public void testGettingAllHostUsingSDKClientSimpleWithQueryParams() {
        SDKClient client = SDKClient.builder()
                .superLiveHost("http://192.168.31.143:5000/api/server-sdk/")
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
        log.info(new JsonMapper().writeValueAsString(result));
    }

    @SneakyThrows
    @Test
    public void testGetAHostUsingSDKClientWithPathVariableHostId() {
        SDKClient client = SDKClient.builder()
                .superLiveHost("http://192.168.31.143:5000/api/server-sdk/")
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build();
        HashMap<?, ?> result = client
                .doGetRequest(
                        client,
                        SDKWebClientBuilder.buildURI(Optional.of(
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
    public void testCreatingAHostUsingSDKClientWithPathVariableHostId() {
        SDKClient client = SDKClient.builder()
                .superLiveHost("http://192.168.31.143:5000/api/server-sdk/")
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build();
        HashMap<?, ?> result = client
                .doGetRequest(
                        client,
                        SDKWebClientBuilder.buildURI(Optional.of(
                                        client.getEndpointHostPathVariable()),
                                "648693da5a508510f60625fb")
                );

        Assertions.assertTrue(!Objects.requireNonNull(result).isEmpty()
                && result.containsKey("data")
                && result.get("data") != null
        );
        log.info(new JsonMapper().writeValueAsString(result));
    }

    /**
     *
     */
    @SneakyThrows
    @Test
    public void testCreatingParticipant() {

        /*SDKClient client = SDKClient.builder()
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
}