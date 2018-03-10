package com.mart.solar.common.registry;

import com.mart.solar.Solar;
import com.mart.solar.common.blocks.*;
import com.mart.solar.common.blocks.base.BlockBase;
import com.mart.solar.common.blocks.base.BlockFlowerBase;
import com.mart.solar.common.blocks.base.ISolarBlock;
import com.mart.solar.common.blocks.enums.EnumSunBurnt;
import com.mart.solar.common.items.base.ItemBase;
import com.mart.solar.common.items.enums.RuneType;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Locale;

@GameRegistry.ObjectHolder(Solar.MODID)
@Mod.EventBusSubscriber(modid = Solar.MODID)
public class ModBlocks {

    public static final Block ALTAR = Blocks.AIR;
    public static final Block BROKEN_ALTAR = Blocks.AIR;
    public static final Block RUNE_INFUSER = Blocks.AIR;
    public static final Block MENHIR = Blocks.AIR;
    //public static final Block RITUAL_STONE = Blocks.AIR;
    public static final Block SUNDIAL = Blocks.AIR;

    public static final Block SILVER_ORE = Blocks.AIR;

    public static final Block FLOWER_MOON = Blocks.AIR;
    public static final Block FLOWER_FIERY = Blocks.AIR;

    public static final Block SUNBURNT = Blocks.AIR;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new BlockAltar("altar"));
        event.getRegistry().register(new BlockBrokenAltar("broken_altar"));
        event.getRegistry().register(new BlockRuneInfuser("rune_infuser"));
        //event.getRegistry().register(new BlockRitualStone("ritual_stone"));
        event.getRegistry().register(new BlockMenhir("menhir"));
        event.getRegistry().register(new BlockSunDial("sundial"));

        event.getRegistry().register(new BlockSilverOre("silver_ore"));

        event.getRegistry().register(new BlockFlowerMoon("flower_moon"));
        event.getRegistry().register(new BlockFlowerFiery("flower_fiery"));

        event.getRegistry().register(new BlockSunburnt().setRegistryName("sunburnt"));
    }

    public static void initItemBlocks(RegistryEvent.Register<Item> event) {
        registerItemBlock(ALTAR, event);
        registerItemBlock(BROKEN_ALTAR, event);
        registerItemBlock(RUNE_INFUSER, event);
        registerItemBlock(MENHIR, event);
        //registerItemBlock(RITUAL_STONE, event);
        registerItemBlock(SUNDIAL, event);

        registerItemBlock(SILVER_ORE, event);

        registerItemBlock(FLOWER_MOON, event);
        registerItemBlock(FLOWER_FIERY, event);

        ISolarBlock solarBlock = (ISolarBlock) SUNBURNT;
        event.getRegistry().register(solarBlock.getItem().setRegistryName(SUNBURNT.getRegistryName()));


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
    public static void registerItems(ModelRegistryEvent event) {
        for(EnumSunBurnt type : EnumSunBurnt.values()){
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SUNBURNT), type.ordinal(), new ModelResourceLocation(SUNBURNT.getRegistryName(), "type=" + type.name().toLowerCase(Locale.ENGLISH)));
        }
    }
}
