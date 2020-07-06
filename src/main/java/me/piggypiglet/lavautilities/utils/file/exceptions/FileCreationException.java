package me.piggypiglet.lavautilities.utils.file.exceptions;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class FileCreationException extends IOException {
    public FileCreationException(@NotNull final File file) {
        super("Something went wrong when trying to create file: " + file.getPath());
    }
}
