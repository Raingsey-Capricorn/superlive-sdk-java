package com.stream.wrs.sdk.utilities;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 19/6/23
 * Project : superlive-sdk-java
 */
public abstract class APIPathBuilder {

    public SDKClient buildHost(SDKClient client) {

        return SDKClient.builder();
    }

    public SDKClient buildMerchant(SDKClient client) {

        return SDKClient.builder();
    }

    public SDKClient buildSticker(SDKClient client) {

        return SDKClient.builder();
    }

    public SDKClient buildGift(SDKClient client) {

        return SDKClient.builder();
    }

    private final SDKClient build(SDKClient client) {

        return SDKClient.builder();
    }


}
