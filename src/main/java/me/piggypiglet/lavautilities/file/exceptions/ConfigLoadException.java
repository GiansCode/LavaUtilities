package me.piggypiglet.lavautilities.file.exceptions;

import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class ConfigLoadException extends RuntimeException {
    public ConfigLoadException(@NotNull final Exception exception) {
        super(exception);
    }
}
