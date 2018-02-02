package com.mart.solar.common.tileentities;

import com.google.common.collect.BiMap;
import com.mart.solar.api.interfaces.IRune;
import com.mart.solar.api.registry.InfuserRecipeRegister;
import com.mart.solar.common.registry.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class TileRuneInfuser extends TileBase implements ITickable {

    private ItemStack rune;
    private ItemStack modifier;

    private int infuserDuration = 140;
    private int currentDuration = 0;
    private boolean infusing = false;

    private ItemStack nextOutput;

    private Random random = new Random();

    //Infuser Methods
    public void onUse(ItemStack heldItem, EntityPlayer player, EnumHand hand) {
        if (heldItem.getItem() instanceof IRune) {
            if (rune == null) {
                ItemStack heldItem2 = heldItem.copy();

                heldItem2.setCount(1);
                setRune(heldItem2);

                heldItem.setCount(heldItem.getCount() - 1);
                player.setHeldItem(hand, heldItem);
            }
        } else {
            if (modifier == null) {
                ItemStack heldItem2 = heldItem.copy();

                heldItem2.setCount(1);
                setModifier(heldItem2);

                heldItem.setCount(heldItem.getCount() - 1);
                player.setHeldItem(hand, heldItem);
            }
        }

        checkRecipe();
    }

    public void extractItem(EntityPlayer player, EnumHand hand) {
        if (modifier != null) {
            player.inventory.addItemStackToInventory(modifier);
            System.out.println("Modifier Extracted:" + modifier.getDisplayName());
            setModifier(null);
        } else if (rune != null) {
            player.inventory.addItemStackToInventory(rune);
            setRune(null);
            System.out.println("Rune Extraced");
        }
    }

    public void checkRecipe() {
        if (rune != null && modifier != null) {
            for (BiMap.Entry<Item, InfuserRecipeRegister.InfuserRecipe> b : InfuserRecipeRegister.getRecipes().entrySet()) {
                if (modifier.getItem() == b.getKey()) {
                    infusing = true;

                    nextOutput = new ItemStack(ModItems.RUNES, 1, b.getValue().getOutput().ordinal());
                    break;
                }
            }
        }
    }

    public void endInfusing() {
        setModifier(null);
        infusing = false;
        currentDuration = 0;
        setRune(nextOutput);
        nextOutput = null;
    }

    //Getters and Setters
    public ItemStack getModifier() {
        return modifier;
    }

    public void setModifier(ItemStack modifier2) {
        this.modifier = modifier2;
    }

    public ItemStack getRune() {
        return rune;
    }

    public void setRune(ItemStack rune) {
        this.rune = rune;
    }

    //Data
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        if (rune != null) {
            NBTTagList tagList = new NBTTagList();
            NBTTagCompound itemCompound = new NBTTagCompound();
            rune.writeToNBT(itemCompound);
            tagList.appendTag(itemCompound);
            compound.setTag("rune", tagList);
            compound.setBoolean("runeAvailable", true);
        } else {
            compound.setBoolean("runeAvailable", false);
        }

        if (modifier != null) {
            NBTTagList itemList = new NBTTagList();
            NBTTagCompound modifierCompound = new NBTTagCompound();
            modifier.writeToNBT(modifierCompound);
            itemList.appendTag(modifierCompound);
            compound.setTag("modifier", itemList);
            compound.setBoolean("modifierAvailable", true);
        } else {
            compound.setBoolean("modifierAvailable", false);
        }

        compound.setBoolean("infuserActive", infusing);

        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        if (compound.getBoolean("runeAvailable")) {
            NBTTagList tagList = (NBTTagList) compound.getTag("rune");
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(0);
            rune = new ItemStack(tagCompound);
        } else {
            rune = null;
        }

        if (compound.getBoolean("modifierAvailable")) {
            NBTTagList modifierList = (NBTTagList) compound.getTag("modifier");
            NBTTagCompound modifierCompound = modifierList.getCompoundTagAt(0);
            modifier = new ItemStack(modifierCompound);
        } else {
            modifier = null;
        }

        infusing = compound.getBoolean("infuserActive");
    }

    //Ticket methods
    @Override
    public void update() {
        notifyUpdate();

        if (infusing) {
            spawnInfuseParticle();
            if (currentDuration <= infuserDuration) {
                currentDuration++;
            } else {
                endInfusing();
            }
        }

    }

    @SideOnly(Side.CLIENT)
    public void spawnInfuseParticle() {
        int[] array = {Item.getIdFromItem(modifier.getItem())};
        Vec3d[] vec = getPositionAndVelocity(getPos());
        getWorld().spawnParticle(EnumParticleTypes.ITEM_CRACK, vec[0].x, vec[0].y, vec[0].z, vec[1].x, 0, vec[1].z, array);
    }

    private Vec3d[] getPositionAndVelocity(BlockPos center) {

        double velocityFactor = 0.1;
        double radius = 0.2 + random.nextDouble() * 0.2;
        double angle = random.nextDouble() * Math.PI * 2;
        double posX = 0.5 + Math.cos(angle) * radius;
        double posZ = 0.5 + Math.sin(angle) * radius;

        Vec3d p = new Vec3d(posX + center.getX(), center.getY() + 2, posZ + center.getZ());
        Vec3d v = new Vec3d((0.5 - posX) * velocityFactor, 0, (0.5 - posZ) * velocityFactor);
        return new Vec3d[]{p, v};
    }
}