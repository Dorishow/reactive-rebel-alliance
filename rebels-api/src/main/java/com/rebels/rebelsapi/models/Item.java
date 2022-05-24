package com.rebels.rebelsapi.models;

import com.rebels.rebelsapi.enumerator.ItemType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Item {
    public ItemType name;
    private Integer amount;
}
