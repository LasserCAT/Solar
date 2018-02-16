package com.mart.solar.common.spells;

import com.mart.solar.api.spell.Spell;
import com.mart.solar.common.items.ItemBase;
import com.mart.solar.common.items.ItemRitualAmulet;
import com.mart.solar.common.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.ArrayList;
import java.util.List;

public class SpellHighTide extends Spell {

    public SpellHighTide() {
        super("High Tide");
    }

    @Override
    public void activateSpell(EntityPlayer player, ItemStack itemStack) {
        if (player.getEntityWorld().isRemote) {
            return;
        }

        if(itemStack.getItem() != ModItems.ritualAmulet){
            return;
        }

        int energy = ItemRitualAmulet.getEnergy(itemStack);
        World world = player.getEntityWorld();
        List<BlockPos> blocks = new ArrayList<>();

        Vec3d vec3d = player.getPositionEyes(0.1F);
        Vec3d vec3d1 = player.getLook(0.1F);
        Vec3d vec3d2 = vec3d.addVector(vec3d1.x * 5, vec3d1.y * 5, vec3d1.z * 5);
        RayTraceResult result = player.getEntityWorld().rayTraceBlocks(vec3d, vec3d2, true, false, true);

        assert result != null;
        Block b = player.getEntityWorld().getBlockState(result.getBlockPos()).getBlock();

        if (b.equals(Blocks.WATER)) {
            blocks.add(new BlockPos(result.getBlockPos().getX(), result.getBlockPos().getY() + 1, result.getBlockPos().getZ()));
        } else {
            return;
        }

        for (int i = 0; i < blocks.size(); i++) {
            if(blocks.size() > energy){
                setSpellToNullOnNBT(ItemBase.getCompound(itemStack));
                ItemRitualAmulet.setEnergy(itemStack, 0);

                WorldServer serverWorld = (WorldServer) player.getEntityWorld();
                serverWorld.playSound(null, result.getBlockPos(), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 100, 1);

                return;
            }

            BlockPos block = blocks.get(i);
            BlockPos blockpos1 = new BlockPos(block.getX(), block.getY(), block.getZ() + 1);
            BlockPos blockpos2 = new BlockPos(block.getX(), block.getY(), block.getZ() - 1);
            BlockPos blockpos3 = new BlockPos(block.getX() + 1, block.getY(), block.getZ());
            BlockPos blockpos4 = new BlockPos(block.getX() - 1, block.getY(), block.getZ());

            Block block1 = world.getBlockState(blockpos1).getBlock();
            Block block2 = world.getBlockState(blockpos2).getBlock();
            Block block3 = world.getBlockState(blockpos3).getBlock();
            Block block4 = world.getBlockState(blockpos4).getBlock();

            if (block1.equals(Blocks.AIR) && !blocks.contains(blockpos1))
                blocks.add(blockpos1);
            if (block2.equals(Blocks.AIR) && !blocks.contains(blockpos2))
                blocks.add(blockpos2);
            if (block3.equals(Blocks.AIR) && !blocks.contains(blockpos3))
                blocks.add(blockpos3);
            if (block4.equals(Blocks.AIR) && !blocks.contains(blockpos4))
                blocks.add(blockpos4);

            setWater(blocks, player.getEntityWorld());
            ItemRitualAmulet.setEnergy(itemStack, energy-blocks.size());
        }
    }

    @Override
    public String getSpellRegistryName() {
        return "spellhightide";
    }

    private void setWater(List<BlockPos> list, World world) {
        for (BlockPos p : list) {
            world.setBlockState(p, Blocks.WATER.getDefaultState());
        }
    }

    @Override
    public Spell getNewInstance() {
        return new SpellHighTide();
    }
}
