package com.stream.wrs.sdk.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 14/6/23
 * Project : superlive-sdk-java
 */
@Component
public class FileValueProvider<T> {

    /**
     * Load file and return with preferred type
     * Due to Java Map key-value type restriction,
     * -- every data type of value which is non-list type
     * -- will be cast to Collections.singletonList
     * -- so that the data can be sent using POST,PUT, and PATCH
     * -- using BodyInserters#fromFormData(MultiValueMap)
     *
     * @param fileName : full path file from either main resources or test resources
     * @return LinkedMultiValueMap<String, Class < ? extends Object>>
     * @see Collections#singletonList(Object)
     * @see org.springframework.web.reactive.function.BodyInserters#fromFormData(MultiValueMap)
     * @see org.springframework.http.HttpMethod#POST
     * @see org.springframework.http.HttpMethod#PUT
     * @see org.springframework.http.HttpMethod#PATCH
     */
    @SneakyThrows
    public LinkedMultiValueMap<String, Class<? extends Object>> readJsonRequestBody(final String fileName) {

        /**
         * Load the json file and cast it to HashMap (key-value)
         */
        HashMap map = new ObjectMapper().readValue(
                Paths.get(fileName).toFile(),
                HashMap.class
        );

        /**
         * Convert non-list value for certain key to singletonList
         */
        map.entrySet()
                .stream()
                .filter(n -> (((Map.Entry) n).getValue() instanceof List) == false)
                .forEach(e -> {
                    Map.Entry entry = ((Map.Entry<String, ?>) e);
                    map.replace(
                            entry.getKey(),
                            entry.getValue(),
                            Collections.singletonList(entry.getValue()));
                });
        /**
         * Return preferred request body for processing
         */
        return new LinkedMultiValueMap<String, Class<? extends Object>>() {{
            putAll(map);
        }};
    }

}
