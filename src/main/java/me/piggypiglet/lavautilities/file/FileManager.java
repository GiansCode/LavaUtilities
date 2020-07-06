package me.piggypiglet.lavautilities.file;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import me.piggypiglet.lavautilities.file.exceptions.ConfigLoadException;
import me.piggypiglet.lavautilities.utils.file.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class FileManager {
    private static final Yaml YAML = new Yaml();

    private final String dataFolder;
    private final Gson gson;

    @Inject
    public FileManager(@NotNull final JavaPlugin main, @NotNull @Named("files") final Map<Class<?>, Object> fileObjects) {
        dataFolder = main.getDataFolder().getPath();

        final AtomicReference<GsonBuilder> builder = new AtomicReference<>(new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES));

        fileObjects.forEach((clazz, instance) -> builder.set(builder.get()
                .registerTypeAdapter(clazz, instanceCreator(instance))));

        gson = builder.get().create();
    }

    @NotNull
    private static <T> InstanceCreator<T> instanceCreator(@NotNull final T instance) {
        return type -> instance;
    }

    public void loadConfig(@NotNull final Class<?> type, @NotNull final String internalPath,
                           @NotNull final String externalPath) throws ConfigLoadException {
        try {
            gson.fromJson(gson.toJsonTree(YAML.load(FileUtils.readFile(createFile(internalPath, externalPath)))), type);

        } catch (final Exception exception) {
            throw new ConfigLoadException(exception);
        }
    }

    @NotNull
    private File createFile(@NotNull final String internalPath, @NotNull final String externalPath) throws IOException {
        return FileUtils.createFile(internalPath, dataFolder + '/' + externalPath);
    }
}
