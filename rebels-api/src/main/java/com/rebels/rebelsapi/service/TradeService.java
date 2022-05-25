package com.rebels.rebelsapi.service;

import com.rebels.rebelsapi.document.trade.Trade;
import com.rebels.rebelsapi.dto.trade.TradeRequest;
import com.rebels.rebelsapi.models.Item;
import com.rebels.rebelsapi.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
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
        requester.subscribe(tradeRequester -> {
            if (tradeRequester.canTrade(request)){
                var receiverMono = rebelService.getRebelById(request.getTargetRebelId());
                receiverMono.subscribe( receiver -> {
                    Mono<Trade> completedTrade = repository.save(trade).log().doOnNext( t -> {
                        showSuccesTradeInfo(tradeRequester.getName(), receiver.getName(), t.getRequesterId(), t.getItems());
                    });
                    completedTrade.subscribe(completed -> finalTrade.set(Mono.just(completed)));
                } );
            } else {
                showDeniedTradeMessage(tradeRequester.getName());
            }
        });

        return finalTrade.get();
    }

    public Flux<Trade> listTrades(){
        return repository.findAllByAccepted(false);
    }

    private void showSuccesTradeInfo(String requester, String receiver, String tradeRequestId, List<Item> items){
        System.out.printf("%n");
        System.out.println("[TRADE - INFO]: requester -> " + requester);
        System.out.println("[TRADE - INFO]: target -> " + receiver);
        System.out.println("[TRADE - INFO]: trade -> " + tradeRequestId);
        System.out.println("[TRADE - INFO]: trade -> " + items);
        System.out.printf("%n");
    }

    private void showDeniedTradeMessage(String requesterName){
        System.out.printf("%n");
        System.out.println("[WARN]:" + requesterName + " can NOT trade ");
        System.out.printf("%n");
    }
}
