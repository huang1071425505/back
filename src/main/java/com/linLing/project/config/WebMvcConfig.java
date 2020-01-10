package com.linLing.project.config;

import com.linLing.project.utils.TokenInterceptorUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig  extends WebMvcConfigurationSupport {
    private TokenInterceptorUtil tokenInterceptorUtil;

    public WebMvcConfig(TokenInterceptorUtil tokenInterceptorUtil) {
        this.tokenInterceptorUtil = tokenInterceptorUtil;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptorUtil).addPathPatterns("/sysUsers/*").excludePathPatterns("/sysUsers/logout");
        super.addInterceptors(registry);
    }

}
