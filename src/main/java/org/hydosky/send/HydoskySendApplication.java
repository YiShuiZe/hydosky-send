package org.hydosky.send;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <h3>hydosky-send</h3>
 * <p>
 *     启动类
 * </p>
 * Created by yang on 19-11-27 下午12:23
 * updated by yang on 19-11-27 下午12:23
 */
@SpringBootApplication
@MapperScan("org.hydosky.send.**.mapper.**")
@EnableAsync
@EnableScheduling
public class HydoskySendApplication {
    public static void main(String[] args) {
        SpringApplication.run(HydoskySendApplication.class, args);
    }
}
