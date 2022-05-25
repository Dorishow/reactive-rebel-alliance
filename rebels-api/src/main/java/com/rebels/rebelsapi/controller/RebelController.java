package com.rebels.rebelsapi.controller;

import com.rebels.rebelsapi.document.Rebel;
import com.rebels.rebelsapi.dto.rebel.RebelRequest;
import com.rebels.rebelsapi.models.Local;
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

    @GetMapping("/traitor")
    public Flux<Rebel> listAllTraitors(){
        return service.listAllTraitors();
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

    @PutMapping("/update-local/{id}")
    public Mono<Rebel> updateRebelLocal(@PathVariable String id, @RequestBody Local local) {
        return service.updateRebelLocal(id, local);
    }

    @DeleteMapping("/{id}")
    public Mono<Rebel> deleteRebel(@PathVariable String id){
        return service.deleteById(id);
    }
}
