package me.piggypiglet.lavautilities.file.objects;

import com.google.gson.annotations.JsonAdapter;
import com.google.inject.Singleton;
import me.piggypiglet.lavautilities.file.annotations.File;
import me.piggypiglet.lavautilities.file.objects.json.GeneratorSetAdapter;
import me.piggypiglet.lavautilities.file.objects.parts.Generator;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
@File(
        internalPath = "/config.yml",
        externalPath = "config.yml"
)
@Singleton
public final class Config {
    @JsonAdapter(GeneratorSetAdapter.class) private Set<Generator> generators;
    private int generationCheckTicks;
    private List<String> sourcePermissions;
    private int sourceWaitTicks;
    private boolean repeatSourceChecks;
    private List<String> enabledWorlds;

    @NotNull
    public Set<Generator> getGenerators() {
        return generators;
    }

    public int getGenerationCheckTicks() {
        return generationCheckTicks;
    }

    @NotNull
    public List<String> getSourcePermissions() {
        return sourcePermissions;
    }

    public int getSourceWaitTicks() {
        return sourceWaitTicks;
    }

    public boolean isRepeatSourceChecks() {
        return repeatSourceChecks;
    }

    @NotNull
    public List<String> getEnabledWorlds() {
        return enabledWorlds;
    }

    @Override
    public String toString() {
        return "Config{" +
                "generations=" + generators +
                ", sourcePermissions=" + sourcePermissions +
                ", enabledWorlds=" + enabledWorlds +
                '}';
    }
}
