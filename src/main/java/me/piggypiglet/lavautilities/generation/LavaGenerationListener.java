package me.piggypiglet.lavautilities.generation;

import com.google.common.collect.Sets;
import com.google.inject.Inject;
import me.piggypiglet.lavautilities.file.objects.Config;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

import static org.bukkit.block.BlockFace.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class LavaGenerationListener implements Listener {
    private static final Set<BlockFace[]> BLOCKFACE_GROUPS = Sets.newHashSet(
            new BlockFace[]{NORTH, SOUTH},
            new BlockFace[]{EAST, WEST}
    );

    @Inject private Config config;

    @EventHandler
    public void onLavaGeneratorCreation(@NotNull final PlayerBucketEmptyEvent event) {

    }
}
