package me.piggypiglet.lavautilities.generation.creation;

import com.google.inject.Inject;
import me.piggypiglet.lavautilities.generation.GeneratorManager;
import me.piggypiglet.lavautilities.generation.objects.GeneratorLocation;
import me.piggypiglet.lavautilities.utils.LocationUtils;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class GeneratorRegistrar {
    @Inject private GeneratorManager manager;

    public void attemptRegistration(@NotNull final int[] relativeOpening, @NotNull final int[] relativeSource,
                                    @NotNull final Block block) {
        final int[] opening = LocationUtils.fromBukkit(block.getRelative(relativeOpening[0], relativeOpening[1], relativeOpening[2])
                .getLocation());
        final int[] source = LocationUtils.fromBukkit(block.getRelative(relativeSource[0], relativeSource[1], relativeSource[2])
                .getLocation());
        final int[] target = LocationUtils.fromBukkit(block.getLocation());

        final int xDiff = Math.abs(target[0] - source[0]);
        final int yDiff = Math.abs(target[1] - source[1]);
        final int zDiff = Math.abs(target[2] - source[2]);

        if (relativeOpening[0] < xDiff || relativeOpening[1] < yDiff || relativeOpening[2] < zDiff) {
            manager.register(new GeneratorLocation(block.getWorld().getUID(), target, source, opening));
        }
    }
}
