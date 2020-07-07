package me.piggypiglet.lavautilities.utils;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class LocationUtils {
    private LocationUtils() {
        throw new AssertionError("This class cannot be initialized.");
    }

    public static int[] fromBukkit(@NotNull final Location location) {
        return new int[]{location.getBlockX(), location.getBlockY(), location.getBlockZ()};
    }
}
