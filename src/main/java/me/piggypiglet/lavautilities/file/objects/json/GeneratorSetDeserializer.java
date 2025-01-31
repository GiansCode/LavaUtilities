package me.piggypiglet.lavautilities.file.objects.json;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import me.piggypiglet.lavautilities.file.objects.parts.Generator;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class GeneratorSetDeserializer implements JsonDeserializer<Set<Generator>> {
    private static final Type DESERIALZIED = new TypeToken<Map<String, Map<String, Object>>>(){}.getType();
    private static final Gson GSON = new Gson();

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public Set<Generator> deserialize(@NotNull final JsonElement json, @NotNull final Type typeOfT,
                                      @NotNull final JsonDeserializationContext context) {
        return ((Map<String, Map<String, Object>>) context.deserialize(json, DESERIALZIED)).entrySet().stream()
                .peek(entry -> entry.getValue().put("name", entry.getKey()))
                .map(Map.Entry::getValue)
                .map(GSON::toJsonTree)
                .map(generator -> (Generator) context.deserialize(generator, Generator.class))
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingInt(Generator::getPriority)).descendingSet()));
    }
}
