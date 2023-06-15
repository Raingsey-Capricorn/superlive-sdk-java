package com.stream.wrs.sdk;

import com.stream.wrs.sdk.utilities.SDKClient;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log
@SpringBootTest
class SDKClientTest {

    @Autowired
    SDKClient client;

    @Test
    public void testSDKClient() {
        Assertions.assertNotNull(client);
    }
}