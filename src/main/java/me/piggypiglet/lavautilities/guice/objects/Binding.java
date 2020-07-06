package me.piggypiglet.lavautilities.guice.objects;

import com.google.inject.TypeLiteral;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public class Binding<T> {
    private final Object type;
    private final T instance;

    public Binding(@NotNull final TypeLiteral<? super T> key, @NotNull final T instance) {
        this.type = key;
        this.instance = instance;
    }

    public Binding(@NotNull final Class<? super T> type, @NotNull final T instance) {
        this.type = type;
        this.instance = instance;
    }

    protected Binding(@NotNull final Binding<T> binding) {
        this.type = binding.type;
        this.instance = binding.instance;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public TypeLiteral<? super T> getKey() {
        return (TypeLiteral<? super T>) type;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public Class<? super T> getType() {
        return (Class<? super T>) type;
    }

    public boolean isKey() {
        return type instanceof TypeLiteral;
    }

    @NotNull
    public T getInstance() {
        return instance;
    }
}
