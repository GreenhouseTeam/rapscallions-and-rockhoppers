package dev.greenhouseteam.rapscallionsandrockhoppers.registry;

import dev.greenhouseteam.rapscallionsandrockhoppers.RapscallionsAndRockhoppers;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class RockhoppersTags {

    public static class ItemTags {
        public static final TagKey<Item> PENGUIN_TEMPT_ITEMS = TagKey.create(Registries.ITEM, RapscallionsAndRockhoppers.asResource("penguin_tempt_items"));
        public static final TagKey<Item> PENGUIN_BREED_ITEMS = TagKey.create(Registries.ITEM, RapscallionsAndRockhoppers.asResource("penguin_breed_items"));
    }

    public static class BlockTags {
        public static final TagKey<Block> ROCKHOPPER_PENGUIN_SPAWN_BLOCKS = TagKey.create(Registries.BLOCK, RapscallionsAndRockhoppers.asResource("rockhopper_penguin_spawn_blocks"));
        public static final TagKey<Block> CHINSTRAP_PENGUIN_SPAWN_BLOCKS = TagKey.create(Registries.BLOCK, RapscallionsAndRockhoppers.asResource("chinstrap_penguin_spawn_blocks"));
    }

    public static class BiomeTags {
        public static final TagKey<Biome> ROCKHOPPER_PENGUIN_SPAWN_BIOMES = TagKey.create(Registries.BIOME, RapscallionsAndRockhoppers.asResource("rockhopper_penguin_spawn_biomes"));
        public static final TagKey<Biome> CHINSTRAP_PENGUIN_SPAWN_BIOMES = TagKey.create(Registries.BIOME, RapscallionsAndRockhoppers.asResource("chinstrap_penguin_spawn_biomes"));
    }

}
