package com.stream.wrs.sdk.test.api.post;

import com.google.gson.Gson;
import com.stream.wrs.sdk.config.ConfigurableProperties;
import com.stream.wrs.sdk.test.CommonTestConfig;
import com.stream.wrs.sdk.utilities.SDKClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 11/10/23
 * Project : superlive-sdk-java
 */
@Slf4j
class SDKClientGiftTest extends CommonTestConfig {

    @Test
    void testGetGifts() {
        final HashMap<?, ?> map = SDKClient.builder()
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .buildApiGift()
                .doGetRequest(ConfigurableProperties.Gift.getIndex());
        log.info(new Gson().toJson(map));
    }

    @Test
    void testAddAGift() {

        final Integer point = 100;
        final Integer status = 1;
        final String name = RandomStringUtils.randomAlphabetic(8).toUpperCase(Locale.ROOT);
        final String giftPack = "648c2ea29dc14af3b179dc38";
        final String imageUrl = "https://images.pexels.com/photos/145683/pexels-photo-145683.jpeg";
        final Map<String, Object> requestBody = new HashMap() {{
            put("name", name);
            put("image", imageUrl);
            put("point", point);
            put("giftPack", giftPack);
            put("status", status);
        }};

        final HashMap<?, ?> map = SDKClient.builder()
                .merchantHostId("648a77d088c133b4c4b96f8a")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .buildApiGift()
                .doPostRequest(ConfigurableProperties.Gift.getIndex(), requestBody);
        log.info(new Gson().toJson(map));
    }
}
