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
     * This method is strickly
     * used with SDKClient.builder()
     * <br/><br/><strong>Usage</strong>:
     * <code>
     * <br/>- SDKClient<br/>.builder()...<br/>.build()<br/>.doGetRequest(String requestURI)
     * <br/>
     * <br/>- SDKClient<br/>.builder()<br/>.buildXXXXXAPI()...<br/>.doGetRequest(String requestURI)
     * </code>
     * <br/>
     *
     * @param requestURI : the requestURI
     * @return HashMap<?, ?> content result content as Json :
     * <br/>{
     * "statusCode": xxx,
     * "data": {...}
     * }
     */
    HashMap<?, ?> doGetRequest(final String requestURI);

    /**
     * This method is strickly
     * used with SDKClient.builder()
     * <br/><br/><strong>Usage</strong>:
     * <code>
     * <br/>- SDKClient<br/>.builder()...<br/>.build()<br/>.doGetRequest(final String requestURI, final Function<UriBuilder, URI> uriBuilderFunction)
     * <br/>
     * <br/>- SDKClient<br/>.builder()<br/>.buildXXXXXAPI()...<br/>.doGetRequest(final String requestURI, final Function<UriBuilder, URI> uriBuilderFunction)
     * </code>
     * <br/>
     *
     * @param requestURI         : the uri appending to the APi's uri
     * @param uriBuilderFunction :fully customized uri
     * @return HashMap<?, ?> content result content as Json :
     * <br/>{
     * "statusCode": xxx,
     * "data": {...}
     * }
     * @see Function<UriBuilder, URI>
     */
    HashMap<?, ?> doGetRequest(final String requestURI, final Function<UriBuilder, URI> uriBuilderFunction);

    /**
     * @param sdkClient
     * @param requestURI
     * @return
     */
    HashMap<?, ?> doGetRequest(final SDKClient sdkClient, final String requestURI);

    /**
     * @param sdkClient
     * @param uriBuilderFunction
     * @return
     */
    HashMap<?, ?> doGetRequest(final SDKClient sdkClient,
                               final Function<UriBuilder, URI> uriBuilderFunction);

    /**
     * @param sdkClient
     * @param baseURL
     * @param requestDataMap
     * @return
     */
    HashMap<?, ?> doPostRequest(final SDKClient sdkClient,
                                final String baseURL,
                                final HashMap requestDataMap);

    /**
     * This method is strickly
     * used with SDKClient.builder()
     * <br/><br/><strong>Usage</strong>:
     * <code>
     * <br/>- SDKClient<br/>.builder()...<br/>.build()<br/>.doPostRequest(String requestURI)
     * <br/>
     * <br/>- SDKClient<br/>.builder()<br/>.buildXXXXXAPI()...<br/>.doPostRequest(String requestURI)
     * </code>
     * <br/>
     *
     * @param requestURI     : the uri appending to the APi's uri
     * @param requestDataMap : the request body represented as HashMap to post
     *                       <br/>{
     *                       "statusCode": xxx,
     *                       "data": {...}
     *                       }
     */
    HashMap<?, ?> doPostRequest(final String requestURI,
                                final HashMap requestDataMap);

    /**
     * @param sdkClient
     * @param requestURI
     * @param id
     * @param requestDataMap
     * @return
     */
    HashMap<?, ?> doPutRequest(final SDKClient sdkClient,
                               final String requestURI,
                               final String id,
                               final HashMap requestDataMap);


    /**
     * This method is strickly
     * used with SDKClient.builder()
     * <br/><br/><strong>Usage</strong>:
     * <code>
     * <br/>- SDKClient<br/>.builder()...<br/>.build()<br/>.doPutRequest(final String requestURI, final String id, final HashMap requestDataMap)
     * <br/>
     * <br/>- SDKClient<br/>.builder()<br/>.buildXXXXXAPI()...<br/>.doPutRequest(final String requestURI, final String id, final HashMap requestDataMap)
     * </code>
     * <br/>
     *
     * @param requestURI     : the uri appending to the APi's uri
     * @param id             : the id value to delete through this uri (.../{id})
     * @param requestDataMap : the request body represented as HashMap
     * @return HashMap<?, ?> content result content as Json :
     * <br/>{
     * "statusCode": xxx,
     * "data": {...}
     * }
     */
    HashMap<?, ?> doPutRequest(final String requestURI,
                               final String id,
                               final HashMap requestDataMap);

    /**
     * @param sdkClient
     * @param requestURI
     * @return
     */
    HashMap<?, ?> doDeleteRequest(final SDKClient sdkClient,
                                  final String requestURI,
                                  final String id);

    /**
     * This method is strickly
     * used with SDKClient.builder()
     * <br/><br/><strong>Usage</strong>:
     * <code>
     * <br/>- SDKClient<br/>.builder()...<br/>.build()<br/>.doDeleteRequest(final String requestURI, final String id)
     * <br/>
     * <br/>- SDKClient<br/>.builder()<br/>.buildXXXXXAPI()...<br/>.doDeleteRequest(final String requestURI, final String id))
     * </code>
     * <br/>
     *
     * @param requestURI : the uri appending to the APi's uri
     * @param id         : the id value to delete through this uri (.../{id})
     * @return HashMap<?, ?> content result content as Json :
     * <br/>{
     * "statusCode": xxx,
     * "data": {...}
     * }
     */
    HashMap<?, ?> doDeleteRequest(final String requestURI,
                                  final String id);
}
