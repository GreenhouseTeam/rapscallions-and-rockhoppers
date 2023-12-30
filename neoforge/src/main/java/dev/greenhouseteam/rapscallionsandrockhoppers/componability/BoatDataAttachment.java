package dev.greenhouseteam.rapscallionsandrockhoppers.componability;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BoatDataAttachment implements IBoatData, INBTSerializable<CompoundTag> {
    private final List<UUID> penguins = new ArrayList<>();
    @Unique
    private @Nullable UUID nextLinkedBoat;
    @Unique
    private @Nullable UUID previousLinkedBoat;
    @Unique
    private @Nullable UUID linkedPlayer;

    @Override
    public @Nullable UUID getNextLinkedBoatUuid() {
        return nextLinkedBoat;
    }

    @Override
    public @Nullable UUID getPreviousLinkedBoatUuid() {
        return previousLinkedBoat;
    }

    @Override
    public @Nullable UUID getLinkedPlayerUuid() {
        return linkedPlayer;
    }

    @Override
    public void setLinkedPlayer(@Nullable UUID player) {
        this.linkedPlayer = player;
    }

    @Override
    public void setNextLinkedBoat(UUID boat) {
        this.nextLinkedBoat = boat;
    }

    @Override
    public void setPreviousLinkedBoat(UUID boat) {
        this.previousLinkedBoat = boat;
    }

    @Override
    public List<UUID> getFollowingPenguins() {
        return List.copyOf(this.penguins);
    }

    @Override
    public int penguinCount() {
        return this.penguins.size();
    }

    @Override
    public void addFollowingPenguin(UUID penguinUUID) {
        this.penguins.add(penguinUUID);
    }

    @Override
    public void removeFollowingPenguin(UUID penguinUUID) {
        this.penguins.remove(penguinUUID);
    }

    @Override
    public void clearFollowingPenguins() {
        this.penguins.clear();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        this.serialize(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        this.deserialize(tag);
    }
}