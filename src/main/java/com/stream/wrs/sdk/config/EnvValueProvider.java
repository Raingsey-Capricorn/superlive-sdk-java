package com.stream.wrs.sdk.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 12/6/23
 * Project : superlive-sdk-java
 */

@Component
public class EnvValueProvider extends ConfigurationProperties {

    @Value("${config.superlive.auth-value}")
    private void accessAuthorization(String accessAuthorization) {
        this.accessAuthorization = accessAuthorization;
    }

    @Value("${config.superlive.host}")
    private void setSuperLiveHost(String superLiveHost) {
        this.superLiveHost = superLiveHost;
    }

    @Value("${config.superlive.sdk-api}")
    private void setSuperLiveApiUri(String superLiveApiUri) {
        this.superLiveApiUri = superLiveApiUri;
    }

    @Value("${config.superlive.endpoints.host.index:http://merch.sp.tv/api/server-sdk/hosts}")
    private void setHostIndex(String hostIndex) {
        Host.index = hostIndex;
    }

    @Value("${config.superlive.endpoints.host.count:http://merch.sp.tv/api/server-sdk/hosts/count}")
    private void setHostCount(String hostCount) {
        Host.count = hostCount;
    }

    @Value("${config.superlive.endpoints.host.path-variable:http://merch.sp.tv/api/server-sdk/hosts/{id}}")
    private void setHostPathVariable(String hostPathVariableId) {
        Host.pathVariableId = hostPathVariableId;
    }

    @Value("${config.superlive.endpoints.participant.index:http://merch.sp.tv/api/server-sdk/participants}")
    private void setParticipantIndex(String participantIndex) {
        Participant.index = participantIndex;
    }

    @Value("${config.superlive.endpoints.participant.path-variable:http://merch.sp.tv/api/server-sdk/participants/{id}}")
    private void setParticipantPathVariableId(String pathVariableId) {
        Participant.pathVariableId = pathVariableId;
    }

    @Value("${config.superlive.endpoints.participant.gift-points:http://merch.sp.tv/api/server-sdk/participants/{id}/credit/gift-point}")
    private void setParticipantGiftPoints(String giftPoints) {
        Participant.giftPoints = giftPoints;
    }

    @Value("${config.superlive.endpoints.gift.index:http://merch.sp.tv/api/server-sdk/gifts}")
    private void setGiftIndex(String giftIndex) {
        Gift.index = giftIndex;
    }

    @Value("${config.superlive.endpoints.gift.packs:http://merch.sp.tv/api/server-sdk/gifts/packs}")
    private void setGiftPacks(String giftPacks) {
        Gift.packs = giftPacks;
    }

    @Value("${config.superlive.endpoints.gift.path-variable:http://merch.sp.tv/api/server-sdk/gifts/{id}}")
    private void setGiftPathVariableId(String giftPathVariableId) {
        Gift.pathVariableId = giftPathVariableId;
    }

    @Value("${config.superlive.endpoints.gift.packs-path-variable:http://merch.sp.tv/api/server-sdk/gifts/packs/{id}}")
    private void setGiftPathVariablePacksId(String giftPathVariablePacksId) {
        Gift.pathVariablePacksId = giftPathVariablePacksId;
    }

    @Value("${config.superlive.endpoints.sticker.index:http://merch.sp.tv/api/server-sdk/stickers}")
    private void setStickerIndex(String stickerIndex) {
        Sticker.index = stickerIndex;
    }

    @Value("${config.superlive.endpoints.sticker.packs:http://merch.sp.tv/api/server-sdk/stickers/packs}")
    private void setStickerPacks(String stickerPacks) {
        Sticker.packs = stickerPacks;
    }

    @Value("${config.superlive.endpoints.sticker.path-variable:http://merch.sp.tv/api/server-sdk/stickers/{id}}")
    private void setStickerPathVariableId(String stickerPathVariableId) {
        Sticker.pathVariableId = stickerPathVariableId;
    }

    @Value("${config.superlive.endpoints.sticker.packs-path-variable:http://merch.sp.tv/api/server-sdk/stickers/packs/{id}}")
    private void setStickerPathVariablePacksId(String stickerPathVariablePacksId) {
        Sticker.pathVariablePacksId = stickerPathVariablePacksId;
    }

    @Value("${config.superlive.endpoints.upload.index:http://merch.sp.tv/api/server-sdk/upload/file}")
    private void setUploadIndex(String uploadIndex) {
        Upload.index = uploadIndex;
    }
}