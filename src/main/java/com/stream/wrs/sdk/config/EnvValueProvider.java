package com.stream.wrs.sdk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 12/6/23
 * Project : superlive-sdk-java
 */

@Component
public class EnvValueProvider extends ConfigurationProperties {

    @Value("${superlive.configuration.merchant.host:http://merch.sp.tv/api/server-sdk}")
    private void setSuperLiveHost(String superLiveHost) {
        this.superLiveHost = superLiveHost;
    }

    @Value("${superlive.configuration.merchant.endpoints.hosts:http://merch.sp.tv/api/server-sdk/hosts}")
    private void setEndpointHosts(String endpointHosts) {
        this.endpointHosts = endpointHosts;
    }

    @Value("${superlive.configuration.merchant.endpoints.counting:http://merch.sp.tv/api/server-sdk/hosts/count}")
    private void setEndpointCounting(String endpointCounting) {
        this.endpointCounting = endpointCounting;
    }

    @Value("${superlive.configuration.merchant.endpoints.path-variable:http://merch.sp.tv/api/server-sdk/hosts/{id}}")
    private void setEndpointPathVariable(String endpointPathVariable) {
        this.endpointHostPathVariable = endpointPathVariable;
    }

    @Value("${superlive.configuration.merchant.endpoints.participants:http://merch.sp.tv/api/server-sdk/participants}")
    private void setEndpointParticipants(String endpointParticipants) {
        this.endpointParticipants = endpointParticipants;
    }

    @Value("${superlive.configuration.merchant.authorization-value:648693da5a508510f60625fb}")
    private void setAccessAuthorization(String accessAuthorization) {
        this.accessAuthorization = accessAuthorization;
    }

    @Value("${superlive.constants.authorization-key:Authorization}")
    private void setAccessAuthorizationKey(String accessAuthorizationKey) {
        this.accessAuthorizationKey = accessAuthorizationKey;
    }

    @Value("${superlive.configuration.merchant.host-id:648a77d088c133b4c4b96f8a}")
    private void setMerchantHostId(String merchantHostId) {
        this.merchantHostId = merchantHostId;
    }

    @Value("${webclient.duration.default:30}")
    private void setDefaultWaitingDuration(String defaultWaitingDuration) {
        this.defaultWaitingDuration = defaultWaitingDuration;
    }

    @Value("${webclient.duration.failsafe:60}")
    private void setDefaultFailsafeDuration(String defaultFailsafeDuration) {
        this.defaultFailsafeDuration = defaultFailsafeDuration;
    }
}