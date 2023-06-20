package com.stream.wrs.sdk.utilities;

import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.function.Function;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 16/6/23
 * Project : superlive-sdk-java
 */
public interface SDKWebClientAction {

    /**
     * @param sdkClient
     * @param requestURI
     * @return
     */
    HashMap<?, ?> doGetRequest(SDKClient sdkClient, String requestURI);

    /**
     * @param requestURI
     * @return
     */
    HashMap<?, ?> doGetRequest(String requestURI);

    /**
     * @param sdkClient
     * @param uriBuilderFunction
     * @return
     */
    HashMap<?, ?> doGetRequest(SDKClient sdkClient, Function<UriBuilder, URI> uriBuilderFunction);

    /**
     * @param requestURI
     * @param uriBuilderFunction
     * @return
     */
    HashMap<?, ?> doGetRequest(String requestURI, Function<UriBuilder, URI> uriBuilderFunction);

    /**
     * @param sdkClient
     * @param requestDataMap
     * @return
     */
    HashMap<?, ?> doPostRequest(final SDKClient sdkClient,
                                final String baseURL,
                                final HashMap requestDataMap);

    /**
     * @param requestURI
     * @param requestDataMap
     * @return
     */
    HashMap<?, ?> doPostRequest(final String requestURI,
                                final HashMap requestDataMap);

    /**
     * @param sdkClient
     * @param requestURI
     * @return
     */
    HashMap<?, ?> doPutRequest(SDKClient sdkClient, URI requestURI);

    /**
     * @param requestURI
     * @param id
     * @param requestDataMap
     * @return
     */
    HashMap<?, ?> doPutRequest(final String requestURI,
                               final String id,
                               final HashMap requestDataMap);

    /**
     * @param sdkClient
     * @param requestURI
     * @return
     */
    HashMap<?, ?> doDeleteRequest(SDKClient sdkClient, URI requestURI);
}
