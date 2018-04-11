package com.androidwatcher.configurer;


import com.androidwatcher.Interceptor.DeviceLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfigurer extends WebMvcConfigurerAdapter {

    @Bean
    public DeviceLoginInterceptor deviceLoginInterceptor() {
        return new DeviceLoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(deviceLoginInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
