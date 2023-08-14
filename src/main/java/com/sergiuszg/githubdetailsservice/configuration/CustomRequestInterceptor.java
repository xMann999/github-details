package com.sergiuszg.githubdetailsservice.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class CustomRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorisation", "Bearer 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");
        template.header("Accept", "application/vnd.github+json");
    }
}
