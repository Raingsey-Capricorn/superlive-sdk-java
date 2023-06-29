package com.stream.wrs.sdk.test.api.put;

import com.stream.wrs.sdk.utilities.SDKClient;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Locale;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 21/6/23
 * Project : superlive-sdk-java
 */
@Log
@WebFluxTest
class SDKClientPuttingParticipantTest {

    @SneakyThrows
    @Test
    void testVersion01_createAParticipants() {

        final SDKClient client = SDKClient.builder()
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build();

        HashMap version1_result = client.doPostRequest(
                client,
                SDKClient.Participant.getIndex(),
                new HashMap() {{
                    put("name", RandomStringUtils.randomAlphabetic(5).toUpperCase(Locale.ROOT));
                    put("description", "Test host description");
                }}
        );

        Assertions.assertTrue(HttpStatus.valueOf(Integer.parseInt(version1_result.get("statusCode").toString())).is2xxSuccessful());
    }

    @SneakyThrows
    @Test
    void testVersion02_PutANewHostUsingHashMapSetup() {

        HashMap version1_result = SDKClient.builder()
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build()
                .doPostRequest(
                        SDKClient.Participant.getIndex(),
                        new HashMap() {{
                            put("name", RandomStringUtils.randomAlphabetic(5).toUpperCase(Locale.ROOT));
                            put("description", "Test host description");
                        }}
                );

        Assertions.assertTrue(HttpStatus.valueOf(Integer.parseInt(version1_result.get("statusCode").toString())).is2xxSuccessful());
    }

    @SneakyThrows
    @Test
    void testVersion03_PutANewHostUsingHashMapSetup() {

        HashMap version1_result = SDKClient.builder()
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .buildApiParticipant()
                .doPostRequest(
                        SDKClient.Participant.getIndex(),
                        new HashMap() {{
                            put("name", RandomStringUtils.randomAlphabetic(5).toUpperCase(Locale.ROOT));
                            put("description", "Test host description");
                        }}
                );

        Assertions.assertTrue(HttpStatus.valueOf(Integer.parseInt(version1_result.get("statusCode").toString())).is2xxSuccessful());
    }
}
