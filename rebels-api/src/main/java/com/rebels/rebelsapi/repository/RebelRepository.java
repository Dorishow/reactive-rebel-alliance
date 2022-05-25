package com.rebels.rebelsapi.repository;

import com.rebels.rebelsapi.document.Rebel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface RebelRepository extends ReactiveCrudRepository<Rebel, String> {
    Flux<Rebel> findAllByIsTraitor(Boolean isTraitor);
}
