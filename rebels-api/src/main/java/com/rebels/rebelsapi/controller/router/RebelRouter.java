package com.rebels.rebelsapi.controller.router;

import com.rebels.rebelsapi.controller.handler.RebelHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RebelRouter {

    @Bean
    public RouterFunction<ServerResponse> router(RebelHandler handler){
        return route()
                .path("/functional/rebel", b1 -> b1
                        .nest(accept(APPLICATION_JSON), b2 -> b2
                                .GET("/{id}", handler::getById)
                                .GET("", handler::listAll)
                                .POST("/report/{id}", handler::reportRebel)
                                .POST("", handler::createRebel)))
                .build();
    }
}
