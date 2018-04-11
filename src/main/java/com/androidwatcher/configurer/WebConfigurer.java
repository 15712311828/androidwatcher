package com.androidwatcher.configurer;


import com.androidwatcher.Interceptor.DeviceLoginInterceptor;
import com.androidwatcher.Interceptor.UserLoginInterceptor;
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

    @Bean
    public UserLoginInterceptor userLoginInterceptor() {
        return new UserLoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(deviceLoginInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(userLoginInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
