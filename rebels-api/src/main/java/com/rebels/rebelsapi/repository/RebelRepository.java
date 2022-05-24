package com.rebels.rebelsapi.repository;

import com.rebels.rebelsapi.document.Rebel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RebelRepository extends ReactiveCrudRepository<Rebel, String> {
}
