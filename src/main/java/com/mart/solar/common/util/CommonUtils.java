package com.mart.solar.common.util;

import net.minecraft.world.World;

public class CommonUtils {

    public static boolean isDay(World world) {
        long worldTime = world.getWorldTime();
        long dayTime = worldTime % 24000;

        return dayTime < 12566 || dayTime > 23450;

    }

}
