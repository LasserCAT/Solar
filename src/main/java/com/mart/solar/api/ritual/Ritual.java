package com.mart.solar.api.ritual;

import com.mart.solar.api.enums.RuneType;
import com.mart.solar.common.registry.ModBlocks;
import com.mart.solar.common.registry.ModItems;
import com.mart.solar.common.tileentities.TileAltar;
import com.mart.solar.common.tileentities.TileMenhir;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.List;

public abstract class Ritual extends IForgeRegistryEntry.Impl<Ritual>{

    private String ritualName;
    private float ritualSolarCost;
    private float ritualLunarCost;

    public Ritual(String ritualName, float ritualSolarCost, float ritualLunarCost) {
        this.ritualName = ritualName;
        this.ritualSolarCost = ritualSolarCost;
        this.ritualLunarCost = ritualLunarCost;
    }

    public abstract void performRitual(TileAltar altar);

    public abstract List<RitualComponent> getRitualComponents();

    public boolean isSetup(TileAltar altar){
        System.out.println("U did it");
        List<RitualComponent> components = getRitualComponents();
        BlockPos altarPos = altar.getPos();

        for(RitualComponent component : components){
            BlockPos checkPos = altarPos.add(component.getComponentPos());

            if(altar.getWorld().getBlockState(checkPos).getBlock() != ModBlocks.menhir){
                System.out.println("blocks isnt menhir");
               return false;
            }

            TileMenhir menhirTile = (TileMenhir) altar.getWorld().getTileEntity(checkPos);
            ItemStack rune = menhirTile.getRune();

            if(rune.isEmpty()){
                System.out.println("Doenst have rune");
                return false;
            }

            int runeID = rune.getItemDamage();

            if(ModItems.RUNES.getDamage(rune) != runeID){
                System.out.println("runeID: " + runeID);
                System.out.println("getDamageID: " + ModItems.RUNES.getDamage(rune));
                System.out.println("not right rune ID");
                return false;
            }
        }

        System.out.println("and you even made it workn");

        return true;
    }

    //getters setters
    public String getRitualName() {
        return ritualName;
    }

    public float getRitualSolarCost() {
        return ritualSolarCost;
    }

    public float getRitualLunarCost() {
        return ritualLunarCost;
    }
}
