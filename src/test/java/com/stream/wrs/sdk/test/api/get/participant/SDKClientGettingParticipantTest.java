package com.stream.wrs.sdk.test.api.get.participant;

import com.stream.wrs.sdk.config.ConfigurableProperties;
import com.stream.wrs.sdk.test.CommonTestConfig;
import com.stream.wrs.sdk.utilities.SDKClient;
import com.stream.wrs.sdk.utilities.SDKWebClientBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 20/6/23
 * Project : superlive-sdk-java
 */
@Slf4j
class SDKClientGettingParticipantTest extends CommonTestConfig {

    /**
     *
     */
    @Test
    @Order(1)
    @SneakyThrows
    void testVersion01_ParticipantsUsingSDKClientSimple() {

        final SDKClient client = SDKClient.builder()
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build();

        final Map<?, ?> version1_result =
                client.doGetRequest(
                        client,
                        SDKClient.Participant.getIndex());

        Assertions.assertTrue(
                !Objects.requireNonNull(version1_result).isEmpty()
                        && version1_result.containsKey("data")
                        && version1_result.get("data") != null
        );
    }

    @Test
    @Order(2)
    @SneakyThrows
    void testVersion02_ParticipantsUsingSDKClientSimple() {

        HashMap<?, ?> version2_result = SDKClient.builder()
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build()
                .doGetRequest("http://merch.sp.tv/api/server-sdk/participants");

        Assertions.assertTrue(
                !Objects.requireNonNull(version2_result).isEmpty()
                        && version2_result.containsKey("data")
                        && version2_result.get("data") != null
        );
    }

    @Test
    @Order(3)
    @SneakyThrows
    void testVersion03__ParticipantsUsingSDKClientSimple() {

        HashMap<?, ?> version3_result = SDKClient.builder()
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .buildApiParticipant()
                .doGetRequest(SDKClient.Participant.getIndex());

        Assertions.assertTrue(
                !Objects.requireNonNull(version3_result).isEmpty()
                        && version3_result.containsKey("data")
                        && version3_result.get("data") != null
        );
    }

    @Test
    @Order(4)
    @SneakyThrows
    void testVersion03__ParticipantsUsingSDKClientSimpleNoType() {

        HashMap<?, ?> version3_result = SDKClient.builder()
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .buildApiParticipant()
                .doGetRequest(SDKClient.Participant.getIndex() + "/no-type");

        List filteredList = (List) ((ArrayList) version3_result.get("data"))
                .stream()
                .filter(p -> Boolean.parseBoolean(((LinkedHashMap) p).get("isGuest").toString()))
                .collect(Collectors.toList());

        log.info("Filtered guest participants {}", filteredList);
        Assertions.assertTrue(
                !Objects.requireNonNull(version3_result).isEmpty()
                        && version3_result.containsKey("data")
                        && version3_result.get("data") != null
        );
    }

    @Test
    @Order(5)
    @SneakyThrows
    void testVersion04__ParticipantsUsingSDKClientWithQueryParams() {

        HashMap<?, ?> version4_result = SDKClient.builder()
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .buildApiParticipant()
                .doGetRequest(ConfigurableProperties.Participant.getIndex(),
                        SDKWebClientBuilder.buildHttpGetURI(Optional.empty(),
                                Optional.of(new HashMap() {{
                                    put("page", "1");
                                    put("limit", "1");
                                    put("search", "");
                                }})
                        )
                );
        Assertions.assertTrue(
                !Objects.requireNonNull(version4_result).isEmpty()
                        && version4_result.containsKey("data")
                        && version4_result.get("data") != null
        );
    }

}
