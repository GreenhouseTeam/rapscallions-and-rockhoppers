package dev.greenhouseteam.rapscallionsandrockhoppers.componability;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class BoatDataAttachment implements IBoatData, INBTSerializable<CompoundTag> {
    private final Set<UUID> penguins = new HashSet<>();
    private final Set<UUID> nextLinkedBoats = new HashSet<>();
    private final Set<UUID> previousLinkedBoats = new HashSet<>();

    private @Nullable UUID linkedPlayer;

    @Override
    public Set<UUID> getNextLinkedBoatUuids() {
        return this.nextLinkedBoats;
    }

    @Override
    public Set<UUID> getPreviousLinkedBoatUuids() {
        return this.previousLinkedBoats;
    }

    @Override
    public void clearNextLinkedBoatUuids() {
        this.nextLinkedBoats.clear();
    }

    @Override
    public void clearPreviousLinkedBoatUuids() {
        this.previousLinkedBoats.clear();
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
    public void addNextLinkedBoat(@Nullable UUID boat) {
        this.nextLinkedBoats.add(boat);
    }

    @Override
    public void removeNextLinkedBoat(@Nullable UUID boat) {
        this.nextLinkedBoats.remove(boat);
    }

    @Override
    public void addPreviousLinkedBoat(@Nullable UUID boat) {
        this.previousLinkedBoats.add(boat);
    }

    @Override
    public void removePreviousLinkedBoat(@Nullable UUID boat) {
        this.previousLinkedBoats.remove(boat);
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