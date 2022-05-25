package com.rebels.rebelsapi.document;

import com.rebels.rebelsapi.dto.inventory.InventoryRequest;
import com.rebels.rebelsapi.dto.rebel.RebelRequest;
import com.rebels.rebelsapi.dto.trade.TradeRequest;
import com.rebels.rebelsapi.enumerator.Genre;
import com.rebels.rebelsapi.models.Inventory;
import com.rebels.rebelsapi.models.Item;
import com.rebels.rebelsapi.models.Local;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Document
@Data
@NoArgsConstructor
@ToString
public class Rebel {
    @Id
    private String id;
    private String name;
    private Integer age;
    private Genre genre;
    private Local local;
    private Inventory inventory;
    private Integer reports = 0;
    private Boolean isTraitor;

    public static Rebel fromRequest(RebelRequest request){
        Rebel rebel = new Rebel();
        rebel.setId(UUID.randomUUID().toString());
        rebel.setName(request.getName());
        rebel.setAge(request.getAge());
        rebel.setGenre(request.getGenre());
        rebel.setLocal(request.getLocal());
        rebel.setInventory( Inventory.fromRequest(request.getInventory()));
        System.out.printf("%n");
        System.out.println("[INFO]: Inventory -> " + rebel.getInventory().getItems());
        rebel.setReports(0);
        rebel.setIsTraitor(false);

        return rebel;
    }

    public Rebel reportRebel(){
        this.setReports(this.getReports() + 1);
        if(this.getReports() >= 3){
            this.setIsTraitor(true);
        }

        return this;
    }

    public Boolean hasItem(Item item){
        Boolean hasItem = false;
        List<Item> paramItemOnBag = this.getInventory().getItems().stream().filter(i -> i.name == item.name).toList();
        if (!paramItemOnBag.isEmpty()){
            InventoryRequest inventoryRequest = new InventoryRequest();
            inventoryRequest.setItems(paramItemOnBag);
            Integer itemPointsOnBag = Inventory.fromRequest(inventoryRequest).getTotalPoints();
            if((item.getAmount() * item.getName().getItemPoints()) <= itemPointsOnBag){
                hasItem = true;
            }
        }

        return hasItem;
    }

    public boolean canTrade(TradeRequest request) {
        if (this.isTraitor) return false;

        AtomicReference<Boolean> canTrade = new AtomicReference<>(true);
        request.getItems().forEach(
                item -> {
                    if (!this.hasItem(item)){
                        canTrade.set(false);
                    }
                }
        );

        return canTrade.get();
    }
}
