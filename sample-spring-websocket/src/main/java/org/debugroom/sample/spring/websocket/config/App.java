package org.debugroom.sample.spring.websocket.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import(WebSocketConfig.class)
@ComponentScan("org.debugroom.sample.spring.websocket.app.web")
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
