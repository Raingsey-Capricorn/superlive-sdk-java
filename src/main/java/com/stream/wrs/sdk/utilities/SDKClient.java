package com.stream.wrs.sdk.utilities;

import com.stream.wrs.sdk.config.ConfigurationProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 15/6/23
 * Project : superlive-sdk-java
 */
@Log
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SDKClient extends ConfigurationProperties {

    @PostConstruct
    private void preset() {
        merchantHostId = null;
        accessAuthorization = null;
        endpointCounting = "/count";
        defaultWaitingDuration = "30";
        defaultFailsafeDuration = "60";
        endpointPathVariable = "/hosts/{id}";
        accessAuthorizationKey = "Authorization";
        superLiveHost = "http://merch.sp.tv/api/server-sdk";
        endpointHosts = "http://merch.sp.tv/api/server-sdk/hosts";
    }

    /**
     * Instantiate SDKClient object
     *
     * @return
     */
    public SDKClient build() {
        if (superLiveHost != null && !superLiveHost.isEmpty() &&
                endpointHosts != null && !endpointHosts.isEmpty() &&
                endpointCounting != null && !endpointCounting.isEmpty() &&
                endpointPathVariable != null && !endpointPathVariable.isEmpty() &&
                accessAuthorization != null && !accessAuthorization.isEmpty() &&
                accessAuthorizationKey != null && !accessAuthorizationKey.isEmpty() &&
                merchantHostId != null && !merchantHostId.isEmpty() &&
                defaultWaitingDuration != null && !defaultWaitingDuration.isEmpty() &&
                defaultFailsafeDuration != null && !defaultFailsafeDuration.isEmpty()
        ) {
            return this;
        } else {
            log.warning("Some field is not correctly configured. Checking is required!");
            return this;
        }
    }

    /**
     * Set superlive's host
     *
     * @param superLiveHost : merchant's superlive URL
     * @return
     */
    public SDKClient superLiveHost(String superLiveHost) {
        this.superLiveHost = superLiveHost;
        return this;
    }

    /**
     * @param endpointHosts
     * @return
     */
    public SDKClient endpointHosts(String endpointHosts) {
        this.endpointHosts = endpointHosts;
        return this;
    }

    /**
     * @param endpointCounting
     * @return
     */
    public SDKClient endpointCounting(String endpointCounting) {
        this.endpointCounting = endpointCounting;
        return this;
    }

    /**
     * @param endpointPathVariable
     * @return
     */
    public SDKClient endpointPathVariable(String endpointPathVariable) {
        this.endpointPathVariable = endpointPathVariable;
        return this;
    }

    /**
     * @param accessAuthorization
     * @return
     */
    public SDKClient accessAuthorization(String accessAuthorization) {
        this.accessAuthorization = accessAuthorization;
        return this;
    }

    /**
     * @param accessAuthorizationKey
     * @return
     */
    public SDKClient accessAuthorizationKey(String accessAuthorizationKey) {
        this.accessAuthorizationKey = accessAuthorizationKey;
        return this;
    }

    /**
     * Required
     *
     * @param merchantHostId
     * @return
     */
    public SDKClient merchantHostId(String merchantHostId) {
        this.merchantHostId = merchantHostId;
        return this;
    }

    /**
     * Optional setting
     *
     * @param defaultWaitingDuration
     * @return
     */
    public SDKClient defaultWaitingDuration(String defaultWaitingDuration) {
        this.defaultWaitingDuration = defaultWaitingDuration;
        return this;
    }

    /**
     * Optional setting
     *
     * @param defaultFailsafeDuration
     * @return
     */
    public SDKClient defaultFailsafeDuration(String defaultFailsafeDuration) {
        this.defaultFailsafeDuration = defaultFailsafeDuration;
        return this;
    }


}
