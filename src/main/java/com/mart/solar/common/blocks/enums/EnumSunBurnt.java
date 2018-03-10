package com.mart.solar.common.blocks.enums;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum EnumSunBurnt implements IStringSerializable {
    RAW, PAVED, ENGRAVED, ARCHED_1, ARCHED_2, ARCHED_3, ARCHED_4, ARCHED_5, OXIDISED_ENDORSED, DIAMOND_ENDORSED,;

    @Override
    public String toString() {
        return name().toLowerCase(Locale.ROOT);
    }

    @Override
    public String getName() {
        return toString();
    }
}
