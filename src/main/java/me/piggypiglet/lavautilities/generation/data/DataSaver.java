package me.piggypiglet.lavautilities.generation.data;

import com.google.inject.Inject;
import me.piggypiglet.lavautilities.file.FileManager;
import me.piggypiglet.lavautilities.file.annotations.File;
import me.piggypiglet.lavautilities.generation.GeneratorManager;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class DataSaver implements Runnable {
    private static final String PATH = GeneratorManager.class.getAnnotation(File.class).externalPath();

    @Inject private FileManager fileManager;
    @Inject private GeneratorManager data;

    @Override
    public void run() {
        fileManager.save(data, PATH);
    }
}
