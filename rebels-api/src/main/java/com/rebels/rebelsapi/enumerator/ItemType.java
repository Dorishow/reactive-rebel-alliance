package com.rebels.rebelsapi.enumerator;

import lombok.Getter;

@Getter
public enum ItemType {
    GUN(4),
    AMMO(3),
    WATER(2),
    FOOD(1);

    private Integer itemPoints;

    private ItemType(Integer itemPoints) {
        this.itemPoints = itemPoints;
    }
}
