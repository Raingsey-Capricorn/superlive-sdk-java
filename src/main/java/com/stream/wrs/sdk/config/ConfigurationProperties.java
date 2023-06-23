package com.stream.wrs.sdk.config;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 15/6/23
 * Project : superlive-sdk-java
 */
@Getter
public abstract class ConfigurationProperties {

    private final String apiUri = "/api/server-sdk";

    public static enum ApiPath {
        HOST("/hosts"),
        PARTICIPANT("/participants"),
        GIFT("/gifts"),
        STICKER("/stickers");

        public final String pathName;

        ApiPath(String pathName) {
            this.pathName = pathName;
        }
    }

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

    @Setter
    protected boolean isHost;

    @Setter
    protected boolean isParticipants;

    @Setter
    protected boolean isSticker;

    @Setter
    protected boolean isGift;

    public List<Boolean> getAPIsPaths() {
        return Arrays.asList(isHost, isParticipants, isGift, isSticker);
    }
}
