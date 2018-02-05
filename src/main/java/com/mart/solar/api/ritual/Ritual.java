package com.mart.solar.api.ritual;

import com.mart.solar.api.enums.RuneType;
import com.mart.solar.common.registry.ModBlocks;
import com.mart.solar.common.registry.ModItems;
import com.mart.solar.common.tileentities.TileAltar;
import com.mart.solar.common.tileentities.TileMenhir;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.ArrayList;
import java.util.List;

import static com.mart.solar.common.registry.ModBlocks.menhir;

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
        List<RitualComponent> components = getRitualComponents();
        BlockPos altarPos = altar.getPos();

        List<TileMenhir> menhirs = new ArrayList<>();

        for(RitualComponent component : components){
            BlockPos checkPos = altarPos.add(component.getComponentPos());

            if(altar.getWorld().getBlockState(checkPos).getBlock() != menhir){
               return false;
            }

            TileMenhir menhirTile = (TileMenhir) altar.getWorld().getTileEntity(checkPos);

            if(menhirTile == null){
                return false;
            }

            ItemStack rune = menhirTile.getRune();

            if(rune.isEmpty() && component.getRuneType() == null){
                continue;
            }

            if(rune.isEmpty()){
                return false;
            }

            int runeID = rune.getItemDamage();

            if(component.getRuneType() != RuneType.values()[runeID]){
                return false;
            }

            menhirs.add(menhirTile);
        }

        menhirs.forEach(TileMenhir::emptyRuneSlot);
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
