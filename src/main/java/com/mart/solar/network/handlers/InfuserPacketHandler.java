package com.mart.solar.network.handlers;

public class InfuserPacketHandler{ // implements IMessageHandler<InfuserPacket, IMessage>

    /*@Override
    public IMessage onMessage(InfuserPacket message, MessageContext ctx) {
        System.out.println("Called");

        TileRuneInfuser runeInfuser = (TileRuneInfuser) message.world.getTileEntity(new BlockPos(message.x, message.y, message.z));

        if(runeInfuser != null){
            System.out.println("TileEntityFound");
            runeInfuser.setRune(message.rune);
            if(message.rune != null){
                System.out.println("Rune is there");
                System.out.println(runeInfuser.getRune().getDisplayName());
            }
            runeInfuser.setModifier(message.modifier);
            if(message.modifier != null){
                System.out.println("Modifier is there");
                System.out.println(runeInfuser.getModifier().getDisplayName());
            }
        }

        return null;
    }*/
}
