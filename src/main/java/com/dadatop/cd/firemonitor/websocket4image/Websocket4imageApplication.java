package com.dadatop.cd.firemonitor.websocket4image;

import com.dadatop.cd.firemonitor.websocket4image.server.WebSocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.dadatop.cd.firemonitor.websocket4image.dao")
public class Websocket4imageApplication {

    public static void main(String[] args) {
        SpringApplication.run(Websocket4imageApplication.class, args);

//        SpringApplication springApplication = new SpringApplication(Websocket4imageApplication.class);
//        ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
//
//        WebSocketServer.setApplicationContext(configurableApplicationContext);
    }

}

