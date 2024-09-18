package com.microserviceandgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroserviceAndGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceAndGatewayApplication.class, args);
    }

}
