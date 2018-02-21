package com.mart.solar.api.infusing;

import com.mart.solar.Solar;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;

public class InfuserReagentManager {

    private static IForgeRegistry<InfuserReagent> REGISTRY;

    public static void setRegistry(IForgeRegistry<InfuserReagent> registry) {
        REGISTRY = registry;
    }

    public static List<InfuserReagent> getReagents() {
        return REGISTRY.getValues();
    }

    public static InfuserReagent getInuserReagent(String registrationName){
        return REGISTRY.getValue(new ResourceLocation(registrationName));
    }

}
