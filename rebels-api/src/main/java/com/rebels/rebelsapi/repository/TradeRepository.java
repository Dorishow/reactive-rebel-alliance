package com.rebels.rebelsapi.repository;

import com.rebels.rebelsapi.document.trade.Trade;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TradeRepository extends ReactiveCrudRepository<Trade, String> {
    Flux<Trade> findAllByAccepted(Boolean accepted);
}
