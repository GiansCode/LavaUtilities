package me.piggypiglet.lavautilities.file.registerables;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import me.piggypiglet.lavautilities.boot.framework.Registerable;
import me.piggypiglet.lavautilities.file.FileManager;
import me.piggypiglet.lavautilities.file.annotations.File;

import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class FilesRegisterable extends Registerable {
    @Inject @Named("files") private Set<Class<?>> fileClasses;
    @Inject private FileManager fileManager;

    @Override
    protected void execute() {
        for (final Class<?> clazz : fileClasses) {
            final File data = clazz.getAnnotation(File.class);
            fileManager.loadConfig(clazz, data.internalPath(), data.externalPath());
        }
    }
}
