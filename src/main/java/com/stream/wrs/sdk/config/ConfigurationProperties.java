package com.stream.wrs.sdk.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

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
    protected String superLiveApiUri;
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
    public static class Host {
        public static String index;
        public static String count;
        public static String pathVariableId;
    }

    /**
     * Participant API - class to manage the calling and usage
     */
    public static class Participant {
        public static String index;
        public static String giftPoints;
        public static String pathVariableId;
    }

    /**
     * Gift API - class to manage the calling and usage
     */
    public static class Gift {
        public static String index;
        public static String packs;
        public static String pathVariableId;
        public static String pathVariablePacksId;
    }

    /**
     * Sticker API - class to manage the calling and usage
     */

    public static class Sticker {
        public static String index;
        public static String packs;
        public static String pathVariableId;
        public static String pathVariablePacksId;
    }

    /**
     * Upload API - class to manage the calling and usage
     */
    public static class Upload {
        public static String index;
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

    public List<Boolean> getAPIsPaths() {
        return Arrays.asList(isHost, isParticipants, isGift, isSticker);
    }
}
