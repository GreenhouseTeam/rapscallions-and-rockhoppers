package dev.greenhouseteam.rapscallionsandrockhoppers.registry;

import dev.greenhouseteam.rapscallionsandrockhoppers.RapscallionsAndRockhoppers;
import dev.greenhouseteam.rapscallionsandrockhoppers.util.RegisterFunction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import org.apache.logging.log4j.util.BiConsumer;

import java.util.function.Consumer;

public class RapscallionsAndRockhoppersItems {
    public static final Item PENGUIN_EGG = new BlockItem(RapscallionsAndRockhoppersBlocks.PENGUIN_EGG, new Item.Properties());
    public static final Item PENGUIN_SPAWN_EGG = new SpawnEggItem(RapscallionsAndRockhoppersEntityTypes.PENGUIN, 0x232232, 0xEBD149, new Item.Properties());
    public static void registerItems(RegisterFunction<Item> function) {
        function.register(BuiltInRegistries.ITEM, RapscallionsAndRockhoppers.asResource("penguin_egg"), PENGUIN_EGG);
        function.register(BuiltInRegistries.ITEM, RapscallionsAndRockhoppers.asResource("penguin_spawn_egg"), PENGUIN_SPAWN_EGG);
    }

    public static void addAfterNaturalBlocksTab(BiConsumer<ItemStack, ItemStack> consumer) {
        consumer.accept(new ItemStack(Items.TURTLE_EGG), new ItemStack(PENGUIN_EGG));
    }

    public static void addSpawnEggsTab(Consumer<ItemStack> consumer) {
        consumer.accept(new ItemStack(PENGUIN_SPAWN_EGG));
    }
}