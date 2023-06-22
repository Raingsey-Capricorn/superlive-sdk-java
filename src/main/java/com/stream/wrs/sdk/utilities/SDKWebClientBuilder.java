package com.stream.wrs.sdk.utilities;

import lombok.SneakyThrows;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.function.Function;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 16/6/23
 * Project : superlive-sdk-java
 */
public abstract class SDKWebClientBuilder {
    public static final Integer FQDN = 1;
    public static final Integer PATH = 2;

    /**
     * @param url
     * @return
     */
    public static String buildURL(Optional<String> url) {
        if (url.isPresent() && !url.get().isEmpty())
            return url.get();
        else return null;
    }

    /**
     * This function is supposed to be using as customization to webClient's uriBuilder
     * SimpleURI include on URL's URI path only see below properties of SDKClient for reference
     *
     * @param simpleURI : String literal as URI's path
     * @return lambda function as URIBuilder
     * @see SDKClient#getEndpointCounting()
     * @see SDKClient#getEndpointHosts()
     * @see SDKClient#getEndpointParticipants()
     */
    public static Function<UriBuilder, URI> buildURI(String simpleURI) {
        return uriBuilder ->
                uriBuilder.path(simpleURI).build();
    }

    /**
     * This function is supposed to be using as customization to webClient's uriBuilder
     * URI path only see below properties of SDKClient for reference
     *
     * @param uri                 : Optional URI, that could be empty, null, but if present:
     *                            It must be the URI of direct path that follow by '?' query parameters
     * @param optionalQueryParams : present/absence query parameters
     * @return lambda function as URIBuilder
     * @see <a href="http://merch.sp.tv/api/server-sdk-docs#/hosts/ServerSDKHostController_findAll">(page, limit & search)</a>
     * @see UriBuilder#queryParam(String, Object...)
     */
    public static Function<UriBuilder, URI> buildURI(Optional<String> uri, HashMap<String, ?> optionalQueryParams) {
        return uriBuilder -> {
            if (uri.isPresent() && !uri.get().isEmpty())
                uriBuilder.path(uri.get());

            optionalQueryParams
                    .entrySet()
                    .stream()
                    .forEach(e -> uriBuilder.queryParam(e.getKey(), Optional.of(e.getValue()).get()));
            return uriBuilder.build();
        };
    }

    /**
     * This function is supposed to be using as customization to webClient's uriBuilder
     * SimpleURI include on URL's URI path only see below properties of SDKClient for reference
     *
     * @param uri            : Optional URI, that could be empty, null, but if present:
     *                       It must be the URI of direct path that follow by '/' and value
     * @param pathVariableId : Required and valid id to support the path's of the URI
     * @return
     */
    public static Function<UriBuilder, URI> buildURI(Optional<String> uri, Object pathVariableId) {
        return uriBuilder -> {
            if (uri.isPresent() && !uri.get().isEmpty())
                uriBuilder.path(uri.get());
            return uriBuilder.build(pathVariableId);
        };
    }


    /**
     * @param dataMap : simple single level formList
     * @return LinkedMultiValueMap<String, Class < ? extends Object>>
     */
    public static MultiValueMap buildRequestBody(HashMap dataMap) {

        dataMap.entrySet()
                .stream()
                .filter(n -> (((Map.Entry) n).getValue() instanceof List) == false)
                .forEach(e -> {
                    Map.Entry entry = ((Map.Entry<String, ?>) e);
                    dataMap.replace(
                            entry.getKey(),
                            entry.getValue(),
                            Collections.singletonList(entry.getValue()));
                });
        /**
         * Return preferred request body for processing
         */
        return new LinkedMultiValueMap() {{
            putAll(dataMap);
        }};
    }

    /**
     * @param url
     * @return
     */
    @SneakyThrows
    public static HashMap<Integer, String> buildFQDN(String url) {
        URL fqdn = new URL(url);
        return new HashMap<Integer, String>() {{
            put(FQDN, String.format("%s://%s%s", fqdn.getProtocol(), fqdn.getHost(), fqdn.getPort() == -1 ? "" : ":" + fqdn.getPort()));
            put(PATH, String.format("%s", fqdn.getPath()));
        }};
    }



}
