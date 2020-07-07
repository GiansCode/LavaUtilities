package me.piggypiglet.lavautilities.generation;

import com.google.inject.Singleton;
import me.piggypiglet.lavautilities.generation.objects.GeneratorLocation;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class GeneratorManager {
    private final Set<GeneratorLocation> generators = new HashSet<>();

    public synchronized void register(@NotNull final GeneratorLocation location) {
        generators.add(location);
    }

    @NotNull
    public Set<GeneratorLocation> getGenerators() {
        return generators;
    }
}
