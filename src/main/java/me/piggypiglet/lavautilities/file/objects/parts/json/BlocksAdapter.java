package me.piggypiglet.lavautilities.file.objects.parts.json;

import com.github.lewysdavies.probabilitymap.ProbabilityMap;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class BlocksAdapter implements JsonSerializer<ProbabilityMap<Material>>, JsonDeserializer<ProbabilityMap<Material>> {
    private static final Type DESERIALIZED = new TypeToken<List<String>>(){}.getType();
    private static final Pattern BLOCK_DELIMITER = Pattern.compile(";");

    @NotNull
    @Override
    public JsonElement serialize(@NotNull final ProbabilityMap<Material> src, @NotNull final Type typeOfSrc,
                                 @NotNull final JsonSerializationContext context) {
        return context.serialize(src.getAll().entrySet().stream()
                .map(block -> block.getKey() + ";" + block.getValue())
                .collect(Collectors.toList()));
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public ProbabilityMap<Material> deserialize(@NotNull final JsonElement json, @NotNull final Type typeOfT,
                                                @NotNull final JsonDeserializationContext context) {
        return new ProbabilityMap<>(((List<String>) context.deserialize(json, DESERIALIZED)).stream()
                .map(BLOCK_DELIMITER::split)
                .collect(Collectors.toMap(block -> Material.valueOf(block[0].toUpperCase()), block -> Integer.parseInt(block[1]))));
    }
}
