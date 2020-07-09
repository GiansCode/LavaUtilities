package me.piggypiglet.lavautilities.generation.formation;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.piggypiglet.lavautilities.file.objects.Config;
import me.piggypiglet.lavautilities.file.objects.parts.Generator;
import me.piggypiglet.lavautilities.generation.GeneratorManager;
import me.piggypiglet.lavautilities.generation.objects.GeneratorLocation;
import me.piggypiglet.lavautilities.schedule.Task;
import me.piggypiglet.lavautilities.utils.BlockUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class GeneratorBlockFormer implements Listener {
    @Inject private GeneratorManager generatorManager;
    @Inject private Task task;
    @Inject private Config config;

    private final Set<GeneratorLocation> forming = new HashSet<>();

    public void defaultForm(@NotNull final GeneratorLocation location) {
        form(location, config.getGenerationDefaultBlock());
    }

    public void form(@NotNull final Generator generator, @NotNull final GeneratorLocation location) {
        form(location, generator.getBlocks().get());
    }

    public void cancel(@NotNull final GeneratorLocation location) {
        forming.remove(location);
    }

    @EventHandler
    public void onPurposefulGeneratedBlockDestruction(@NotNull final BlockBreakEvent event) {
        final int[] block = BlockUtils.location(event.getBlock());
        final UUID world = event.getBlock().getWorld().getUID();

        final Optional<GeneratorLocation> optionalLocation = generatorManager.getGenerators().stream()
                .filter(generator -> Arrays.equals(generator.getBlock(), block) && generator.getWorld().equals(world))
                .findFirst();

        if (!optionalLocation.isPresent()) return;

        task.async(task -> {
            final Optional<Generator> optionalGenerator = config.getGenerators().stream()
                    .filter(generator -> event.getPlayer().hasPermission("lavautilities.generator." + generator.getName()))
                    .findFirst();

            if (optionalGenerator.isPresent()) {
                form(optionalGenerator.get(), optionalLocation.get());
            } else {
                defaultForm(optionalLocation.get());
            }
        });
    }

    private void form(@NotNull final GeneratorLocation location, @NotNull final Material material) {
        final int[] block = location.getBlock();
        final long[] tickRange = config.getGenerationIntervalTicks();
        forming.add(location);

        task.syncDelayed(task -> {
            if (!forming.contains(location)) return;

            forming.remove(location);

            final Block opening = Bukkit.getWorld(location.getWorld()).getBlockAt(block[0], block[1], block[2]);

            if (opening.getType() == Material.AIR) {
                opening.setType(material);
            }
        }, ThreadLocalRandom.current().nextLong(tickRange[0], tickRange[1]));
    }
}
