package me.piggypiglet.lavautilities.scanning.framework;

import me.piggypiglet.lavautilities.utils.annotation.wrapper.AnnotationRules;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public interface Scanner {
    @NotNull
    <T> Set<Class<? extends T>> getSubTypesOf(@NotNull final Class<T> type);

    @NotNull
    Set<Class<?>> getClassesAnnotatedWith(@NotNull final AnnotationRules rules);
}
