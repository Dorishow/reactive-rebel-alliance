package com.rebels.rebelsapi.dto.inventory;

import com.rebels.rebelsapi.models.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
public class InventoryRequest {
    private List<Item> items;
}
