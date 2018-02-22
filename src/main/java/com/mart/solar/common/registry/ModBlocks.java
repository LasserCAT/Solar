package com.mart.solar.common.registry;

import com.mart.solar.common.blocks.*;
import com.mart.solar.common.blocks.base.BlockBase;
import com.mart.solar.common.blocks.base.BlockFlowerBase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;

public class ModBlocks {

    public static BlockAltar sunTotem;
    public static BlockBrokenAltar brokenTotem;
    public static BlockRuneInfuser runeInfuser;
    public static BlockSilverOre silverOre;

    public static BlockMenhir menhir;
    public static BlockRitualStone ritualStone;

    public static BlockFlowerMoon flowerMoon;

    public static void init(RegistryEvent.Register<Block> event) {
        sunTotem = register(new BlockAltar("suntotem"), event);
        brokenTotem = register(new BlockBrokenAltar("brokentotem"), event);
        runeInfuser = register(new BlockRuneInfuser("runeinfuser"), event);
        silverOre = register(new BlockSilverOre("silverore"), event);
        ritualStone = register(new BlockRitualStone(), event);

        menhir = register(new BlockMenhir("menhir"), event);

        flowerMoon = register(new BlockFlowerMoon("flower_moon"), event);
    }

    public static void initItemBlocks(RegistryEvent.Register<Item> event) {
        registerItemBlock(sunTotem, event);
        registerItemBlock(brokenTotem, event);
        registerItemBlock(runeInfuser, event);
        registerItemBlock(silverOre, event);
        registerItemBlock(menhir, event);
        registerItemBlock(ritualStone, event);
        registerItemBlock(flowerMoon, event);
    }

    private static <T extends Block> T register(T block, RegistryEvent.Register<Block> event) {
        event.getRegistry().register(block);

        return block;
    }

    private static void registerItemBlock(Block block, RegistryEvent.Register<Item> event) {
        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(block.getRegistryName());

        if (block instanceof BlockBase) {
            ((BlockBase) block).registerItemModel(itemBlock);
        }

        if(block instanceof BlockFlowerBase){
            ((BlockFlowerBase) block).registerItemModel(itemBlock);
        }

        event.getRegistry().register(itemBlock);
    }


}
