package com.rebels.rebelsapi.document.trade;

import com.rebels.rebelsapi.document.Rebel;
import com.rebels.rebelsapi.dto.rebel.RebelRequest;
import com.rebels.rebelsapi.dto.trade.TradeRequest;
import com.rebels.rebelsapi.models.Inventory;
import com.rebels.rebelsapi.models.Item;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
@Data
@NoArgsConstructor
@ToString
public class Trade {
    @Id
    private String id;
    private String requesterId;
    private List<Item> items;
    private String targetRebelId;
    private Boolean accepted;

    public static Trade fromRequest(TradeRequest request){
        Trade trade = new Trade();
        trade.setId(UUID.randomUUID().toString());
        trade.setRequesterId(request.getRequesterId());
        trade.setItems(request.getItems());
        trade.setTargetRebelId(request.getTargetRebelId());
        trade.setAccepted(false);

        return trade;
    }
}
