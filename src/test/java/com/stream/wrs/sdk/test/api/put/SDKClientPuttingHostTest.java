package com.stream.wrs.sdk.test.api.put;

import com.stream.wrs.sdk.config.FileValueProvider;
import com.stream.wrs.sdk.utilities.SDKClient;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 21/6/23
 * Project : superlive-sdk-java
 */
@Log
@SpringBootTest
class SDKClientPuttingHostTest {

    @Autowired
    FileValueProvider fileProvider;

    @SneakyThrows
    @Test
    void testVersion01_PutANewHostUsingFileReaderFromJsonData() {

        //The username is unique, so before running the test, make sure to change username value
        final Map valueMap =
                fileProvider.readJsonRequestBodyAsMultiPartData("src/test/resources/sample-host-request-body.json");

        final SDKClient client = SDKClient.builder()
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build();

        HashMap version1_result = client.doPutRequest(
                client,
                client.getEndpointHostPathVariable(),
                "649566c41f37fc99fb8de30b",
                valueMap
        );

        Assertions.assertTrue(HttpStatus.valueOf(Integer.parseInt(version1_result.get("statusCode").toString())).is2xxSuccessful());
    }


    @SneakyThrows
    @Test
    void testVersion01_PutANewHostUsingHashMapSetup() {


        final SDKClient client = SDKClient.builder()
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build();

        HashMap version1_result = client.doPutRequest(
                client,
                client.getEndpointHostPathVariable(),
                "649566c41f37fc99fb8de30b",
                new HashMap() {{
                    put("name", "SampleHost" + RandomStringUtils.randomAlphabetic(5).toUpperCase(Locale.ROOT));
                    put("description", "Test host description");
                    put("username", RandomStringUtils.randomAlphabetic(15).toUpperCase(Locale.ROOT));
                    put("password", "123456");
                    put("giftPacks", new String[]{"648c2ea29dc14af3b179dc38"});
                    put("stickerPacks", new String[]{"64926ab9e969a07a83abfb93"});
                    put("isChatEnabled", true);
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
                .doPutRequest(
                        SDKClient.Host.getPathVariableId(),
                        "649566c41f37fc99fb8de30b",
                        new HashMap() {{
                            put("name", "SampleHost" + RandomStringUtils.randomAlphabetic(5).toUpperCase(Locale.ROOT));
                            put("description", "Test host description");
                            put("username", RandomStringUtils.randomAlphabetic(15).toUpperCase(Locale.ROOT));
                            put("password", "123456");
                            put("giftPacks", new String[]{"648c2ea29dc14af3b179dc38"});
                            put("stickerPacks", new String[]{"64926ab9e969a07a83abfb93"});
                            put("isChatEnabled", true);
                        }}
                );

        Assertions.assertTrue(HttpStatus.valueOf(Integer.parseInt(version1_result.get("statusCode").toString())).is2xxSuccessful());
    }

    @SneakyThrows
    @Test
    void testVersion03_PutANewHostUsingHashMapSetup() {

        HashMap version1_result = SDKClient.builder()
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .buildApiHost()
                .doPutRequest(
                        "/{id}",
                        "649566c41f37fc99fb8de30b",
                        new HashMap() {{
                            put("name", "SampleHost" + RandomStringUtils.randomAlphabetic(5).toUpperCase(Locale.ROOT));
                            put("description", "Test host description");
                            put("username", RandomStringUtils.randomAlphabetic(15).toUpperCase(Locale.ROOT));
                            put("password", "123456");
                            put("giftPacks", new String[]{"648c2ea29dc14af3b179dc38"});
                            put("stickerPacks", new String[]{"64926ab9e969a07a83abfb93"});
                            put("isChatEnabled", true);
                        }}
                );

        Assertions.assertTrue(HttpStatus.valueOf(Integer.parseInt(version1_result.get("statusCode").toString())).is2xxSuccessful());
    }
}
