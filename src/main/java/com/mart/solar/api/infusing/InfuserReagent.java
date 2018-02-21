package com.mart.solar.api.infusing;

import com.mart.solar.api.enums.RuneType;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class InfuserReagent extends IForgeRegistryEntry.Impl<InfuserReagent> {

    private final Item reagent;
    private final RuneType runeType;
    private final int reagentUses;

    public InfuserReagent(Item reagent, RuneType runeType, int reagentUses, String registryName){
        this.reagent = reagent;
        this.runeType = runeType;
        this.reagentUses = reagentUses;

        setRegistryName(registryName);
    }

    public Item getReagent() {
        return reagent;
    }

    public RuneType getRuneType() {
        return runeType;
    }

    public int getReagentUses() {
        return reagentUses;
    }
}
