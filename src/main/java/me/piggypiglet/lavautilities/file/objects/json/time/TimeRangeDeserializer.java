package me.piggypiglet.lavautilities.file.objects.json.time;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import org.jetbrains.annotations.NotNull;
import sh.okx.timeapi.TimeAPI;

import java.lang.reflect.Type;
import java.util.Arrays;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class TimeRangeDeserializer implements JsonDeserializer<long[]> {
    @NotNull
    @Override
    public long[] deserialize(@NotNull final JsonElement json, @NotNull final Type typeOfT,
                            @NotNull final JsonDeserializationContext context) {
        return Arrays.stream(json.getAsString().split("-"))
                .map(String::trim)
                .mapToLong(time -> {
                    try {
                        return Long.parseLong(time);
                    } catch (final Exception exception) {
                        return new TimeAPI(time).getTicks();
                    }
                })
                .toArray();
    }
}
