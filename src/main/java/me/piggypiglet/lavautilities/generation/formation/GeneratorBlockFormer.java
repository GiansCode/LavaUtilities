package me.piggypiglet.lavautilities.generation.formation;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.piggypiglet.lavautilities.file.objects.Config;
import me.piggypiglet.lavautilities.generation.GeneratorManager;
import me.piggypiglet.lavautilities.generation.objects.GeneratorLocation;
import me.piggypiglet.lavautilities.schedule.Task;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class GeneratorBlockFormer implements Runnable {
    @Inject private GeneratorManager generatorManager;
    @Inject private Task task;
    @Inject private Config config;

    private final Set<GeneratorLocation> formingGenerators = new HashSet<>();

    @Override
    public void run() {
        generatorManager.getGenerators().stream()
                .filter(generator -> !formingGenerators.contains(generator))
                .forEach(generator -> {
                    formingGenerators.add(generator);

                    task.syncDelayed(asyncTask -> {
                        formingGenerators.remove(generator);

                        final int[] opening = generator.getBlock();
                        Bukkit.getWorld(generator.getWorld())
                                .getBlockAt(opening[0], opening[1], opening[2]).setType(Material.STONE);
                    }, ThreadLocalRandom.current().nextLong(config.getGenerationIntervalTicks()[0], config.getGenerationIntervalTicks()[1]));
                });
    }
}
