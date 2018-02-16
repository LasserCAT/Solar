package com.mart.solar.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

public class SolarProtectionParticle extends Particle {

    public SolarProtectionParticle(World worldIn, double posXIn, double posYIn, double posZIn) {
        super(worldIn, posXIn, posYIn, posZIn);
        System.out.println("Particle is spawned");
    }


}
