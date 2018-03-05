package com.mart.solar.common.registry;

import com.mart.solar.common.blocks.*;
import com.mart.solar.common.blocks.base.BlockBase;
import com.mart.solar.common.blocks.base.BlockFlowerBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder("solar")
@Mod.EventBusSubscriber(modid = "solar")
public class ModBlocks {

    @GameRegistry.ObjectHolder("altar")
    public static Block blockAltar = Blocks.AIR;

    @GameRegistry.ObjectHolder("broken_altar")
    public static Block brokenAltar = Blocks.AIR;

    @GameRegistry.ObjectHolder("rune_infuser")
    public static Block runeInfuser = Blocks.AIR;

    @GameRegistry.ObjectHolder("menhir")
    public static Block menhir = Blocks.AIR;

    @GameRegistry.ObjectHolder("ritual_stone")
    public static Block ritualStone = Blocks.AIR;

    @GameRegistry.ObjectHolder("sundial")
    public static Block sundial = Blocks.AIR;

    @GameRegistry.ObjectHolder("silver_ore")
    public static Block silverOre = Blocks.AIR;

    @GameRegistry.ObjectHolder("flower_moon")
    public static Block flowerMoon = Blocks.AIR;

    @GameRegistry.ObjectHolder("flower_fiery")
    public static Block flowerFiery = Blocks.AIR;

    public static void init(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new BlockAltar("altar"));
        event.getRegistry().register(new BlockBrokenAltar("broken_altar"));
        event.getRegistry().register(new BlockRuneInfuser("rune_infuser"));
        event.getRegistry().register(new BlockRitualStone("ritual_stone"));
        event.getRegistry().register(new BlockMenhir("menhir"));
        event.getRegistry().register(new BlockSunDial("sundial"));

        event.getRegistry().register(new BlockSilverOre("silver_ore"));

        event.getRegistry().register(new BlockFlowerMoon("flower_moon"));
        event.getRegistry().register(new BlockFlowerFiery("flower_fiery"));
    }

    public static void initItemBlocks(RegistryEvent.Register<Item> event) {
        registerItemBlock(blockAltar, event);
        registerItemBlock(brokenAltar, event);
        registerItemBlock(runeInfuser, event);
        registerItemBlock(menhir, event);
        registerItemBlock(ritualStone, event);
        registerItemBlock(sundial, event);

        registerItemBlock(silverOre, event);

        registerItemBlock(flowerMoon, event);
        registerItemBlock(flowerFiery, event);
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

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        init(event);
    }


}
