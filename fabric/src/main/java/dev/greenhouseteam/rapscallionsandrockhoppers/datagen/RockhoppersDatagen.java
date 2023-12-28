package dev.greenhouseteam.rapscallionsandrockhoppers.datagen;

import dev.greenhouseteam.rapscallionsandrockhoppers.entity.PenguinType;
import dev.greenhouseteam.rapscallionsandrockhoppers.registry.RockhoppersBlocks;
import dev.greenhouseteam.rapscallionsandrockhoppers.registry.RockhoppersItems;
import dev.greenhouseteam.rapscallionsandrockhoppers.registry.RockhoppersTags;
import dev.greenhouseteam.rapscallionsandrockhoppers.util.RockhoppersResourceKeys;
import dev.greenhouseteam.rapscallionsandrockhoppers.util.WeightedHolderSet;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class RockhoppersDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(RockhoppersBiomeTagProvider::new);
        pack.addProvider(RockhoppersDynamicRegistryProvider::new);
        pack.addProvider(RockhoppersItemTagProvider::new);
        pack.addProvider(RockhoppersModelProvider::new);
        pack.addProvider(RockhoppersBlockLootProvider::new);

    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(RockhoppersResourceKeys.PENGUIN_TYPE_REGISTRY, v -> {});
    }

    public static class RockhoppersDynamicRegistryProvider extends FabricDynamicRegistryProvider {

        public RockhoppersDynamicRegistryProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void configure(HolderLookup.Provider registries, Entries entries) {
            Optional<Holder<SoundEvent>> idleSound = Optional.of(registries.lookup(Registries.SOUND_EVENT).orElseThrow().getOrThrow(RockhoppersResourceKeys.SoundEventKeys.PENGUIN_AMBIENT));
            Optional<Holder<SoundEvent>> hurtSound = Optional.of(registries.lookup(Registries.SOUND_EVENT).orElseThrow().getOrThrow(RockhoppersResourceKeys.SoundEventKeys.PENGUIN_HURT));
            Optional<Holder<SoundEvent>> deathSound = Optional.of(registries.lookup(Registries.SOUND_EVENT).orElseThrow().getOrThrow(RockhoppersResourceKeys.SoundEventKeys.PENGUIN_DEATH));
            Optional<Holder<SoundEvent>> waterJumpSound = Optional.of(registries.lookup(Registries.SOUND_EVENT).orElseThrow().getOrThrow(RockhoppersResourceKeys.SoundEventKeys.PENGUIN_JUMP));

            entries.add(RockhoppersResourceKeys.PenguinTypeKeys.ROCKHOPPER, new PenguinType(
                    Optional.empty(), Optional.empty(), List.of(new WeightedHolderSet<>(registries.lookupOrThrow(Registries.BIOME).getOrThrow(RockhoppersTags.BiomeTags.ROCKHOPPER_PENGUIN_SPAWN_BIOMES), 1)),
                    new PenguinType.PenguinSounds(idleSound, hurtSound, deathSound, waterJumpSound), Optional.empty()));
            entries.add(RockhoppersResourceKeys.PenguinTypeKeys.CHINSTRAP, new PenguinType(
                    Optional.empty(), Optional.empty(), List.of(new WeightedHolderSet<>(registries.lookupOrThrow(Registries.BIOME).getOrThrow(RockhoppersTags.BiomeTags.CHINSTRAP_PENGUIN_SPAWN_BIOMES), 1)),
                    new PenguinType.PenguinSounds(idleSound, hurtSound, deathSound, waterJumpSound), Optional.empty()));
            entries.add(RockhoppersResourceKeys.PenguinTypeKeys.GUNTER, new PenguinType(
                    Optional.empty(), Optional.empty(), List.of(),
                    new PenguinType.PenguinSounds(idleSound, hurtSound, deathSound, waterJumpSound), Optional.of("Gunter")));
        }

        @Override
        public String getName() {
            return "Rapscallions and Rockhoppers Dynamic Registries";
        }
    }

    public static class RockhoppersModelProvider extends FabricModelProvider {

        public RockhoppersModelProvider(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
            createEgg(RockhoppersBlocks.PENGUIN_EGG, blockStateModelGenerator);
        }

        @Override
        public void generateItemModels(ItemModelGenerators itemModelGenerator) {
            itemModelGenerator.generateFlatItem(RockhoppersItems.PENGUIN_EGG, ModelTemplates.FLAT_ITEM);
            itemModelGenerator.generateFlatItem(RockhoppersItems.PENGUIN_SPAWN_EGG, RockhoppersModelTemplates.SPAWN_EGG);

        }
        public void createEgg(Block egg, BlockModelGenerators blockModelGenerators) {
            TextureMapping textureMapping = RockhoppersTextureMappings.createEggMapping(egg);
            TextureMapping slightlyCrackedTextureMapping = RockhoppersTextureMappings.createEggMapping(egg, "_slightly_cracked");
            TextureMapping veryCrackedTextureMapping = RockhoppersTextureMappings.createEggMapping(egg, "_very_cracked");
            var eggModel = RockhoppersModelTemplates.EGG.create(egg, textureMapping, blockModelGenerators.modelOutput);
            var slightlyCrackedEggModel = RockhoppersModelTemplates.EGG.createWithSuffix(egg, "_slightly_cracked", slightlyCrackedTextureMapping, blockModelGenerators.modelOutput);
            var veryCrackedEggModel = RockhoppersModelTemplates.EGG.createWithSuffix(egg, "_very_cracked", veryCrackedTextureMapping, blockModelGenerators.modelOutput);
            var multiVariant = MultiVariantGenerator.multiVariant(egg)
                            .with(PropertyDispatch.property(BlockStateProperties.HATCH)
                                            .select(0, Variant.variant().with(VariantProperties.MODEL, eggModel))
                                            .select(1, Variant.variant().with(VariantProperties.MODEL, slightlyCrackedEggModel))
                                            .select(2, Variant.variant().with(VariantProperties.MODEL, veryCrackedEggModel)));
            blockModelGenerators.blockStateOutput.accept(multiVariant);
        }
    }
    public static class RockhoppersBlockLootProvider extends FabricBlockLootTableProvider {
        protected RockhoppersBlockLootProvider(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        @Override
        public void generate() {
            dropWhenSilkTouch(RockhoppersBlocks.PENGUIN_EGG);
        }
    }

    public static class RockhoppersItemTagProvider extends FabricTagProvider.ItemTagProvider {

        public RockhoppersItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            this.tag(RockhoppersTags.ItemTags.PENGUIN_BREED_ITEMS)
                    .add(this.reverseLookup(Items.INK_SAC), this.reverseLookup(Items.GLOW_INK_SAC));
            this.tag(RockhoppersTags.ItemTags.PENGUIN_TEMPT_ITEMS)
                    .add(this.reverseLookup(Items.COD), this.reverseLookup(Items.SALMON), this.reverseLookup(Items.TROPICAL_FISH))
                    .addTag(RockhoppersTags.ItemTags.PENGUIN_BREED_ITEMS);
        }
    }

    public static class RockhoppersBiomeTagProvider extends FabricTagProvider<Biome> {

        public RockhoppersBiomeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, Registries.BIOME, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            this.tag(RockhoppersTags.BiomeTags.ROCKHOPPER_PENGUIN_SPAWN_BIOMES).add(Biomes.STONY_SHORE);
            this.tag(RockhoppersTags.BiomeTags.CHINSTRAP_PENGUIN_SPAWN_BIOMES).add(Biomes.FROZEN_OCEAN);
        }
    }
}
