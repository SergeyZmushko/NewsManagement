package com.mjc.school.config;

import com.mjc.school.versioning.ApiVersionRequestMappingHandlerMapping;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@org.springframework.context.annotation.Configuration
public class WebConfig implements WebMvcRegistrations {

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        ApiVersionRequestMappingHandlerMapping handlerMapping = new ApiVersionRequestMappingHandlerMapping();
        handlerMapping.setOrder(0);
        handlerMapping.setRemoveSemicolonContent(false);
        return handlerMapping;
    }


}
