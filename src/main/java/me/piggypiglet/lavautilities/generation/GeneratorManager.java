package me.piggypiglet.lavautilities.generation;

import com.google.inject.Singleton;
import me.piggypiglet.lavautilities.file.annotations.File;
import me.piggypiglet.lavautilities.generation.objects.GeneratorLocation;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
@File(
        internalPath = "/data.yml",
        externalPath = "data.yml"
)
@Singleton
public final class GeneratorManager {
    private final Set<GeneratorLocation> generators = new HashSet<>();

    public synchronized void register(@NotNull final GeneratorLocation location) {
        generators.add(location);
    }

    public synchronized void unregister(@NotNull final GeneratorLocation location) {
        generators.remove(location);
    }

    @NotNull
    public Set<GeneratorLocation> getGenerators() {
        return generators;
    }
}
