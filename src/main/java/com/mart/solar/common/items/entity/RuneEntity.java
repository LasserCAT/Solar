package com.mart.solar.common.items.entity;

import com.mart.solar.api.infusing.InfuserReagentManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RuneEntity  extends EntityItem{

    List<EntityItem> pullableItems = new ArrayList<>();

    public RuneEntity(World worldIn, double x, double y, double z, ItemStack stack) {
        super(worldIn, x, y, z, stack);
    }

    public RuneEntity(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onUpdate() {
        pullableItems.clear();
        List<EntityItem> entitiesInRange = getEntityWorld().getEntitiesWithinAABB(EntityItem.class,
                new AxisAlignedBB(this.getPosition().getX() - 3, this.getPosition().getY()-1, this.getPosition().getZ() - 3,
                        this.getPosition().getX() + 3, this.getPosition().getY()+ 1, this.getPosition().getZ() + 3));

        for(EntityItem entityItem : entitiesInRange){
            if(entityItem == this){
                continue;
            }

            InfuserReagentManager.getReagents().forEach(rg -> {
                if(entityItem.getItem().getItem() == rg.getReagent()){
                    this.pullableItems.add(entityItem);
                }
            });
        }

        for(EntityItem entityItem : pullableItems){
            double factor = 0.01d;

            entityItem.motionX += (this.posX - entityItem.posX) * factor;

            entityItem.motionY += (this.posY - entityItem.posY) * factor;

            entityItem.motionZ += (this.posZ - entityItem.posZ) * factor;
        }

        super.onUpdate();

    }
}
