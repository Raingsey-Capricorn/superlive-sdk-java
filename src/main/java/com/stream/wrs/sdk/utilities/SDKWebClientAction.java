package com.stream.wrs.sdk.utilities;

import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 16/6/23
 * Project : superlive-sdk-java
 */
public interface SDKWebClientAction {

    /**
     * This method is strickly used with SDKClient.builder()
     * This methods support two type of URI, Fully Qualified Domain Name address (FQDN), or after <em>buildXxxAPI()</em> with URI's path as example below
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
     * This method is strickly used with SDKClient.builder()
     * This methods support two type of URI, Fully Qualified Domain Name address (FQDN), or after <em>buildXxxAPI()</em> with URI's path as example below
     * <br/><br/><strong>Usage</strong>:
     * <code>
     * <br/>- SDKClient<br/>.builder()...<br/>.build()<br/>.doGetRequest(final String requestURI, final Function<UriBuilder, URI> uriBuilderFunction)
     * <br/>
     * <br/>- SDKClient<br/>.builder()<br/>.buildXXXXXAPI()...<br/>.doGetRequest(final String requestURI, final Function<UriBuilder, URI> uriBuilderFunction)
     * </code>
     * <br/>
     *
     * @param requestURI         : the uri appending to the APi's uri
     * @param uriBuilderFunction :fully customized uri (path variable and query param capabilities)
     * @return HashMap<?, ?> content result content as Json :
     * <br/>{
     * "statusCode": xxx,
     * "data": {...}
     * }
     * @see SDKWebClientBuilder#buildHttpGetURI(String)
     * @see SDKWebClientBuilder#buildHttpGetURI(Optional, Object)
     * @see SDKWebClientBuilder#buildHttpGetURI(Optional, Optional)
     */
    HashMap<?, ?> doGetRequest(final String requestURI, final Function<UriBuilder, URI> uriBuilderFunction);

    /**
     * This method is strickly
     * used with SDKClient.builder()
     * This methods support two type of URI, Fully Qualified Domain Name address (FQDN), or after <em>buildXxxAPI()</em> with URI's path as example below
     * <br/><br/><strong>Usage</strong>:
     * <code>
     * <br/>- SDKClient<br/>.builder()<br/>.buildXXXXXAPI()...<br/>.doGetRequest(final String requestURI, final Function<UriBuilder, URI> uriBuilderFunction)
     * </code>
     * <br/>
     *
     * @param uriBuilderFunction :fully customized uri (path variable and query param capabilities)
     * @return HashMap<?, ?> content result content as Json :
     * <br/>{
     * "statusCode": xxx,
     * "data": {...}
     * }
     * @see SDKWebClientBuilder#buildHttpGetURI(String)
     * @see SDKWebClientBuilder#buildHttpGetURI(Optional, Object)
     * @see SDKWebClientBuilder#buildHttpGetURI(Optional, Optional)
     */
    HashMap<?, ?> doGetRequest(final Function<UriBuilder, URI> uriBuilderFunction);

    /**
     * @param sdkClient  : sdkClient's instance
     * @param requestURI : the uri appending to the APi's uri
     * @return HashMap<?, ?> content result content as Json :
     * <br/>{
     * "statusCode": xxx,
     * "data": {...}
     * }
     * @deprecated As of superlive-sdk-java 1.0-SNAPSHOT
     * because of using SDKClient instance separately,
     * It's recommended to apply with latest implementation as below {@link #doGetRequest(String)} instead.
     */
    @Deprecated
    HashMap<?, ?> doGetRequest(final SDKClient sdkClient, final String requestURI);

    /**
     * @param sdkClient          : sdkClient's instance
     * @param uriBuilderFunction :fully customized uri (path variable and query param capabilities)
     * @return HashMap<?, ?> content result content as Json :
     * <br/>{
     * "statusCode": xxx,
     * "data": {...}
     * }
     * @see SDKWebClientBuilder#buildHttpGetURI(String)
     * @see SDKWebClientBuilder#buildHttpGetURI(Optional, Object)
     * @see SDKWebClientBuilder#buildHttpGetURI(Optional, Optional)
     * @deprecated As of superlive-sdk-java 1.0-SNAPSHOT
     * because of using SDKClient instance separately,
     * It's recommended to apply with latest implementation as below {@link #doGetRequest(String, Function)} instead.
     */
    @Deprecated
    HashMap<?, ?> doGetRequest(final SDKClient sdkClient,
                               final Function<UriBuilder, URI> uriBuilderFunction);

    /**
     * @param sdkClient      : sdkClient's instance
     * @param baseURL        : the uri appending to the APi's uri
     * @param requestDataMap : the request body represented as HashMap to post
     * @return HashMap<?, ?> content result content as Json :
     * <br/>{
     * "statusCode": xxx,
     * "data": {...}
     * }
     * @deprecated As of superlive-sdk-java 1.0-SNAPSHOT
     * because of using SDKClient instance separately,
     * It's recommended to apply with latest implementation as below {@link #doPostRequest(String, Map)} instead.
     */
    @Deprecated
    HashMap<?, ?> doPostRequest(final SDKClient sdkClient,
                                final String baseURL,
                                final Map<?, ?> requestDataMap);

    /**
     * This method is strickly used with SDKClient.builder()
     * This methods support two type of URI, Fully Qualified Domain Name address (FQDN), or after <em>buildXxxAPI()</em> with URI's path as example below
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
     * @return HashMap<?, ?> content result content as Json :
     * <br/>{
     * "statusCode": xxx,
     * "data": {...}
     * }
     */
    HashMap<?, ?> doPostRequest(final String requestURI,
                                final Map<?, ?> requestDataMap);

    /**
     * @param sdkClient      : sdkClient's instance
     * @param requestURI     : the uri appending to the APi's uri
     * @param id             : the <strong>id</strong> value to update through this uri (.../{id})
     * @param requestDataMap : request body in the form of HasMap<String, Object>
     * @return HashMap<?, ?> content result content as Json :
     * <br/>{
     * "statusCode": xxx,
     * "data": {...}
     * }
     * @deprecated As of superlive-sdk-java 1.0-SNAPSHOT
     * because of using SDKClient instance separately,
     * It's recommended to apply with latest implementation as below {@link #doDeleteRequest(String, String)} instead.
     */
    @Deprecated
    HashMap<?, ?> doPutRequest(final SDKClient sdkClient,
                               final String requestURI,
                               final String id,
                               final Map<?, ?> requestDataMap);


    /**
     * This method is strickly
     * used with SDKClient.builder()
     * This methods support two type of URI, Fully Qualified Domain Name address (FQDN), or after <em>buildXxxAPI()</em> with URI's path as example below
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
                               final Map<?, ?> requestDataMap);

    /**
     * @param sdkClient  :
     * @param requestURI :
     * @return HashMap<?, ?> content result content as Json :
     * <br/>{
     * "statusCode": xxx,
     * "data": {...}
     * }
     * @deprecated As of superlive-sdk-java 1.0-SNAPSHOT
     * because of using SDKClient instance separately,
     * It's recommended to apply with latest implementation as below {@link #doDeleteRequest(String, String)} instead.
     */
    @Deprecated
    HashMap<?, ?> doDeleteRequest(final SDKClient sdkClient,
                                  final String requestURI,
                                  final String id);

    /**
     * This method is strictly used with SDKClient.builder()
     * This methods support two type of URI, Fully Qualified Domain Name address (FQDN), or after <em>buildXxxAPI()</em> with URI's path as example below
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
