package guru.sfg.brewery.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> route(InventoryHandler inventoryHandler) {
        return RouterFunctions
                .route(GET("/api/v1/inventory-failover")
                    .and(accept(MediaType.APPLICATION_JSON)),
                        inventoryHandler::handleInventoryFailover);
    }
}
