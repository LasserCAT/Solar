package com.mart.solar.common.tileentities;

//https://pastebin.com/9Tj891ua

import net.minecraft.util.ITickable;

import java.util.Random;

public class TileRuneInfuser extends TileBase implements ITickable {

    public TileRuneInfuser(){

    }



    @Override
    public void update() {
        if(this.getWorld().isRemote){
            return;
        }
        Random random = new Random(this.world.getSeed());
        System.out.println(this.world.getSeed());


        System.out.println(random.nextInt(10));
    }
}