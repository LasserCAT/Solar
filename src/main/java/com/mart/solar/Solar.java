package com.mart.solar;

import com.mart.solar.common.network.GuiHandler;
import com.mart.solar.common.creativetabs.SolarTab;
import com.mart.solar.common.world.gen.WorldGenSilverOre;
import com.mart.solar.common.network.handlers.ClientPacketHandler;
import com.mart.solar.common.network.handlers.ServerPacketHandler;
import com.mart.solar.common.network.SolarPacketHandler;
import com.mart.solar.common.registry.*;
import com.mart.solar.common.CommonProxy;
import com.mart.solar.common.world.gen.structures.StructForgottenAltar;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.logging.Logger;

@Mod(modid = Solar.MODID, version = Solar.VERSION)
@Mod.EventBusSubscriber
public class Solar
{

    public static final SolarTab solarTab = new SolarTab();

    public static final String networkChannelName = "solarMod";
    public static FMLEventChannel channel;

    public static final String MODID = "solar";
    public static final String name = "Solar";
    public static final String VERSION = "1.0";

    public static Logger logger;

    @SidedProxy(serverSide = "com.mart.solar.common.CommonProxy", clientSide = "com.mart.solar.client.ClientProxy")
    public static CommonProxy proxy;

    @Mod.Instance(MODID)
    public static Solar instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModTiles.init();
        ModRecipes.init();

        SolarPacketHandler.init();

        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(networkChannelName);
        channel.register(new ServerPacketHandler());
        channel.register(new ClientPacketHandler());

        StructForgottenAltar forgottenAltar = new StructForgottenAltar();
        WorldGenSilverOre silverOre = new WorldGenSilverOre();
        GameRegistry.registerWorldGenerator(forgottenAltar, 1);
        GameRegistry.registerWorldGenerator(silverOre, 1);

        OreDictionary.registerOre("oreSilver", ModBlocks.silverOre);
        OreDictionary.registerOre("ingotSilver", ModItems.silverIngot);

        GameRegistry.addSmelting(ModBlocks.silverOre, new ItemStack(ModItems.silverIngot, 1), 1.5f);

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
