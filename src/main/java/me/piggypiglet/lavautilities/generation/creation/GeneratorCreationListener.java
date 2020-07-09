package me.piggypiglet.lavautilities.generation.creation;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import me.piggypiglet.lavautilities.file.objects.Config;
import me.piggypiglet.lavautilities.schedule.Task;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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

    @Inject private Task task;
    @Inject private GeneratorRegistrar registrar;
    @Inject private Config config;

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
        if (!config.getEnabledWorlds().contains(block.getWorld().getName())) return;

        if (block.getType() != Material.LAVA) return;

        for (int i = 0; i < RELATIVE_SOURCES.size(); ++i) {
            final int[] relativeOpening = RELATIVE_OPENINGS.get(i);
            final int[] relativeSource = RELATIVE_SOURCES.get(i);

            if (notRelativeMatch(relativeOpening, block, Material.AIR)) continue;
            if (notRelativeMatch(relativeSource, block, Material.LAVA)) continue;

            registrar.attemptRegistration(relativeOpening, relativeSource, block);
            break;
        }
    }

    private static boolean notRelativeMatch(@NotNull final int[] relative, @NotNull final Block block,
                                            @NotNull final Material material) {
        return block.getRelative(relative[0], relative[1], relative[2]).getType() != material;
    }
}
