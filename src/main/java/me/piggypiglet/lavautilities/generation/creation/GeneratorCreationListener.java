package me.piggypiglet.lavautilities.generation.creation;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import me.piggypiglet.lavautilities.schedule.Task;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class GeneratorCreationListener implements Listener {
    private static final List<int[]> RELATIVE_OPENINGS = Lists.newArrayList(
            new int[]{1, 0, 0},
            new int[]{-1, 0, 0},
            new int[]{0, 0, 1},
            new int[]{0, 0, -1}
    );

    private static final List<int[]> RELATIVE_SOURCES = Lists.newArrayList(
            new int[]{2, 0, 0},
            new int[]{-2, 0, 0},
            new int[]{0, 0, 2},
            new int[]{0, 0, -2}
    );

    @Inject private GeneratorRegistrar registrar;
    @Inject private Task task;

    @EventHandler
    public void onLavaGeneratorCreation(@NotNull final BlockFromToEvent event) {
        task.async(task -> {
            onCreation(event.getBlock());
            onCreation(event.getToBlock());
        });
    }

    @EventHandler
    public void onLavaGeneratorCreation(@NotNull final PlayerBucketEmptyEvent event) {
        task.async(task -> onCreation(event.getBlock()));
    }

    private void onCreation(@NotNull final Block block) {
        if (block.getType() != Material.LAVA) return;

        final Optional<int[]> optionalOpening = RELATIVE_OPENINGS.stream()
                .filter(relativeFilter(block, Material.AIR))
                .findAny();

        if (!optionalOpening.isPresent()) return;

        final Optional<int[]> optionalSource = RELATIVE_SOURCES.stream()
                .filter(relativeFilter(block, Material.LAVA))
                .findAny();

        if (!optionalSource.isPresent()) return;

        registrar.attemptRegistration(optionalOpening.get(), optionalSource.get(), block);
    }

    private static Predicate<? super int[]> relativeFilter(@NotNull final Block block, @NotNull final Material material) {
        return relative -> block.getRelative(relative[0], relative[1], relative[2]).getType() == material;
    }
}
