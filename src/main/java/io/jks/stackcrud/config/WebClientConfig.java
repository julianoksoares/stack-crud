package io.jks.stackcrud.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean
    public WebClient webClient(){
        return WebClient.builder().build();
    }

}
