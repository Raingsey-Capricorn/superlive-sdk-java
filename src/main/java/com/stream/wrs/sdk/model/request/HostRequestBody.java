package com.stream.wrs.sdk.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    public String name;
    public String description;
    public String username;
    public String password;
    public List<String> giftPacks;
    public List<String> stickerPacks;
    @JsonProperty("isChatEnabled")
    public boolean isChatEnabled;

}
