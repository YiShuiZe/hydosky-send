package org.hydosky.send.config.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h3>hydosky-send</h3>
 * <p>
 *     自定义拦截器
 *     打印来访者信息
 * </p>
 * Created by yang on 19-12-21 上午10:30
 * updated by yang on 19-12-21 上午10:30
 */
@Component
public class ResourceInterceptor extends HandlerInterceptorAdapter {
    private static final String USER_AGENT = "user-agent";
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(logger.isInfoEnabled()){
            String uri = request.getRequestURI();
            logger.info("【访问信息】用户访问地址: {}, 来路地址: {}, UserAgent: {}", uri, getIpAddr(request), request.getHeader(USER_AGENT));
        }
        return true;
    }
    public  String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        if (ip.split(",").length > 1) {
            ip = ip.split(",")[0];
        }
        return ip;
    }
}
