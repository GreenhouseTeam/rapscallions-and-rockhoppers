package dev.greenhouseteam.rapscallionsandrockhoppers.network;

import dev.greenhouseteam.rapscallionsandrockhoppers.RapscallionsAndRockhoppers;
import dev.greenhouseteam.rapscallionsandrockhoppers.network.s2c.SyncBlockPosLookPacketS2C;
import net.neoforged.neoforge.network.NetworkRegistry;
import net.neoforged.neoforge.network.simple.MessageFunctions;
import net.neoforged.neoforge.network.simple.SimpleChannel;

import java.util.function.Consumer;

public class RapscallionsAndRockhoppersPacketHandler {
    private static final String PROTOCOL_VERISON = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            RapscallionsAndRockhoppers.asResource("main"),
            () -> PROTOCOL_VERISON,
            PROTOCOL_VERISON::equals,
            PROTOCOL_VERISON::equals
    );


    public static void register() {
        int i = 0;
        INSTANCE.registerMessage(i++, SyncBlockPosLookPacketS2C.class, SyncBlockPosLookPacketS2C::encode, SyncBlockPosLookPacketS2C::decode, createS2CHandler(SyncBlockPosLookPacketS2C::handle));
    }


    private static <MSG extends SyncBlockPosLookPacketS2C>  MessageFunctions.MessageConsumer<MSG> createS2CHandler(Consumer<MSG> handler) {
        return (msg, ctx) -> {
            handler.accept(msg);
            ctx.setPacketHandled(true);
        };
    }
}
