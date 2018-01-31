package com.mart.solar.common.world.data;

import com.mart.solar.Solar;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InteractedWithAltarData extends WorldSavedData {

    private static final String IDENTIFIER = Solar.MODID + "interactedWithAltarList";

    private List<UUID> playerUUIDSList = new ArrayList<>();

    public InteractedWithAltarData() {
        super(IDENTIFIER);
    }

    public InteractedWithAltarData(String IDENTIFIER) {
        super(IDENTIFIER);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        NBTTagList tagList = nbt.getTagList("playerUUIDS", Constants.NBT.TAG_COMPOUND);
        this.playerUUIDSList.clear();
        for(int i = 0; i < tagList.tagCount(); i++){
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            UUID uuid = tag.getUniqueId("uuid");
            this.playerUUIDSList.add(uuid);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        NBTTagList tagList = new NBTTagList();
        for(UUID uuid : playerUUIDSList){
            NBTTagCompound tag = new NBTTagCompound();
            tag.setUniqueId("uuid", uuid);
            tagList.appendTag(tag);
        }

        nbt.setTag("playerUUIDS", tagList);
        return nbt;
    }

    public void addUUID(UUID uuid) {
        if(!this.playerUUIDSList.contains(uuid)){
            this.playerUUIDSList.add(uuid);
        }
        markDirty();
    }

    public List<UUID> getUUIDList() {
        for(UUID uuid: this.playerUUIDSList){
            System.out.println(uuid.toString());
        }
        return this.playerUUIDSList;
    }

    public static InteractedWithAltarData get(World world) {

        MapStorage storage = world.getMapStorage();
        if(storage == null) return null;
        InteractedWithAltarData data = (InteractedWithAltarData) storage.getOrLoadData(InteractedWithAltarData.class, IDENTIFIER);
        if (data == null) {
            data = new InteractedWithAltarData();
            storage.setData(IDENTIFIER, data);
        }
        return data;
    }
}
