package com.mart.solar.common.world.data;

import com.mart.solar.Solar;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InteractedWithAltarData extends WorldSavedData{

    private static final String IDENTIFIER = Solar.MODID + "interactedWithAltarList";
    private String playerUUIDs = "";

    private List<String> playerUUIDList = new ArrayList<>();

    private InteractedWithAltarData() {
        super(IDENTIFIER);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.playerUUIDs = nbt.getString("playerUUIDS");
        readyList();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setString("playerUUIDS", playerUUIDs);
        return null;
    }

    private void readyList(){
        String[] stringsArray = playerUUIDs.split(", ");
        this.playerUUIDList.clear();

        Collections.addAll(this.playerUUIDList, stringsArray);
    }

    public void addUUID(String UUID){
        if(this.playerUUIDs.equalsIgnoreCase("")){
            this.playerUUIDs = UUID;
        }
        else{
            this.playerUUIDs = this.playerUUIDs + ", " + UUID;
            markDirty();
        }
    }

    public List<String> getUUIDList(){
        return this.playerUUIDList;
    }

    public static InteractedWithAltarData getData(World world){
        MapStorage storage = world.getMapStorage();
        InteractedWithAltarData data = (InteractedWithAltarData) storage.getOrLoadData(InteractedWithAltarData.class, IDENTIFIER);
        if(data == null){
            data = new InteractedWithAltarData();
            storage.setData(IDENTIFIER, data);
        }
        return data;
    }
}
