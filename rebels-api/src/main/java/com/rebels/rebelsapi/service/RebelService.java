package com.rebels.rebelsapi.service;

import com.rebels.rebelsapi.document.Rebel;
import com.rebels.rebelsapi.dto.rebel.RebelRequest;
import com.rebels.rebelsapi.repository.RebelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RebelService {

    private final RebelRepository repository;

    public Mono<Rebel> createRebel(RebelRequest request){
        System.out.printf("Request: " + request);
        final var rebel = Rebel.fromRequest(request);
        System.out.printf("Rebel: " + rebel);
        return repository.save(rebel);
    }

    public Flux<Rebel> listAllRebels() {
        return repository.findAll();
    }

    public Mono<Rebel> getRebelById(String id){
        return repository.findById(id);
    }

    public Mono<Rebel> deleteById(String id){
        final var deletedRebel = repository.findById(id);
        repository.deleteById(id);
        return deletedRebel;
    }
}
