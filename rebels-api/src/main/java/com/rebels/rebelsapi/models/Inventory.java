package com.rebels.rebelsapi.models;

import com.rebels.rebelsapi.dto.inventory.InventoryRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter @Setter
@ToString
public class Inventory {
    private List<Item> items;
    private Integer totalPoints = 0;

    public static Inventory fromRequest(InventoryRequest request){
        Inventory inventory = new Inventory();
        inventory.setItems(request.getItems());
        request.getItems().forEach(
                item ->
                    inventory.setTotalPoints(inventory.getTotalPoints()
                        + (item.name.getItemPoints() * item.getAmount()))
        );

        return inventory;
    }
}
