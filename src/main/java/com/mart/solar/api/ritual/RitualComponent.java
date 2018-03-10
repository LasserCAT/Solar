package com.mart.solar.api.ritual;

import com.mart.solar.common.items.enums.RuneType;
import net.minecraft.util.math.BlockPos;

public class RitualComponent {
    private final BlockPos componentPos;
    private final RuneType runeType;

    public RitualComponent(BlockPos componentPos, RuneType runeType) {
        this.componentPos = componentPos;
        this.runeType = runeType;
    }

    public BlockPos getComponentPos() {
        return componentPos;
    }

    public RuneType getRuneType() {
        return runeType;
    }
}
