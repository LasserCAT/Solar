package com.mart.solar.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.World;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector3d;

public class EnergyParticle extends Particle {

    private double endX, endY, endZ, startX, startY, startZ;


    public EnergyParticle(World worldIn, double startX, double startY, double startZ, double endX, double endY, double endZ) {
        super(worldIn, startX + 0.5, startY + 0.5, startZ + 0.5);

        this.endX = endX + 0.5;
        this.endY = endY + 0.5;
        this.endZ = endZ + 0.5;

        this.startX = startX;
        this.startY = startY;
        this.startX = startX;

        this.motionX = (this.endX - this.posX) * 0.05;
        this.motionY = (this.endY - this.posY) * 0.05;
        this.motionZ = (this.endZ - this.posZ) * 0.05;

        TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("solar:particles/energy_particle");
        this.setParticleTexture(sprite);

        this.setRBGColorF(1, 1, 0);

        this.particleMaxAge = 100;
        this.canCollide = false;
    }

    @Override
    public void onUpdate() {

        Vector3d beginVec = new Vector3d(this.posX, this.posY, this.posZ);
        Vector3d endVec = new Vector3d(this.endX, this.endY, this.endZ);

        double distance  = beginVec.length() - endVec.length();

        if (distance > -0.2 && distance < 0.2) {
            this.setExpired();
            return;
        }

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
