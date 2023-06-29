package com.stream.wrs.sdk.utilities;

import com.stream.wrs.sdk.config.ConfigurableProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
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
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.regex.Pattern;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 16/6/23
 * Project : superlive-sdk-java
 */
@Log
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class SDKWebClientBuilder {
    public static final Integer FQDN = 1;
    public static final Integer PATH = 2;
    private static final String FQDN_PATTERN = "^((http|https)://).(((\\d{0,3}).(\\d{0,3}).(\\d{0,3}).(\\d{0,3}):\\d{1,10}/api/)|([a-z.]*/api/))";

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
    public static Function<UriBuilder, URI> buildHttpGetURI(Optional<String> uri, Optional<HashMap<String, ?>> optionalQueryParams) {
        return uriBuilder -> {
            if (uri.isPresent() && !uri.get().isEmpty())
                uriBuilder.path(uri.get());
            optionalQueryParams.ifPresent(stringHashMap ->
                    stringHashMap.forEach((key, value) -> uriBuilder.queryParam(key, Optional.of(value).get()))
            );
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
    public static MultiValueMap buildRequestBody(Map dataMap) {

        dataMap.entrySet()
                .stream()
                .filter(n -> !(((Map.Entry) n).getValue() instanceof List))
                .forEach(e -> {
                    Map.Entry entry = ((Map.Entry<String, ?>) e);
                    dataMap.replace(
                            entry.getKey(),
                            entry.getValue(),
                            Collections.singletonList(entry.getValue()));
                });
        LinkedMultiValueMap valueMap = new LinkedMultiValueMap();
        valueMap.putAll(dataMap);
        return valueMap;
    }

    /**
     * @param url
     * @return
     */
    @SneakyThrows
    public static Map<Integer, String> buildFQDN(String url) {
        URL fqdn = new URL(url);
        HashMap<Integer, String> map = new HashMap();
        map.put(FQDN, String.format("%s://%s%s", fqdn.getProtocol(), fqdn.getHost(), fqdn.getPort() == -1 ? "" : ":" + fqdn.getPort()));
        map.put(PATH, String.format("%s", fqdn.getPath()));
        return map;
    }

    /**
     * @param fqdn
     * @param uriBuilderFunction
     * @return
     */
    private static WebClient.RequestHeadersSpec<?> buildHttpGetURI(
            final Map<Integer, String> fqdn,
            final Optional<Function<UriBuilder, URI>> uriBuilderFunction) {

        return WebClient.builder()
                .baseUrl(fqdn.get(SDKWebClientBuilder.FQDN))
                .filters(exchangeRequestFilters())
                .build()
                .get()
                .uri(uriBuilder -> uriBuilderFunction.isPresent() ?
                        uriBuilderFunction.get().apply(uriBuilder.path(fqdn.get(SDKWebClientBuilder.PATH))) :
                        uriBuilder.path(fqdn.get(SDKWebClientBuilder.PATH)).build()
                );
    }

    /**
     * @param fqdn
     * @param path
     * @return
     */
    private static WebClient.RequestHeadersSpec<?> buildHttpGetURI(
            final String fqdn,
            final String path,
            final Optional<Function<UriBuilder, URI>> uriBuilderFunction) {

        return WebClient.builder()
                .baseUrl(fqdn)
                .filters(exchangeRequestFilters())
                .build()
                .get()
                .uri(uriBuilder -> uriBuilderFunction.isPresent() ?
                        uriBuilderFunction.get().apply(uriBuilder.path(path)) :
                        uriBuilder.path(path).build()
                );
    }

    /**
     * @param fqdn
     * @param path
     * @return
     */
    private static WebClient.RequestBodySpec buildHttpPostURI(
            final String fqdn,
            final String path) {

        return WebClient.builder()
                .baseUrl(fqdn)
                .filters(exchangeRequestFilters())
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder.path(path).build());
    }

    /**
     * @param fqdn
     * @return
     */
    public static WebClient.RequestBodySpec buildHttpPostURI(
            final Map<Integer, String> fqdn) {

        return WebClient.builder()
                .baseUrl(fqdn.get(SDKWebClientBuilder.FQDN))
                .filters(exchangeRequestFilters())
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(fqdn.get(SDKWebClientBuilder.PATH))
                        .build()
                );
    }

    /**
     * @param id
     * @param fqdn
     * @return
     */

    private static WebClient.RequestBodySpec buildHttpPutURI(
            final String id,
            final Map<Integer, String> fqdn) {

        return WebClient.builder()
                .baseUrl(fqdn.get(SDKWebClientBuilder.FQDN))
                .filters(exchangeRequestFilters())
                .build()
                .put()
                .uri(uriBuilder -> uriBuilder
                        .path(fqdn.get(SDKWebClientBuilder.PATH))
                        .build(id)
                );
    }

    /**
     * @param superliveHost
     * @param id
     * @param path
     * @return
     */
    private static WebClient.RequestBodySpec buildHttpPutURI(
            final String superliveHost,
            final String id,
            final String path) {

        return WebClient.builder()
                .baseUrl(superliveHost)
                .filters(exchangeRequestFilters())
                .build()
                .put()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .build(id)
                );
    }

    /**
     * @param superliveHost
     * @param id
     * @param path
     * @return
     */
    private static WebClient.RequestHeadersSpec<?> buildHttpDeleteURI(
            final String superliveHost,
            final String id,
            final String path) {

        return WebClient.builder()
                .baseUrl(superliveHost)
                .filters(exchangeRequestFilters())
                .build()
                .delete()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .build(id)
                );
    }

    /**
     * @param id
     * @param fqdn
     * @return
     */
    private static WebClient.RequestHeadersSpec<?> buildHttpDeleteURI(
            final String id,
            final Map<Integer, String> fqdn) {

        return WebClient.builder()
                .baseUrl(fqdn.get(SDKWebClientBuilder.FQDN))
                .filters(exchangeRequestFilters())
                .build()
                .delete()
                .uri(uriBuilder -> uriBuilder
                        .path(fqdn.get(SDKWebClientBuilder.PATH))
                        .build(id)
                );
    }


    /**
     * @param uri
     * @param uriBuilderFunction
     * @param booleanList
     * @param superLiveHost
     * @return
     */
    public static WebClient.RequestHeadersSpec<?> buildAPIPathForHttpGet(
            final Optional<String> uri,
            final Optional<Function<UriBuilder, URI>> uriBuilderFunction,
            final List<Boolean> booleanList,
            final String superLiveHost) {

        if (Boolean.TRUE.equals(booleanList.get(0)))
            return buildHttpGetURI(superLiveHost, simplifyURI(uri, ConfigurableProperties.ApiPath.HOST), uriBuilderFunction);
        else if (Boolean.TRUE.equals(booleanList.get(1)))
            return buildHttpGetURI(superLiveHost, simplifyURI(uri, ConfigurableProperties.ApiPath.PARTICIPANT), uriBuilderFunction);
        else if (Boolean.TRUE.equals(booleanList.get(2)))
            return buildHttpGetURI(superLiveHost, simplifyURI(uri, ConfigurableProperties.ApiPath.GIFT), uriBuilderFunction);
        else if (Boolean.TRUE.equals(booleanList.get(3)))
            return buildHttpGetURI(superLiveHost, simplifyURI(uri, ConfigurableProperties.ApiPath.STICKER), uriBuilderFunction);
        else if (Boolean.TRUE.equals(booleanList.get(4)))
            return buildHttpGetURI(superLiveHost, simplifyURI(uri, ConfigurableProperties.ApiPath.UPLOAD), uriBuilderFunction);
        else return uri.map(u -> Pattern.compile(FQDN_PATTERN).matcher(u).find() ?
                            buildHttpGetURI(SDKWebClientBuilder.buildFQDN(u), uriBuilderFunction) :
                            buildHttpGetURI(superLiveHost, u, uriBuilderFunction))
                    .orElse(null);
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

        if (Boolean.TRUE.equals(booleanList.get(0)))
            return buildHttpPostURI(superLiveHost, simplifyURI(Optional.ofNullable(uri), ConfigurableProperties.ApiPath.HOST));
        else if (Boolean.TRUE.equals(booleanList.get(1)))
            return buildHttpPostURI(superLiveHost, simplifyURI(Optional.ofNullable(uri), ConfigurableProperties.ApiPath.PARTICIPANT));
        else if (Boolean.TRUE.equals(booleanList.get(2)))
            return buildHttpPostURI(superLiveHost, simplifyURI(Optional.ofNullable(uri), ConfigurableProperties.ApiPath.GIFT));
        else if (Boolean.TRUE.equals(booleanList.get(3)))
            return buildHttpPostURI(superLiveHost, simplifyURI(Optional.ofNullable(uri), ConfigurableProperties.ApiPath.STICKER));
        else if (Boolean.TRUE.equals(booleanList.get(4)))
            return buildHttpPostURI(superLiveHost, simplifyURI(Optional.ofNullable(uri), ConfigurableProperties.ApiPath.UPLOAD));
        else
            return Pattern.compile(FQDN_PATTERN).matcher(uri).find() ?
                    buildHttpPostURI(SDKWebClientBuilder.buildFQDN(uri)) :
                    buildHttpPostURI(superLiveHost, uri);
    }

    /**
     * @param uri
     * @param id
     * @param booleanList
     * @param superLiveHost
     * @return
     */
    public static WebClient.RequestBodySpec buildAPIPathForHttpPut(
            final String uri,
            final String id,
            final List<Boolean> booleanList,
            final String superLiveHost) {

        if (Boolean.TRUE.equals(booleanList.get(0)))
            return buildHttpPutURI(superLiveHost, id, simplifyURI(Optional.ofNullable(uri), ConfigurableProperties.ApiPath.HOST));
        else if (Boolean.TRUE.equals(booleanList.get(1)))
            return buildHttpPutURI(superLiveHost, id, simplifyURI(Optional.ofNullable(uri), ConfigurableProperties.ApiPath.PARTICIPANT));
        else if (Boolean.TRUE.equals(booleanList.get(2)))
            return buildHttpPutURI(superLiveHost, id, simplifyURI(Optional.ofNullable(uri), ConfigurableProperties.ApiPath.GIFT));
        else if (Boolean.TRUE.equals(booleanList.get(3)))
            return buildHttpPutURI(superLiveHost, id, simplifyURI(Optional.ofNullable(uri), ConfigurableProperties.ApiPath.STICKER));
        else if (Boolean.TRUE.equals(booleanList.get(4)))
            return buildHttpPutURI(superLiveHost, id, simplifyURI(Optional.ofNullable(uri), ConfigurableProperties.ApiPath.UPLOAD));
        else
            return Pattern.compile(FQDN_PATTERN).matcher(uri).find() ?
                    buildHttpPutURI(id, SDKWebClientBuilder.buildFQDN(uri)) :
                    buildHttpPutURI(superLiveHost, id, uri);
    }

    /**
     * @param uri
     * @param id
     * @param booleanList
     * @param superLiveHost
     * @return
     */
    public static WebClient.RequestHeadersSpec<?> buildAPIPathForHttpDelete(
            final String uri,
            final String id,
            final List<Boolean> booleanList,
            final String superLiveHost) {

        if (Boolean.TRUE.equals(booleanList.get(0)))
            return buildHttpDeleteURI(superLiveHost, id, simplifyURI(Optional.ofNullable(uri), ConfigurableProperties.ApiPath.HOST));
        else if (Boolean.TRUE.equals(booleanList.get(1)))
            return buildHttpDeleteURI(superLiveHost, id, simplifyURI(Optional.ofNullable(uri), ConfigurableProperties.ApiPath.PARTICIPANT));
        else if (Boolean.TRUE.equals(booleanList.get(2)))
            return buildHttpDeleteURI(superLiveHost, id, simplifyURI(Optional.ofNullable(uri), ConfigurableProperties.ApiPath.GIFT));
        else if (Boolean.TRUE.equals(booleanList.get(3)))
            return buildHttpDeleteURI(superLiveHost, id, simplifyURI(Optional.ofNullable(uri), ConfigurableProperties.ApiPath.STICKER));
        else if (Boolean.TRUE.equals(booleanList.get(4)))
            return buildHttpDeleteURI(superLiveHost, id, simplifyURI(Optional.ofNullable(uri), ConfigurableProperties.ApiPath.UPLOAD));
        else
            return Pattern.compile(FQDN_PATTERN).matcher(uri).find() ?
                    buildHttpDeleteURI(id, SDKWebClientBuilder.buildFQDN(uri)) :
                    buildHttpDeleteURI(superLiveHost, id, uri);
    }


    /**
     * @param uri
     * @param apiPath
     * @return
     */
    @SneakyThrows
    private static String simplifyURI(final Optional<String> uri, ConfigurableProperties.ApiPath apiPath) {

        return uri.map(u -> u.contains(apiPath.pathName) ?
                String.format("%s", uri.get()) :
                String.format("%s/%s", apiPath.pathName, uri.get())
        ).orElse(null);
    }

    /**
     * @return
     */
    public static Consumer<List<ExchangeFilterFunction>> exchangeRequestFilters() {
        return exchangeFilterFunctions ->
                exchangeFilterFunctions.add((clientRequest, next) -> {
                    clientRequest.headers().forEach((name, values) -> log.log(Level.INFO, String.format("%s : %s", name, values)));
                    log.log(Level.INFO, String.format("Request URL : %s", clientRequest.url()));
                    return next.exchange(clientRequest);
                });
    }

    /**
     * @return
     */
    public static Consumer<HashMap> logMessageOnSuccess() {
        return map -> {
            log.log(Level.INFO, String.format("Response status : %s", map.get("statusCode")));
            log.log(Level.INFO, String.format("Response data : %s", map.get("data")));
        };
    }

}
