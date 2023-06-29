package com.stream.wrs.sdk.model.request;

import lombok.Data;

import java.util.List;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 21/6/23
 * Project : superlive-sdk-java
 */
@Data
public class HostRequestBody {
    private String name;
    private String description;
    private String username;
    private String password;
    private List<String> giftPacks;
    private List<String> stickerPacks;
    private boolean isChatEnabled;

}
