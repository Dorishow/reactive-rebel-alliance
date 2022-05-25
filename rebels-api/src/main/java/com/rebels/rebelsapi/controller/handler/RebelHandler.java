package com.rebels.rebelsapi.controller.handler;

import com.rebels.rebelsapi.document.Rebel;
import com.rebels.rebelsapi.dto.rebel.RebelRequest;
import com.rebels.rebelsapi.models.Local;
import com.rebels.rebelsapi.service.RebelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RebelHandler {

    private final RebelService service;

    public Mono<ServerResponse> listAll(ServerRequest request){
        return ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.listAllRebels(), Rebel.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request){
        String id = request.pathVariable("id");
        return ServerResponse.status(HttpStatus.FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getRebelById(id), Rebel.class);
    }

    public Mono<ServerResponse> createRebel(ServerRequest request){
        final Mono<RebelRequest> rebelRequestMono = request.bodyToMono(RebelRequest.class);
        return ServerResponse
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(rebelRequestMono.flatMap(service::createRebel), Rebel.class));
    }

    public Mono<ServerResponse> reportRebel(ServerRequest request){
        final String id = request.pathVariable("id");
        return ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.reportRebel(id), Rebel.class);
    }
}
