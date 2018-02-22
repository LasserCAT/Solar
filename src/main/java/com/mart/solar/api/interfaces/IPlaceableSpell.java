package com.mart.solar.api.interfaces;


import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IPlaceableSpell {

    void tick(World world, BlockPos pos, int tickValue);

}
