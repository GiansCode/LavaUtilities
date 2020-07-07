package me.piggypiglet.lavautilities.file.objects.json.time;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import org.jetbrains.annotations.NotNull;
import sh.okx.timeapi.TimeAPI;

import java.lang.reflect.Type;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class TimeDeserializer implements JsonDeserializer<Long> {
    @NotNull
    @Override
    public Long deserialize(@NotNull final JsonElement json, @NotNull final Type typeOfT,
                            @NotNull final JsonDeserializationContext context) {
        final String time = json.getAsString();

        try {
            return Long.parseLong(time);
        } catch (final Exception exception) {
            return new TimeAPI(time).getTicks();
        }
    }
}
