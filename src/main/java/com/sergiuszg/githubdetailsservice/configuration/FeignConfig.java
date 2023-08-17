package com.sergiuszg.githubdetailsservice.configuration;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FeignConfig {

    private final DefaultErrorDecoder defaultErrorDecoder;

    @Bean
    public RequestInterceptor requestTokenBearerInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization", "Bearer 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");
            requestTemplate.header("Accept", "application/vnd.github+json");
        };
    }
}
