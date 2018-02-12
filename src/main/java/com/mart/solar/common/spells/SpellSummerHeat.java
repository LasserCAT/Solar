package com.mart.solar.common.spells;

import com.mart.solar.api.spell.Spell;
import com.mart.solar.common.items.ItemRitualAmulet;
import com.mart.solar.common.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.ArrayList;
import java.util.List;

public class SpellSummerHeat extends Spell {

    public SpellSummerHeat() {
        super("Summer Heat");
    }

    @Override
    public void activateSpell(EntityPlayer player, ItemStack itemStack) {
        System.out.println("Calls spell");
        if (player.getEntityWorld().isRemote) {
            return;
        }

        if(itemStack.getItem() != ModItems.ritualAmulet){
            System.out.println("is no amulet");
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
            blocks.add(result.getBlockPos());
        } else {
            return;
        }

        System.out.println("Block is wataaaw");

        for (int i = 0; i < blocks.size(); i++) {
            System.out.println("BAM");
            if(blocks.size() > energy){
                System.out.println("Well this is too pricy for me. Bye spell");
                ItemRitualAmulet.setCurrentSpell(itemStack, "");
                ItemRitualAmulet.setEnergy(itemStack, 0);
                System.out.println("Sound");
                player.getEntityWorld().playSound(player, result.getBlockPos(), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 100, 1);

                for(BlockPos blockPos : blocks){
                    System.out.println("particle");
                    WorldServer serverWorld = (WorldServer) player.getEntityWorld();
                    serverWorld.spawnParticle(EnumParticleTypes.SMOKE_LARGE, blockPos.getX(), blockPos.getY() + 1, blockPos.getZ(), 0, 2, 0);
                }
                return;
            }

            BlockPos block = blocks.get(i);
            BlockPos blockPos1 = new BlockPos(block.getX(), block.getY(), block.getZ() + 1);
            BlockPos blockPos2 = new BlockPos(block.getX(), block.getY(), block.getZ() - 1);
            BlockPos blockPos3 = new BlockPos(block.getX() + 1, block.getY(), block.getZ());
            BlockPos blockPos4 = new BlockPos(block.getX() - 1, block.getY(), block.getZ());

            Block block1 = world.getBlockState(blockPos1).getBlock();
            Block block2 = world.getBlockState(blockPos2).getBlock();
            Block block3 = world.getBlockState(blockPos3).getBlock();
            Block block4 = world.getBlockState(blockPos4).getBlock();

            if (block1.equals(Blocks.WATER) && !blocks.contains(blockPos1))
                blocks.add(blockPos1);
            if (block2.equals(Blocks.WATER) && !blocks.contains(blockPos2))
                blocks.add(blockPos2);
            if (block3.equals(Blocks.WATER) && !blocks.contains(blockPos3))
                blocks.add(blockPos3);
            if (block4.equals(Blocks.WATER) && !blocks.contains(blockPos4))
                blocks.add(blockPos4);

        }

        System.out.println("turn blocks into air");
        setWater(blocks, player.getEntityWorld());
        System.out.println("oh and energt to 0");
        ItemRitualAmulet.setEnergy(itemStack, energy-blocks.size());
    }

    @Override
    public String getSpellRegistryName() {
        return "spellsummerheat";
    }

    private void setWater(List<BlockPos> list, World world) {
        for (BlockPos p : list) {
            world.setBlockToAir(p);
        }
    }
}
