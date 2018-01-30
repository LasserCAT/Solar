package com.mart.solar.registry;

import com.mart.solar.blocks.*;
import com.mart.solar.blocks.menhirs.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks {

    public static BlockTotem sunTotem;
    public static BlockBrokenTotem brokenTotem;
    public static BlockRuneInfuser runeInfuser;
    public static BlockSilverOre silverOre;

    public static BlockMenhir menhir;
    public static BlockSunMenhir sunMenhir;
    public static BlockMoonMenhir moonMenhir;
    public static BlockTimeMenhir timeMenhir;
    public static BlockLifeMenhir lifeMenhir;

    public static void init(RegistryEvent.Register<Block> event) {
        sunTotem = register(new BlockTotem("suntotem"), event);
        brokenTotem = register(new BlockBrokenTotem("brokentotem"), event);
        runeInfuser = register(new BlockRuneInfuser("runeinfuser"), event);
        silverOre = register(new BlockSilverOre("silverore"), event);

        menhir = register(new BlockMenhir("menhir"), event);
        sunMenhir = register(new BlockSunMenhir("sunmenhir"), event);
        moonMenhir = register(new BlockMoonMenhir("moonmenhir"), event);
        timeMenhir = register(new BlockTimeMenhir("timemenhir"), event);
        lifeMenhir = register(new BlockLifeMenhir("lifemenhir"), event);
    }

    public static void initItemBlocks(RegistryEvent.Register<Item> event) {
        registerItemBlock(sunTotem, event);
        registerItemBlock(brokenTotem, event);
        registerItemBlock(runeInfuser, event);
        registerItemBlock(silverOre, event);
        registerItemBlock(menhir, event);
        registerItemBlock(sunMenhir, event);
        registerItemBlock(moonMenhir, event);
        registerItemBlock(timeMenhir, event);
        registerItemBlock(lifeMenhir, event);
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
