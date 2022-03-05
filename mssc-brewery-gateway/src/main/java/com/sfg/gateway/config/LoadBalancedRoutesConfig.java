package com.sfg.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local-discovery")
@Configuration
public class LoadBalancedRoutesConfig {

        @Bean
        public RouteLocator loadBalancedRoutes(RouteLocatorBuilder builder) {
            System.out.println("Local-discovery profile applied");
            return builder.routes()
                    .route(r -> r.path("/api/v1/beer/*", "/api/v1/beer*", "/api/v1/beerUpc/*")
                            .uri("lb://beer-service")
                            .id("beer-service"))
                    .route(r -> r.path("/api/v1/customers/**")
                            .uri("lb://order-service")
                            .id("order-service"))
                    .route(r -> r.path("/api/v1/beer/*/inventory")
                            .uri("lb://inventory-service")
                            .id("inventory-service"))
                    .route(r -> r.path("/api/v1/inventory-failover/**")
                            .filters(f -> f.circuitBreaker(c -> c.setName("inventoryCB")
                                    .setFallbackUri("forward://inventory-failover")
                                    .setRouteId("inv-failover")))
                            .uri("lb://inventory-failover")
                            .id("inventory-failover-service"))
                    .build();

    }

}