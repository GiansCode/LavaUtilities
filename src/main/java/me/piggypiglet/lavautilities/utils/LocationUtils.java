package me.piggypiglet.lavautilities.utils;

import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class LocationUtils {
    private LocationUtils() {
        throw new AssertionError("This class cannot be initialized.");
    }

    public static int[] location(@NotNull final Block block) {
        return new int[]{block.getX(), block.getY(), block.getZ()};
    }
}
