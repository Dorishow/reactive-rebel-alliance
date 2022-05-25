package com.rebels.rebelsapi.dto.trade;

import com.rebels.rebelsapi.models.Item;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Data
@Getter
@Setter
@ToString
public class TradeRequest {
    private String requesterId;
    private List<Item> items;
    private String targetRebelId;
}
