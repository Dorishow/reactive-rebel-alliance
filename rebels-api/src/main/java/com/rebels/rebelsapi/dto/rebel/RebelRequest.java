package com.rebels.rebelsapi.dto.rebel;

import com.rebels.rebelsapi.dto.inventory.InventoryRequest;
import com.rebels.rebelsapi.models.Local;
import com.rebels.rebelsapi.enumerator.Genre;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter @Setter
@ToString
public class RebelRequest {
    private String name;
    private Integer age;
    private Genre genre;
    private Local local;
    private InventoryRequest inventory;
}
