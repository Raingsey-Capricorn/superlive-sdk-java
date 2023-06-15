package com.stream.wrs.sdk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Author  : pisethraringsey.suon
 * Email   : pisethraingsey@dr-tech.com
 * Date    : 12/6/23
 * Project : superlive-sdk-java
 */
@SpringBootApplication
public class StreamSDKApplication extends SpringBootServletInitializer {

    /**
     * @param strings
     */
    public static void main(String... strings) {
        SpringApplication.run(StreamSDKApplication.class, strings);
    }

}
