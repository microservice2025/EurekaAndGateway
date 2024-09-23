package org.gateway.controller;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class TimerFilter extends AbstractGatewayFilterFactory<TimerFilter.Config> {
    public TimerFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            LocalTime now = LocalTime.now();
            LocalTime startTime = LocalTime.of(12, 0);
            LocalTime endTime = LocalTime.of(13, 0);

            // If the current time is between 12  and 13, reject the request Forbidden (403)
            if (now.isAfter(startTime) && now.isBefore(endTime)) {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }

            // If the time is outside, proceed with the request
            return chain.filter(exchange);
        };
    }

    // Static configuration class
    public static class Config {

    }
}
