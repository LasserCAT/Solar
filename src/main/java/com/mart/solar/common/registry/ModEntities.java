package com.mart.solar.common.registry;

import com.mart.solar.Solar;
import com.mart.solar.client.render.entityrender.RenderSpell;
import com.mart.solar.common.entity.EntitySpellContainer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEntities {

    private static int ID = 0;

    private static <T extends Entity> void regRender(Class<T> entityClass, IRenderFactory<? super T> renderFactory)
    {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
    }

    public static void regEntities(RegistryEvent.Register<EntityEntry> event)
    {
        EntityEntry entry1 = EntityEntryBuilder.create()
                .entity(EntitySpellContainer.class)
                .id(new ResourceLocation(Solar.MODID, "spell_container"), ID++)
                .name("spell_container")
                .tracker(64, 20, false)
                .build();

        event.getRegistry().register(entry1);
    }

    @SideOnly(Side.CLIENT)
    public static void regRenders()
    {
        regRender(EntitySpellContainer.class, RenderSpell.FACTORY);
    }
}
