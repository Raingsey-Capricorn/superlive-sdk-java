package com.stream.wrs.sdk.config;

import lombok.Getter;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 15/6/23
 * Project : superlive-sdk-java
 */
@Getter
public abstract class ConfigurationProperties {

    protected String superLiveHost;
    protected String endpointHosts;
    protected String endpointCounting;
    protected String endpointParticipants;
    protected String endpointHostPathVariable;
    protected String endpointParticipantsPathVariable;
    protected String accessAuthorization;
    protected String accessAuthorizationKey;
    protected String merchantHostId;
    protected String defaultWaitingDuration;
    protected String defaultFailsafeDuration;

}
