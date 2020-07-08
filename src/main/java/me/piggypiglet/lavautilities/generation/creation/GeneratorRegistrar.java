package me.piggypiglet.lavautilities.generation.creation;

import com.google.inject.Inject;
import me.piggypiglet.lavautilities.generation.GeneratorManager;
import me.piggypiglet.lavautilities.generation.formation.GeneratorBlockFormer;
import me.piggypiglet.lavautilities.generation.objects.GeneratorLocation;
import me.piggypiglet.lavautilities.utils.BlockUtils;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class GeneratorRegistrar {
    @Inject private GeneratorManager manager;
    @Inject private GeneratorBlockFormer former;

    public void attemptRegistration(@NotNull final int[] relativeOpening, @NotNull final int[] relativeSource,
                                    @NotNull final Block block) {
        final int[] opening = BlockUtils.location(block.getRelative(relativeOpening[0], relativeOpening[1], relativeOpening[2]));
        final int[] source = BlockUtils.location(block.getRelative(relativeSource[0], relativeSource[1], relativeSource[2]));
        final int[] target = BlockUtils.location(block);

        final int xDiff = Math.abs(target[0] - source[0]);
        final int yDiff = Math.abs(target[1] - source[1]);
        final int zDiff = Math.abs(target[2] - source[2]);

        if (relativeOpening[0] < xDiff || relativeOpening[1] < yDiff || relativeOpening[2] < zDiff) {
            final GeneratorLocation location = new GeneratorLocation(block.getWorld().getUID(), target, source, opening);

            manager.register(location);
            former.defaultForm(location);
        }
    }
}
