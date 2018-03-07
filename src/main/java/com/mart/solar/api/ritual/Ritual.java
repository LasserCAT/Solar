package com.mart.solar.api.ritual;

import com.mart.solar.api.enums.RuneType;
import com.mart.solar.api.spell.Spell;
import com.mart.solar.common.items.ItemBase;
import com.mart.solar.common.items.ItemRitualAmulet;
import com.mart.solar.common.registry.ModItems;
import com.mart.solar.common.tileentities.TileAltar;
import com.mart.solar.common.tileentities.TileMenhir;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.List;

import static com.mart.solar.common.registry.ModBlocks.MENHIR;

public abstract class Ritual extends IForgeRegistryEntry.Impl<Ritual> {

    private String ritualName;

    public Ritual(String ritualName) {
        this.ritualName = ritualName;
    }

    public boolean isSetup(TileAltar altar) {
        List<RitualComponent> components = getRitualComponents();
        BlockPos altarPos = altar.getPos();

        for (RitualComponent component : components) {
            BlockPos checkPos = altarPos.add(component.getComponentPos());

            if (altar.getWorld().getBlockState(checkPos).getBlock() != MENHIR) {
                return false;
            }

            TileMenhir menhirTile = (TileMenhir) altar.getWorld().getTileEntity(checkPos);

            if (menhirTile == null) {
                return false;
            }

            ItemStack rune = menhirTile.getItemStackHandler().getStackInSlot(0);

            if (rune.isEmpty() && component.getRuneType() == null) {
                continue;
            }

            if (rune.isEmpty()) {
                return false;
            }

            int runeID = rune.getItemDamage();

            if (component.getRuneType() != RuneType.values()[runeID]) {
                return false;
            }
        }

        return true;
    }

    protected void givePlayerSpell(EntityPlayer player, Spell spell, int amuletEnergy) {
        ItemStack mainItem = player.getHeldItem(EnumHand.MAIN_HAND);
        ItemStack offItem = player.getHeldItem(EnumHand.OFF_HAND);
        ItemStack itemStack;

        if (mainItem.getItem() == ModItems.RITUAL_AMULET) {
            itemStack = mainItem;
        } else if (offItem.getItem() == ModItems.RITUAL_AMULET) {
            itemStack = offItem;
        } else {
            return;
        }

        spell.saveSpellHandleToNBT(ItemBase.getCompound(itemStack));
        spell.saveDataToNBT(ItemBase.getCompound(itemStack));
        ItemRitualAmulet.setEnergy(itemStack, amuletEnergy);
    }

    public void clearRunes(TileAltar altar){
        BlockPos altarPos = altar.getPos();
        for(RitualComponent component : getRitualComponents()){
            BlockPos checkPos = altarPos.add(component.getComponentPos());

            if (altar.getWorld().getBlockState(checkPos).getBlock() != MENHIR) {
                return;
            }

            TileMenhir menhirTile = (TileMenhir) altar.getWorld().getTileEntity(checkPos);

            if (menhirTile == null) {
                return;
            }

            menhirTile.emptyRuneSlot();
        }
    }

    public abstract void performRitual(TileAltar altar, EntityPlayer player);

    public abstract List<RitualComponent> getRitualComponents();

    public abstract int amuletEnergy();

    //getters setters
    public String getRitualName() {
        return ritualName;
    }

}
