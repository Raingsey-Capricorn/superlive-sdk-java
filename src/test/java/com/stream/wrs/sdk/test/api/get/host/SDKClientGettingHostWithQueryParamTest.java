package com.stream.wrs.sdk.test.api.get.host;

import com.stream.wrs.sdk.test.CommonTestConfig;
import com.stream.wrs.sdk.utilities.SDKClient;
import com.stream.wrs.sdk.utilities.SDKWebClientBuilder;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 20/6/23
 * Project : superlive-sdk-java
 * This Test case is to ensure consistency between version of the SDKClient API
 */
@Log
public class SDKClientGettingHostWithQueryParamTest extends CommonTestConfig {

    /**
     *
     */
    @Test
    @Order(1)
    @SneakyThrows
    public void testVersion01_GetHostUsingSDKClientWithQueryParam() {

        final SDKClient client = SDKClient.builder()
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build();

        final HashMap<?, ?> version1_result =
                client.doGetRequest(
                        client,
                        SDKWebClientBuilder.buildHttpGetURI(
                                Optional.of(client.getEndpointHosts()),
                                new HashMap() {{
                                    put("page", "1");
                                    put("limit", "1");
                                    put("search", "");
                                }}
                        )
                );

        Assertions.assertTrue(
                !Objects.requireNonNull(version1_result).isEmpty()
                        && version1_result.containsKey("data")
                        && version1_result.get("data") != null
        );
    }

    /**
     *
     */
    @Test
    @Order(2)
    @SneakyThrows
    public void testVersion02_GetHostUsingSDKClientWithQueryParam() {

        HashMap<?, ?> version2_result = SDKClient.builder()
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build()
                .doGetRequest("http://merch.sp.tv/api/server-sdk/hosts",
                        SDKWebClientBuilder.buildHttpGetURI(
                                Optional.of("/"),
                                new HashMap() {{
                                    put("page", "1");
                                    put("limit", "3");
                                    put("search", "");
                                }}
                        ));

        Assertions.assertTrue(
                !Objects.requireNonNull(version2_result).isEmpty()
                        && version2_result.containsKey("data")
                        && version2_result.get("data") != null
        );
    }

    /**
     *
     */
    @Test
    @Order(3)
    @SneakyThrows
    public void testVersion03_GetHostUsingSDKClientWithQueryParam() {

        HashMap<?, ?> version3_result = SDKClient.builder()
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .buildApiHost()
                .doGetRequest("/",
                        SDKWebClientBuilder.buildHttpGetURI(
                                Optional.of("/"),
                                new HashMap() {{
                                    put("page", "1");
                                    put("limit", "5");
                                    put("search", "");
                                }}
                        ));

        Assertions.assertTrue(
                !Objects.requireNonNull(version3_result).isEmpty()
                        && version3_result.containsKey("data")
                        && version3_result.get("data") != null
        );
    }

}