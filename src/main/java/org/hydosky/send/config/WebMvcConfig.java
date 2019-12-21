package org.hydosky.send.config;

import org.hydosky.send.config.interceptors.ResourceInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <h3>hydosky-send</h3>
 * <p>
 *     将拦截器加载到WebMvcConfigurer
 * </p>
 * Created by yang on 19-12-21 上午10:33
 * updated by yang on 19-12-21 上午10:33
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private ResourceInterceptor resourceInterceptor;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("执行InterceptorRegistry");
        registry.addInterceptor(resourceInterceptor);
    }
}
