package com.stream.wrs.sdk.test.get;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.stream.wrs.sdk.test.CommonTestConfig;
import com.stream.wrs.sdk.utilities.SDKClient;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Objects;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 20/6/23
 * Project : superlive-sdk-java
 */
@Log
public class SDKClientGettingHostTest extends CommonTestConfig {

    /**
     *
     */
    @Test
    @Order(1)
    @SneakyThrows
    public void testVersion01_CountingHostUsingSDKClientSimple() {

        final SDKClient client = SDKClient.builder()
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build();

        final HashMap<?, ?> version1_result =
                client.doGetRequest(
                        client,
                        client.getEndpointCounting());

        Assertions.assertTrue(
                !Objects.requireNonNull(version1_result).isEmpty()
                        && version1_result.containsKey("data")
                        && version1_result.get("data") != null
        );
        log.info(new JsonMapper().writeValueAsString(version1_result));
    }


    @Test
    @Order(2)
    @SneakyThrows
    public void testVersion02_CountingHostUsingSDKClientSimple() {

        HashMap<?, ?> version2_result = SDKClient.builder()
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build()
                .doGetRequest("http://merch.sp.tv/api/server-sdk/hosts/count");

        Assertions.assertTrue(
                !Objects.requireNonNull(version2_result).isEmpty()
                        && version2_result.containsKey("data")
                        && version2_result.get("data") != null
        );
        log.info(new JsonMapper().writeValueAsString(version2_result));
    }

    /**
     *
     */
    @Test
    @Order(3)
    @SneakyThrows
    public void testVersion03_CountingHostUsingSDKClientSimple() {

        HashMap<?, ?> version3_result = SDKClient.builder()
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .buildMerchantHostAPI()
                .doGetRequest("/count");

        Assertions.assertTrue(
                !Objects.requireNonNull(version3_result).isEmpty()
                        && version3_result.containsKey("data")
                        && version3_result.get("data") != null
        );
        log.info(new JsonMapper().writeValueAsString(version3_result));
    }

}