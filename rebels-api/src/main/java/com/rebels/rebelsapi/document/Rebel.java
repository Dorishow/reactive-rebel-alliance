package com.rebels.rebelsapi.document;

import com.rebels.rebelsapi.dto.rebel.RebelRequest;
import com.rebels.rebelsapi.enumerator.Genre;
import com.rebels.rebelsapi.models.Inventory;
import com.rebels.rebelsapi.models.Local;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

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
}
