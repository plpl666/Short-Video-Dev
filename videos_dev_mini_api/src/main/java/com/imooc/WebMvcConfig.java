package com.imooc;




import com.imooc.controller.interceptor.MiniInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("file:D:/_videos_dev/");
    }

    

    //注册zk客户端
    @Bean(initMethod = "init")
    public ZKCuratorClient zkCuratorClient() {
        return new ZKCuratorClient();
    }

    //注册拦截器
    @Bean
    public MiniInterceptor miniInterceptor() {
        return new MiniInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(miniInterceptor()).addPathPatterns("/users/**").excludePathPatterns("/users/queryPublisher","/users/userLikeOrNot")
                                                  .addPathPatterns("/bgm/**")
                                                  .addPathPatterns("/videos/**").excludePathPatterns("/videos/hotWords","/videos/showVideos","/videos/getVideoComments","/videos/getVideoCommentCounts");
        super.addInterceptors(registry);
    }



}
