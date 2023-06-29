package com.stream.wrs.sdk.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

import java.util.Arrays;
import java.util.List;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 15/6/23
 * Project : superlive-sdk-java
 */
@Log
@Getter
public abstract class ConfigurableProperties {

    public enum ApiPath {
        HOST("/hosts"),
        PARTICIPANT("/participants"),
        GIFT("/gifts"),
        UPLOAD("/upload"),
        STICKER("/stickers");

        public final String pathName;

        ApiPath(String pathName) {
            this.pathName = pathName;
        }

    }

    protected String superLiveHost;
    protected String superLiveApiUri = "/api/server-sdk";
    protected String accessAuthorization;
    protected String accessAuthorizationKey;
    protected String endpointHosts;
    protected String endpointCounting;
    protected String endpointParticipants;
    protected String endpointHostPathVariable;
    protected String endpointParticipantsPathVariable;
    protected String merchantHostId;
    protected String defaultWaitingDuration;
    protected String defaultFailsafeDuration;

    /**
     * Host API - class to manage the calling and usage
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Host {
        @Setter
        @Getter
        private static String index;
        @Setter
        @Getter
        private static String count;
        @Setter
        @Getter
        private static String pathVariableId;

    }

    /**
     * Participant API - class to manage the calling and usage
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Participant {
        @Setter
        @Getter
        private static String index;
        @Setter
        @Getter
        private static String giftPoints;
        @Setter
        @Getter
        private static String pathVariableId;
    }

    /**
     * Gift API - class to manage the calling and usage
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Gift {
        @Setter
        @Getter
        private static String index;
        @Setter
        @Getter
        private static String packs;
        @Setter
        @Getter
        private static String pathVariableId;
        @Setter
        @Getter
        private static String pathVariablePacksId;
    }

    /**
     * Sticker API - class to manage the calling and usage
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Sticker {
        @Setter
        @Getter
        private static String index;
        @Setter
        @Getter
        private static String packs;
        @Setter
        @Getter
        private static String pathVariableId;
        @Setter
        @Getter
        private static String pathVariablePacksId;
    }

    /**
     * Upload API - class to manage the calling and usage
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Upload {
        @Setter
        @Getter
        private static String index;
    }

    @Setter
    protected boolean isHost;
    @Setter
    protected boolean isParticipants;
    @Setter
    protected boolean isSticker;
    @Setter
    protected boolean isGift;
    @Setter
    protected boolean isUpload;

    protected static final String WARNING_MESSAGE = "Some field is not correctly configured for host-api. Checking is required!";

    public List<Boolean> getAPIsPaths() {
        return Arrays.asList(isHost, isParticipants, isGift, isSticker, isUpload);
    }

}
