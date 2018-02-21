package com.mart.solar.api.infusing;

import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;

public class InfuserReagentManager {

    private static IForgeRegistry<InfuserReagent> REGISTRY;

    public static void setRegistry(IForgeRegistry<InfuserReagent> registry) {
        REGISTRY = registry;
    }

    public static List<InfuserReagent> getRituals() {
        return REGISTRY.getValues();
    }

}
