package com.rebels.rebelsapi.controller;

import com.rebels.rebelsapi.document.trade.Trade;
import com.rebels.rebelsapi.dto.trade.TradeRequest;
import com.rebels.rebelsapi.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/trade")
@RequiredArgsConstructor
public class TradeController {

    private final TradeService service;

    @PostMapping
    public Mono<Trade> requestTrade(@RequestBody TradeRequest request){
        return service.requestTrade(request);
    }

    @GetMapping
    public Flux<Trade> listAllTrades(){
        return service.listTrades();
    }
}
