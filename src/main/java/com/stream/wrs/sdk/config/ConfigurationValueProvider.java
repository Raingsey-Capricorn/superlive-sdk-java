package com.stream.wrs.sdk.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 12/6/23
 * Project : superlive-sdk-java
 */
@Getter
@Component
public class ConfigurationValueProvider {

    @Value("${superlive.configuration.merchant.host}")
    private String superLiveHost;

    @Value("${superlive.configuration.merchant.endpoints.hosts}")
    private String endpointHosts;

    @Value("${superlive.configuration.merchant.endpoints.counting}")
    private String endpointCounting;

    @Value("${superlive.configuration.merchant.endpoints.path-variable}")
    private String endpointPathVariable;

    @Value("${superlive.configuration.merchant.authorizationKey}")
    private String accessAuthorization;

    @Value("${superlive.constants.authorization-key}")
    private String accessAuthorizationKey;

    @Value("${superlive.configuration.merchant.host-id}")
    private String merchantHostId;

    @Value("${webclient.duration.default}")
    private String defaultWaitingDuration;

    @Value("${webclient.duration.failsafe}")
    private String defaultFailsafeDuration;

}