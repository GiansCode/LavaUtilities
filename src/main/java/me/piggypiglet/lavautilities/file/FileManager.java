package me.piggypiglet.lavautilities.file;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import me.piggypiglet.lavautilities.file.exceptions.FileLoadException;
import me.piggypiglet.lavautilities.file.exceptions.FileSaveException;
import me.piggypiglet.lavautilities.utils.file.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class FileManager {
    private static final Yaml YAML = new Yaml();
    private static final Type MAP = new TypeToken<Map<String, Object>>(){}.getType();

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

    public void loadFile(@NotNull final Class<?> type, @NotNull final String internalPath,
                         @NotNull final String externalPath) throws FileLoadException {
        try {
            gson.fromJson(gson.toJsonTree(YAML.load(FileUtils.readFile(createFile(internalPath, externalPath)))), type);
        } catch (final Exception exception) {
            throw new FileLoadException(exception);
        }
    }

    public void save(@NotNull final Object file, @NotNull final String externalPath) {
        try {
            writeFile(externalPath, YAML.dump(gson.fromJson(gson.toJsonTree(file), MAP)));
        } catch (final Exception exception) {
            throw new FileSaveException(exception);
        }
    }

    @NotNull
    private File createFile(@NotNull final String internalPath, @NotNull final String externalPath) throws IOException {
        return FileUtils.createFile(internalPath, dataFolder + '/' + externalPath);
    }

    private void writeFile(@NotNull final String path, @NotNull final String content) throws IOException {
        FileUtils.writeFile(dataFolder + '/' + path, content);
    }
}
