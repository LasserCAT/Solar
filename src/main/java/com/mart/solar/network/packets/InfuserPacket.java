package com.mart.solar.network.packets;

public class InfuserPacket { //implements IMessage

   /* public ItemStack rune;
    public ItemStack modifier;

    public int x,y,z;

    public World world;

    public InfuserPacket(){}

    public InfuserPacket(ItemStack rune, ItemStack modifier, int x, int y, int z, World world){
        this.rune = rune;
        this.modifier = modifier;
        this.x = x;
        this.z = z;
        this.y = y;
        this.world = world;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        rune = ByteBufUtils.readItemStack(buf);
        modifier = ByteBufUtils.readItemStack(buf);
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();

        int worldId = buf.readInt();

        WorldServer[] Worlds = DimensionManager.getWorlds();

        for (WorldServer world : Worlds) {
            if(world.provider.getDimension() == worldId){
                this.world = world;
                return;
            }
        }

    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeItemStack(buf, rune);
        ByteBufUtils.writeItemStack(buf, modifier);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(world.provider.getDimension());
    }*/
}
