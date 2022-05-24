package com.rebels.rebelsapi.controller;

import com.rebels.rebelsapi.document.Rebel;
import com.rebels.rebelsapi.dto.rebel.RebelRequest;
import com.rebels.rebelsapi.service.RebelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/rebel")
@RequiredArgsConstructor
public class RebelController {

    private final RebelService service;

    @GetMapping
    public Flux<Rebel> listAll(){
        return service.listAllRebels();
    }

    @GetMapping("/{id}")
    public Mono<Rebel> getById(@PathVariable String id){
        return service.getRebelById(id);
    }

    @PostMapping
    public Mono<Rebel> create(@RequestBody RebelRequest request){
        return service.createRebel(request);
    }

    @PostMapping("/report")
    public Mono<Rebel> report(@RequestBody String id){
        return service.reportRebel(id);
    }
}
