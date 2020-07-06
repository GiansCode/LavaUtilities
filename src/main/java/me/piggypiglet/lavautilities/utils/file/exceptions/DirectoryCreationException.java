package me.piggypiglet.lavautilities.utils.file.exceptions;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class DirectoryCreationException extends IOException {
    public DirectoryCreationException(@NotNull final File file) {
        super("Something went wrong when trying to create directory: " + file.getPath());
    }
}
