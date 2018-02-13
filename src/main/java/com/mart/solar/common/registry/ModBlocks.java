package com.mart.solar.common.registry;

import com.mart.solar.common.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;

public class ModBlocks {

    public static BlockAltar sunTotem;
    public static BlockBrokenTotem brokenTotem;
    public static BlockRuneInfuser runeInfuser;
    public static BlockSilverOre silverOre;

    public static BlockMenhir menhir;
    public static BlockRitualStone ritualStone;

    public static void init(RegistryEvent.Register<Block> event) {
        sunTotem = register(new BlockAltar("suntotem"), event);
        brokenTotem = register(new BlockBrokenTotem("brokentotem"), event);
        runeInfuser = register(new BlockRuneInfuser("runeinfuser"), event);
        silverOre = register(new BlockSilverOre("silverore"), event);
        ritualStone = register(new BlockRitualStone(), event);

        menhir = register(new BlockMenhir("menhir"), event);
    }

    public static void initItemBlocks(RegistryEvent.Register<Item> event) {
        registerItemBlock(sunTotem, event);
        registerItemBlock(brokenTotem, event);
        registerItemBlock(runeInfuser, event);
        registerItemBlock(silverOre, event);
        registerItemBlock(menhir, event);
        registerItemBlock(ritualStone, event);
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

        event.getRegistry().register(itemBlock);
    }

}
