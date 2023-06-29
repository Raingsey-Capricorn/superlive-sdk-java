package com.stream.wrs.sdk.test.api.post;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.stream.wrs.sdk.config.FileValueProvider;
import com.stream.wrs.sdk.utilities.SDKClient;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
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
class SDKClientPostingHostTest {

    @Autowired
    FileValueProvider fileProvider;

    @Order(1)
    @SneakyThrows
    @Test
    void testVersion01_PostANewHostUsingFileReaderFromJsonData() {

        //The username is unique, so before running the test, make sure to change username value
        final Map valueMap =
                fileProvider.readJsonRequestBodyAsMultiPartData("src/test/resources/sample-host-request-body.json");

        final SDKClient client = SDKClient.builder()
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build();

        Map version1_result = client.doPostRequest(
                client,
                client.getEndpointHosts(),
                valueMap
        );

        log.info(new JsonMapper().writeValueAsString(version1_result));
        Assertions.assertTrue(HttpStatus.valueOf(Integer.parseInt(version1_result.get("statusCode").toString())).is2xxSuccessful());
    }

    @Order(2)
    @SneakyThrows
    @Test
    void testVersion01_PostANewHostUsingHashMapSetup() {


        final SDKClient client = SDKClient.builder()
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build();

        HashMap version1_result = client.doPostRequest(
                client,
                client.getEndpointHosts(),
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
        log.info(new JsonMapper().writeValueAsString(version1_result));
        Assertions.assertTrue(HttpStatus.valueOf(Integer.parseInt(version1_result.get("statusCode").toString())).is2xxSuccessful());
    }

    @Order(3)
    @SneakyThrows
    @Test
    void testVersion02_PostANewHostUsingHashMapSetup() {

        HashMap version1_result = SDKClient.builder()
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build()
                .doPostRequest(
                        "/hosts",
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

        log.info(new JsonMapper().writeValueAsString(version1_result));
        Assertions.assertTrue(HttpStatus.valueOf(Integer.parseInt(version1_result.get("statusCode").toString())).is2xxSuccessful());
    }

    @Order(4)
    @SneakyThrows
    @Test
    void testVersion03_PostANewHostUsingHashMapSetup() {

        HashMap version1_result = SDKClient.builder()
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .buildApiHost()
                .doPostRequest(
                        "/",
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

        log.info(new JsonMapper().writeValueAsString(version1_result));
        Assertions.assertTrue(HttpStatus.valueOf(Integer.parseInt(version1_result.get("statusCode").toString())).is2xxSuccessful());
    }
}
