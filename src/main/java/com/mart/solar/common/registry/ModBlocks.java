package com.mart.solar.common.registry;

import com.mart.solar.common.blocks.*;
import com.mart.solar.common.blocks.base.BlockBase;
import com.mart.solar.common.blocks.base.BlockFlowerBase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;

public class ModBlocks {

    public static BlockAltar blockAltar;
    public static BlockBrokenAltar brokenAltar;
    public static BlockRuneInfuser runeInfuser;
    public static BlockMenhir menhir;
    public static BlockRitualStone ritualStone;
    public static BlockSunDial sunDial;

    public static BlockSilverOre silverOre;

    public static BlockFlowerMoon flowerMoon;

    public static void init(RegistryEvent.Register<Block> event) {
        blockAltar = register(new BlockAltar("suntotem"), event);
        brokenAltar = register(new BlockBrokenAltar("brokentotem"), event);
        runeInfuser = register(new BlockRuneInfuser("runeinfuser"), event);
        ritualStone = register(new BlockRitualStone(), event);
        menhir = register(new BlockMenhir("menhir"), event);
        sunDial = register(new BlockSunDial("sun_dial"), event);

        silverOre = register(new BlockSilverOre("silverore"), event);

        flowerMoon = register(new BlockFlowerMoon("flower_moon"), event);
    }

    public static void initItemBlocks(RegistryEvent.Register<Item> event) {
        registerItemBlock(blockAltar, event);
        registerItemBlock(brokenAltar, event);
        registerItemBlock(runeInfuser, event);
        registerItemBlock(menhir, event);
        registerItemBlock(ritualStone, event);
        registerItemBlock(sunDial, event);

        registerItemBlock(silverOre, event);

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
