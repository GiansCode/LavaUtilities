package me.piggypiglet.lavautilities.generation.data.registerables;

import com.google.inject.Inject;
import me.piggypiglet.lavautilities.boot.framework.Registerable;
import me.piggypiglet.lavautilities.file.objects.Config;
import me.piggypiglet.lavautilities.generation.data.DataSaver;
import me.piggypiglet.lavautilities.schedule.Task;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class DataSaveRegisterable extends Registerable {
    @Inject private Task task;
    @Inject private DataSaver dataSaver;
    @Inject private Config config;

    @Override
    protected void execute() {
        task.asyncTimer(dataSaver, config.getGenerationDataSaveTicks());
    }
}
