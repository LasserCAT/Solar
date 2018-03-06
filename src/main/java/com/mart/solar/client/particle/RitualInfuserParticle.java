package com.mart.solar.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.World;

import javax.vecmath.Vector3d;

public class RitualInfuserParticle extends Particle {

    private double endX, endY, endZ;


    public RitualInfuserParticle(World worldIn, double startX, double startY, double startZ, double endX, double endY, double endZ) {
        super(worldIn, startX + 0.5, startY + 0.5, startZ + 0.5);

        this.endX = endX + 0.5;
        this.endY = endY + 0.5;
        this.endZ = endZ + 0.5;

        this.motionX = (this.endX - this.posX) * 0.05;
        this.motionY = (this.endY - this.posY) * 0.05;
        this.motionZ = (this.endZ - this.posZ) * 0.05;

        TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("solar:light");
        this.setParticleTexture(sprite);

        this.particleMaxAge = 100;
        this.canCollide = false;
    }

    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setExpired();
            return;
        }

        this.move(this.motionX, this.motionY, this.motionZ);
    }

    public int getFXLayer()
    {
        return 1;
    }
}
