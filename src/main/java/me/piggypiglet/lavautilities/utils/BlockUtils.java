package me.piggypiglet.lavautilities.utils;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class BlockUtils {
    private BlockUtils() {
        throw new AssertionError("This class cannot be initialized.");
    }

    @NotNull
    public static int[] location(@NotNull final Block block) {
        return new int[]{block.getX(), block.getY(), block.getZ()};
    }

    @NotNull
    public static Block block(@NotNull final int[] location, @NotNull final World world) {
        return world.getBlockAt(location[0], location[1], location[2]);
    }
}
