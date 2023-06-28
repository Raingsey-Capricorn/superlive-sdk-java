package com.stream.wrs.sdk.test.api.delete;

import com.stream.wrs.sdk.test.CommonTestConfig;
import com.stream.wrs.sdk.utilities.SDKClient;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Objects;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 26/6/23
 * Project : superlive-sdk-java
 */

@Log
public class SDKClientDeletionTest extends CommonTestConfig {

    @Test
    @Order(1)
    @Disabled
    @SneakyThrows
    public void testVersion01_deletingAHostUsingHostId() {

        final SDKClient client = SDKClient.builder()
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build();

        //This ID has already deleted, so when running just give another existing host-id to delete
        final HashMap<?, ?> version1_result =
                client.doDeleteRequest(
                        client,
                        "6488327a96bc8514776f5a41"
                        ,
                        client.getEndpointHostPathVariable());

        Assertions.assertTrue(
                !Objects.requireNonNull(version1_result).isEmpty()
                        && version1_result.containsKey("data")
                        && version1_result.get("data") != null
        );
    }

    @Test
    @Order(2)
    @Disabled
    @SneakyThrows
    public void testVersion02_deletingAHostUsingHostId() {

        final HashMap version1_result = SDKClient.builder()
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build()
                .doDeleteRequest(
                        "http://merch.sp.tv/api/server-sdk/hosts/{id}",
                        "648c2db09dc14af3b179dbab"
                );

        Assertions.assertTrue(
                !Objects.requireNonNull(version1_result).isEmpty()
                        && version1_result.containsKey("data")
                        && version1_result.get("data") != null
        );
    }

    @Test
    @Order(3)
    @Disabled
    @SneakyThrows
    public void testVersion03_deletingAHostUsingHostId() {

        final HashMap version1_result = SDKClient.builder()
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .buildApiHost()
                .doDeleteRequest(
                        SDKClient.builder().getEndpointHostPathVariable(),
                        "64926d22e969a07a83abfc90"
                );

        Assertions.assertTrue(
                !Objects.requireNonNull(version1_result).isEmpty()
                        && version1_result.containsKey("data")
                        && version1_result.get("data") != null
        );
    }
}
