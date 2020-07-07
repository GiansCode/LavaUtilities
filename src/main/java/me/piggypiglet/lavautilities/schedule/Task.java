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
    private final BukkitScheduler SCHEDULER = Bukkit.getScheduler();

    @Inject private JavaPlugin main;

    public void syncDelayed(@NotNull final Consumer<BukkitTask> task, final long ticks) {
        SCHEDULER.runTaskLater(main, task, ticks);
    }

    public void async(@NotNull final Consumer<BukkitTask> task) {
        SCHEDULER.runTaskAsynchronously(main, task);
    }

    public void asyncDelayed(@NotNull final Consumer<BukkitTask> task, final long delay) {
        SCHEDULER.runTaskLaterAsynchronously(main, task, delay);
    }

    public void asyncTimer(@NotNull final Runnable task, final long interval) {
        SCHEDULER.runTaskTimerAsynchronously(main, task, interval, interval);
    }
}
