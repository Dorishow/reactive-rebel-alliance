package com.rebels.rebelsapi.service;

import com.rebels.rebelsapi.document.trade.Trade;
import com.rebels.rebelsapi.dto.trade.TradeRequest;
import com.rebels.rebelsapi.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class TradeService {

    private final TradeRepository repository;
    private final RebelService rebelService;

    public Mono<Trade> requestTrade(TradeRequest request){
        Trade trade = Trade.fromRequest(request);
        AtomicReference<Mono<Trade>> finalTrade = new AtomicReference<Mono<Trade>>();
        var requester = rebelService.getRebelById(request.getRequesterId());
        requester.subscribe(rebel -> {
            if (rebel.canTrade(request)){
                System.out.printf("%n");
                System.out.println("[INFO]:" + rebel.getName() + " can trade " + request.getItems());
                System.out.printf("%n");
            } else {
                System.out.printf("%n");
                System.out.println("[WARN]:" + rebel.getName() + " can NOT trade ");
                System.out.printf("%n");
            }
        });

        return finalTrade.get();
    }

    public Flux<Trade> listTrades(){
        return repository.findAllByAccepted(false);
    }
}
