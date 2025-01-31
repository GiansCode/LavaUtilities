package me.piggypiglet.lavautilities.generation.destruction.registerables;

import com.google.inject.Inject;
import me.piggypiglet.lavautilities.boot.framework.Registerable;
import me.piggypiglet.lavautilities.file.objects.Config;
import me.piggypiglet.lavautilities.generation.destruction.GeneratorDestructionWatcher;
import me.piggypiglet.lavautilities.schedule.Task;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.met
// ------------------------------
public final class DestructionLoopRegisterable extends Registerable {
    @Inject private Task task;
    @Inject private GeneratorDestructionWatcher watcher;
    @Inject private Config config;

    @Override
    protected void execute() {
        task.asyncTimer(watcher, config.getGenerationDestructionCheckTicks());
    }
}
