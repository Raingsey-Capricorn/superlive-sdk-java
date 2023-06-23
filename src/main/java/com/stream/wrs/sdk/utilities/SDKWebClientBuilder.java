package com.stream.wrs.sdk.utilities;

import com.stream.wrs.sdk.config.ConfigurationProperties;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 16/6/23
 * Project : superlive-sdk-java
 */
@Log
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
    public static Function<UriBuilder, URI> buildHttpGetURI(String simpleURI) {
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
    public static Function<UriBuilder, URI> buildHttpGetURI(Optional<String> uri, HashMap<String, ?> optionalQueryParams) {
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
    public static Function<UriBuilder, URI> buildHttpGetURI(Optional<String> uri, Object pathVariableId) {
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

    /**
     * @param fqdn
     * @param uriBuilderFunction
     * @return
     */
    private static WebClient.RequestHeadersSpec<?> buildHttpGetURI(
            final HashMap<Integer, String> fqdn,
            final Function<UriBuilder, URI> uriBuilderFunction) {

        return WebClient.create(fqdn.get(SDKWebClientBuilder.FQDN)).get().uri((uriBuilder) -> {
            if (uriBuilderFunction == null) {
                uriBuilder.path(fqdn.get(SDKWebClientBuilder.PATH));
                log.info("GET Requesting through url => :" + uriBuilder.build());
                return uriBuilder.build();
            } else {
                uriBuilder.path(fqdn.get(SDKWebClientBuilder.PATH));
                URI uri = uriBuilderFunction.apply(uriBuilder);
                log.info("GET Requesting through url => :" + uri);
                return uri;
            }
        });
    }

    /**
     * @param fqdn
     * @param path
     * @return
     */
    private static WebClient.RequestHeadersSpec<?> buildHttpGetURI(
            final String fqdn,
            final String path,
            final Function<UriBuilder, URI> uriBuilderFunction) {

        return WebClient.create(fqdn).get().uri((uriBuilder) -> {
            if (uriBuilderFunction == null) {
                uriBuilder.path(path);
                log.info("GET Requesting through url => :" + uriBuilder.build());
                return uriBuilder.build();
            } else {
                uriBuilder.path(path);
                URI uri = uriBuilderFunction.apply(uriBuilder);
                log.info("GET Requesting through url => :" + uri);
                return uri;
            }
        });
    }

    /**
     * @param fqdn
     * @param path
     * @return
     */
    private static WebClient.RequestBodySpec buildHttpPostURI(
            final String fqdn,
            final String path) {

        return WebClient.create(fqdn)
                .post()
                .uri((uriBuilder) -> {
                    uriBuilder.path(path);
                    log.info("POST Requesting through url => :" + uriBuilder.build());
                    return uriBuilder.build();
                });
    }

    private static ExchangeFilterFunction requestLogger() {
        return (clientRequest, next) -> {
            clientRequest.headers().forEach((name, values) -> log.info(String.format("%s : %s", name, values)));
            log.info(String.format("Request URL : %s", clientRequest.url()));
            return next.exchange(clientRequest);
        };
    }


    /**
     * @param fqdn
     * @return
     */
    private static WebClient.RequestBodySpec buildHttpPostURI(
            final HashMap<Integer, String> fqdn) {

        return WebClient.create(fqdn.get(SDKWebClientBuilder.FQDN)).post().uri((uriBuilder) -> {
            uriBuilder.path(fqdn.get(SDKWebClientBuilder.PATH));
            log.info("GET Requesting through url => :" + uriBuilder.build());
            return uriBuilder.build();
        });
    }


    private static WebClient.RequestBodySpec buildHttpPutURI(
            final String id,
            final HashMap<Integer, String> fqdn) {

        return WebClient.create(fqdn.get(SDKWebClientBuilder.FQDN))
                .put()
                .uri(uriBuilder -> {
                    URI uri = uriBuilder
                            .path(fqdn.get(SDKWebClientBuilder.PATH))
                            .build(id);
                    log.info("PUT Requesting through url => :" + uri);
                    return uri;
                });
    }

    private static WebClient.RequestBodySpec buildHttpPutURI(
            final String superliveHost,
            final String id,
            final String path) {

        return WebClient.create(superliveHost)
                .put()
                .uri(uriBuilder -> {
                    URI uri = uriBuilder
                            .path(path)
                            .build(id);
                    log.info("PUT Requesting through url => :" + uri);
                    return uri;
                });
    }


    /**
     * @param uri
     * @param uriBuilderFunction
     * @param booleanList
     * @param superLiveHost
     * @return
     */
    public static WebClient.RequestHeadersSpec<?> buildAPIPathForHttpGet(
            final String uri,
            final Function<UriBuilder, URI> uriBuilderFunction,
            final List<Boolean> booleanList,
            final String superLiveHost) {

        if (booleanList.get(0)) {
            return buildHttpGetURI(superLiveHost,
                    String.format("%s/%s", ConfigurationProperties.ApiPath.HOST.pathName, uri),
                    uriBuilderFunction);
        } else if (booleanList.get(1)) {
            return buildHttpGetURI(superLiveHost,
                    String.format("%s/%s", ConfigurationProperties.ApiPath.PARTICIPANT.pathName, uri),
                    uriBuilderFunction);
        } else if (booleanList.get(2)) {
            return buildHttpGetURI(superLiveHost,
                    String.format("%s/%s", ConfigurationProperties.ApiPath.GIFT.pathName, uri),
                    uriBuilderFunction);
        } else if (booleanList.get(3)) {
            return buildHttpGetURI(superLiveHost,
                    String.format("%s/%s", ConfigurationProperties.ApiPath.STICKER.pathName, uri),
                    uriBuilderFunction);
        } else {
            if (Pattern.compile("((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)")
                    .matcher(uri)
                    .matches()) {
                return buildHttpGetURI(SDKWebClientBuilder.buildFQDN(uri), uriBuilderFunction);
            } else {
                return buildHttpGetURI(superLiveHost, uri, uriBuilderFunction);
            }
        }
    }

    /**
     * @param uri
     * @param booleanList
     * @param superLiveHost
     * @return
     */
    public static WebClient.RequestBodySpec buildAPIPathForHttpPost(
            final String uri,
            final List<Boolean> booleanList,
            final String superLiveHost) {

        if (booleanList.get(0)) {
            return buildHttpPostURI(superLiveHost,
                    String.format("%s/%s", ConfigurationProperties.ApiPath.HOST.pathName, uri));
        } else if (booleanList.get(1)) {
            return buildHttpPostURI(superLiveHost,
                    String.format("%s/%s", ConfigurationProperties.ApiPath.PARTICIPANT.pathName, uri));
        } else if (booleanList.get(2)) {
            return buildHttpPostURI(superLiveHost,
                    String.format("%s/%s", ConfigurationProperties.ApiPath.GIFT.pathName, uri));
        } else if (booleanList.get(3)) {
            return buildHttpPostURI(superLiveHost,
                    String.format("%s/%s", ConfigurationProperties.ApiPath.STICKER.pathName, uri));
        } else {
            if (Pattern.compile("((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)")
                    .matcher(uri)
                    .matches()) {

                return buildHttpPostURI(SDKWebClientBuilder.buildFQDN(uri));

            } else {
                return buildHttpPostURI(superLiveHost, uri);
            }
        }
    }

    public static WebClient.RequestBodySpec buildAPIPathForHttpPut(
            final String uri,
            final String id,
            final List<Boolean> booleanList,
            final String superLiveHost) {

        if (booleanList.get(0)) {
            return buildHttpPutURI(superLiveHost, id,
                    String.format("%s/%s", ConfigurationProperties.ApiPath.HOST.pathName, uri));
        } else if (booleanList.get(1)) {
            return buildHttpPutURI(superLiveHost, id,
                    String.format("%s/%s", ConfigurationProperties.ApiPath.PARTICIPANT.pathName, uri));
        } else if (booleanList.get(2)) {
            return buildHttpPutURI(superLiveHost, id,
                    String.format("%s/%s", ConfigurationProperties.ApiPath.GIFT.pathName, uri));
        } else if (booleanList.get(3)) {
            return buildHttpPutURI(superLiveHost, id,
                    String.format("%s/%s", ConfigurationProperties.ApiPath.STICKER.pathName, uri));
        } else {
            if (Pattern.compile("((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)")
                    .matcher(uri)
                    .matches()) {

                return buildHttpPutURI(id, SDKWebClientBuilder.buildFQDN(uri));

            } else {
                return buildHttpPutURI(superLiveHost, id, uri);
            }
        }
    }


}
