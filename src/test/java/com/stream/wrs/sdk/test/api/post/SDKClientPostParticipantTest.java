package com.stream.wrs.sdk.test.api.post;

import com.stream.wrs.sdk.config.ConfigurableProperties;
import com.stream.wrs.sdk.test.CommonTestConfig;
import com.stream.wrs.sdk.utilities.SDKClient;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Locale;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 30/6/23
 * Project : superlive-sdk-java
 */
class SDKClientPostParticipantTest extends CommonTestConfig {

    @Test
    @Order(1)
    void testPostingParticipant() {

        final HashMap<?, ?> map = SDKClient.builder()
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .buildApiParticipant()
                .doPostRequest(ConfigurableProperties.Participant.getIndex(),
                        new HashMap() {{
                            put("name", RandomStringUtils.randomAlphabetic(8).toUpperCase(Locale.ROOT));
                            put("description", "User - " + RandomStringUtils.randomAlphabetic(15).toUpperCase(Locale.ROOT));
                        }});
        Assertions.assertTrue(HttpStatus.valueOf(Integer.parseInt(map.get("statusCode").toString())).is2xxSuccessful());
    }

    @Test
    @Order(2)
    void testPuttingParticipant() {

        final HashMap<?, ?> map = SDKClient.builder()
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .buildApiParticipant()
                .doPutRequest(ConfigurableProperties.Participant.getPathVariableId(),
                        "649e8d5dc53171b4d5e86d22",
                        new HashMap() {{
                            put("name", RandomStringUtils.randomAlphabetic(8).toUpperCase(Locale.ROOT));
                            put("description", "User - " + RandomStringUtils.randomAlphabetic(15).toUpperCase(Locale.ROOT));
                        }});
        Assertions.assertTrue(HttpStatus.valueOf(Integer.parseInt(map.get("statusCode").toString())).is2xxSuccessful());
    }
}
