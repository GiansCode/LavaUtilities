package me.piggypiglet.lavautilities.generation.destruction;

import com.google.inject.Inject;
import me.piggypiglet.lavautilities.generation.GeneratorManager;
import me.piggypiglet.lavautilities.generation.objects.GeneratorLocation;
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
    @Inject private GeneratorDestructor destructor;

    @Override
    public void run() {
        for (final GeneratorLocation generator : new HashSet<>(generatorManager.getGenerators())) {
            final World world = Bukkit.getWorld(generator.getWorld());

            final int[] block = generator.getBlock();
            if (!world.isChunkLoaded(block[0] >> 4, block[2] >> 4)) continue;

            if (BlockUtils.block(generator.getFirstSource(), world).getType() == Material.LAVA
                    && BlockUtils.block(generator.getSecondSource(), world).getType() == Material.LAVA
                    && BlockUtils.block(block, world).getType() != Material.LAVA) continue;

            destructor.destruct(generator);
        }
    }
}
