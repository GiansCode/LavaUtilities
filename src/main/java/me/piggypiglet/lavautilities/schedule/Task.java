package me.piggypiglet.lavautilities.schedule;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class Task {
    private final BukkitScheduler scheduler = Bukkit.getScheduler();

    @Inject private JavaPlugin main;

    public void sync(@NotNull final Consumer<BukkitTask> function, final long ticks) {
        scheduler.runTaskLater(main, function, ticks);
    }
}
