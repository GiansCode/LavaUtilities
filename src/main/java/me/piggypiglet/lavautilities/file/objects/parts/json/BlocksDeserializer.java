package me.piggypiglet.lavautilities.file.objects.parts.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import me.piggypiglet.lavautilities.utils.collection.WeightedList;
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
public final class BlocksDeserializer implements JsonDeserializer<WeightedList<Material>> {
    private static final Type DESERIALIZED = new TypeToken<List<String>>(){}.getType();
    private static final Pattern BLOCK_DELIMITER = Pattern.compile(";");

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public WeightedList<Material> deserialize(@NotNull final JsonElement json, @NotNull final Type typeOfT,
                                                @NotNull final JsonDeserializationContext context) {
        return new WeightedList<>(((List<String>) context.deserialize(json, DESERIALIZED)).stream()
                .map(BLOCK_DELIMITER::split)
                .collect(Collectors.toMap(block -> Material.valueOf(block[0].toUpperCase()), block -> Integer.parseInt(block[1]))));
    }
}
