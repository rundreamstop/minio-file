package com.rundreamstop.minio.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

/**
 * 文件服务
 *
 * @author zhangzihaopk@gmail.com
 */
@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RunMinioFileApplication {
    public static void main(String[] args) {
        try {
            System.setProperty("spring.devtools.restart.enabled", "false");
            ConfigurableApplicationContext ctx = SpringApplication.run(RunMinioFileApplication.class, args);
            String host = InetAddress.getLocalHost().getHostAddress();
            Environment environment = ctx.getBean(Environment.class);
            String port = environment.getProperty("local.server.port");

            String url = "http://" + host + ":" + port;
            log.info("--------------启动成功-------------   " + url);

            log.info("--------------启动项目环境---------   " + environment.getProperty("spring.profiles.active"));
            log.info("--------------启动项目端口---------   " + port);
            log.info("--------------启动项目名字---------   " + environment.getProperty("spring.application.name"));

        } catch (Exception e) {
            log.error("--------------启动失败-------------");
            log.error("--------------启动失败-------------", e);
            e.printStackTrace();
        }
    }
}
