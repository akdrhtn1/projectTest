package com.todotogether.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource("classpath:application-real.properties"),
      //  @PropertySource("classpath:application-real-db.properties"),
        @PropertySource("classpath:application-s3.properties"),
        @PropertySource("classpath:email.properties")
})
public class PropertiesWithJavaConfig {

}
