package com.mart.solar.common.spells;

import com.mart.solar.api.interfaces.IPlaceableSpell;
import com.mart.solar.api.spell.Spell;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class SpellBotanica extends Spell implements IPlaceableSpell {



    public SpellBotanica() {
        super("Botanica");

        this.lifeSpan = 24000;
    }

    @Override
    public void tick(World world, BlockPos pos, int tickValue) {
        if(tickValue % 20 != 0){
            return;
        }

        Random rand = new Random();

        int randX = rand.nextInt(16) - 8;
        int randZ = rand.nextInt(16) - 8;

        BlockPos randomPos = pos.add(randX, 0, randZ);

        IBlockState state = world.getBlockState(randomPos);

        System.out.println("X: " + randomPos.getX() + "Y: " + randomPos.getY() + "Z: " + randomPos.getZ());

        if(!(state.getBlock() instanceof IGrowable)){
            return;
        }

        IGrowable igrowable = (IGrowable)state.getBlock();

        if (igrowable.canGrow(world, randomPos, state, world.isRemote))
        {
            if (!world.isRemote)
            {
                if (igrowable.canUseBonemeal(world, world.rand, randomPos, state))
                {
                    igrowable.grow(world, world.rand, randomPos, state);
                }

            }
        }
    }

    @Override
    public void activateSpell(EntityPlayer player, ItemStack stack) {
        this.placeSpell(player, stack);
    }

    @Override
    public String getSpellRegistryName() {
        return "spell_botanica";
    }

    @Override
    public void saveDataToNBT(NBTTagCompound tag) {

    }

    @Override
    public void getDataFromNBT(NBTTagCompound tag) {

    }

    @Override
    public Spell getNewInstance() {
        return new SpellBotanica();
    }
}
