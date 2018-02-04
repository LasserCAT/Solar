package com.mart.solar.api.util;

import com.mart.solar.Solar;
import com.mart.solar.api.interfaces.IRune;

public class ItemRuneEnum<T extends Enum<T>> extends ItemEnum implements IRune {

    public ItemRuneEnum(Class<T> enumClass) {
        super(enumClass);
        setUnlocalizedName(Solar.MODID + "_rune");
    }

}
