package me.piggypiglet.lavautilities.generation.objects;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class GeneratorLocation {
    private final UUID world;
    private final int[] firstSource;
    private final int[] secondSource;
    private final int[] block;

    public GeneratorLocation(final UUID world, final int[] firstSource, final int[] secondSource, final int[] block) {
        this.world = world;
        this.firstSource = firstSource;
        this.secondSource = secondSource;
        this.block = block;
    }

    public UUID getWorld() {
        return world;
    }

    public int[] getFirstSource() {
        return firstSource;
    }

    public int[] getSecondSource() {
        return secondSource;
    }

    public int[] getBlock() {
        return block;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final GeneratorLocation that = (GeneratorLocation) o;
        return world.equals(that.world) &&
                Arrays.equals(block, that.block);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(world);
        result = 31 * result + Arrays.hashCode(block);
        return result;
    }

    @Override
    public String toString() {
        return "GeneratorLocation{" +
                "world=" + world +
                ", firstSource=" + Arrays.toString(firstSource) +
                ", secondSource=" + Arrays.toString(secondSource) +
                ", block=" + Arrays.toString(block) +
                '}';
    }
}
