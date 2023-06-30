package com.stream.wrs.sdk.test.api.get.host;

import com.stream.wrs.sdk.utilities.SDKClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 6/10/23
 * Project : superlive-sdk-java
 */
@Slf4j
class TestAPICodeSnippet {

    @Test
    @SneakyThrows
    void initializeSDKClient() {
        final SDKClient sdkClient = SDKClient
                .builder()
                .superLiveHost("http://merch.sp.tv/api/server-sdk")
                .accessAuthorization("dqkoimeT_qNak4E9Fl6DfKY_")
                .build();
        final HashMap<?, ?> response = sdkClient.doGetRequest("/hosts");
        System.out.println("<<<< Status : " + response.get("statusCode"));
        System.out.println("<<<< data   : " + response.get("data"));
        System.out.println("<<<< extra   : " + response.get("extra"));
    }
}

