package me.piggypiglet.lavautilities.sources;

import com.google.common.collect.Sets;
import com.google.inject.Inject;
import me.piggypiglet.lavautilities.file.objects.Config;
import me.piggypiglet.lavautilities.schedule.Task;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Set;

import static org.bukkit.block.BlockFace.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class InfiniteLavaSourceListener implements Listener {
    @Inject private Config config;
    @Inject private Task task;

    private static final Set<BlockFace[]> BLOCKFACE_GROUPS = Sets.newHashSet(
            new BlockFace[]{NORTH, NORTH_EAST, EAST},
            new BlockFace[]{EAST, SOUTH, SOUTH_EAST},
            new BlockFace[]{WEST, SOUTH_WEST, SOUTH},
            new BlockFace[]{NORTH, NORTH_WEST, WEST},
            new BlockFace[]{NORTH, SOUTH},
            new BlockFace[]{WEST, EAST}
    );

    @EventHandler
    public void onLavaBucketFill(@NotNull final PlayerBucketFillEvent event) {
        final Block block = event.getBlock();

        if (config.getSourcePermissions().stream().noneMatch(event.getPlayer()::hasPermission)) return;
        if (!config.getEnabledWorlds().contains(block.getWorld().getName())) return;
        if (!performChecks(block)) replaceLava(block);
    }

    private void replaceLava(@NotNull final Block block) {
        task.sync(task -> {
            if (config.isRepeatSourceChecks() && performChecks(block)) return;

            block.setType(Material.LAVA);
        }, config.getSourceWaitTicks());
    }

    private static boolean performChecks(@NotNull final Block block) {
        if (block.getType() != Material.LAVA) return true;

        return BLOCKFACE_GROUPS.stream()
                .noneMatch(group -> Arrays.stream(group)
                        .allMatch(face -> block.getRelative(face).getType() == Material.LAVA));
    }
}
