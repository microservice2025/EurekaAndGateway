package org.gateway;

import org.gateway.controller.TimerFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder b) {
        return b.routes()
            .route("AccountService",
                p -> p.path("/api/v1/balance/**")
                .filters(f -> f.rewritePath("/balance/(?<segment>.*)", "/${segment}")
                     .filters(new TimerFilter().apply(new TimerFilter.Config() ))  // Apply the TimerFilter
                    .addResponseHeader("X-response-timestamp", LocalDateTime.now().toString())
                    .addResponseHeader("X-response-by", "Senglorn")
                )
                .uri("lb://ACCOUNTSERVICE"))


            .route("LoanService",
                p -> p.path("/api/v1/loan/**")
                .filters(f -> f.rewritePath("/loan/(?<segment>.*)", "/${segment}"))
                .uri("lb://LOANSERVICE"))



            .build();
    }

    // Create the WebClient Bean with Load Balancing
    @Bean
    @LoadBalanced
    public WebClient.Builder webBuilder() {

        return WebClient.builder();
    }

}
