package me.piggypiglet.lavautilities.file.objects;

import com.google.gson.annotations.JsonAdapter;
import com.google.inject.Singleton;
import me.piggypiglet.lavautilities.file.annotations.File;
import me.piggypiglet.lavautilities.file.objects.json.GeneratorSetDeserializer;
import me.piggypiglet.lavautilities.file.objects.json.time.TimeDeserializer;
import me.piggypiglet.lavautilities.file.objects.json.time.TimeRangeDeserializer;
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
    @JsonAdapter(GeneratorSetDeserializer.class) private Set<Generator> generators;
    @JsonAdapter(TimeDeserializer.class) private long generationCheckTime;
    @JsonAdapter(TimeRangeDeserializer.class) private long[] generationIntervalTime;
    private List<String> sourcePermissions;
    @JsonAdapter(TimeDeserializer.class) private long sourceWaitTime;
    private boolean repeatSourceChecks;
    private List<String> enabledWorlds;

    @NotNull
    public Set<Generator> getGenerators() {
        return generators;
    }

    public long getGenerationCheckTicks() {
        return generationCheckTime;
    }

    @NotNull
    public long[] getGenerationIntervalTicks() {
        return generationIntervalTime;
    }

    @NotNull
    public List<String> getSourcePermissions() {
        return sourcePermissions;
    }

    public long getSourceWaitTicks() {
        return sourceWaitTime;
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
