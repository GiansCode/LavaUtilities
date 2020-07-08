package me.piggypiglet.lavautilities.generation.destruction;

import com.google.inject.Inject;
import me.piggypiglet.lavautilities.generation.GeneratorManager;
import me.piggypiglet.lavautilities.utils.BlockUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.HashSet;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class GeneratorDestructionWatcher implements Runnable {
    @Inject private GeneratorManager generatorManager;

    @Override
    public void run() {
        new HashSet<>(generatorManager.getGenerators()).forEach(generator -> {
            final World world = Bukkit.getWorld(generator.getWorld());

            if (BlockUtils.block(generator.getFirstSource(), world).getType() == Material.LAVA
                    && BlockUtils.block(generator.getSecondSource(), world).getType() == Material.LAVA) return;

            generatorManager.unregister(generator);
        });
    }
}
