package me.piggypiglet.lavautilities.generation.formation.registerables;

import com.google.inject.Inject;
import me.piggypiglet.lavautilities.boot.framework.Registerable;
import me.piggypiglet.lavautilities.generation.formation.GeneratorBlockFormer;
import me.piggypiglet.lavautilities.schedule.Task;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class FormationLoopRegisterable extends Registerable {
    private static final long LOOP_TICKS = 20;

    @Inject private Task task;
    @Inject private GeneratorBlockFormer generatorBlockFormer;

    @Override
    protected void execute() {
        task.asyncTimer(generatorBlockFormer, LOOP_TICKS);
    }
}
