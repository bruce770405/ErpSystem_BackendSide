package com.bruce.erpapp.config;

import com.bruce.erpapp.common.utils.JsonUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringWebConfiguration implements WebMvcConfigurer {

    /**
     * 設定允許跨網域可以存取.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowCredentials(true).allowedOrigins("*").allowedMethods("POST", "GET");
    }


    /**
     * 設定允許存取一般的資源
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**.html", "/images/**").addResourceLocations("/");
    }

    /**
     * 設定JSON資料在進入時的物件轉換Mapping規則.
     *
     * @return convert bean
     */
    @Bean(name = "MappingJackson2HttpMessageConverter")
    public MappingJackson2HttpMessageConverter converter() {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(JsonUtils.getObjectMapper());
        return converter;
    }
}
