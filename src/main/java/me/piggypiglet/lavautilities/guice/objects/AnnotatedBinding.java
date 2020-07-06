package me.piggypiglet.lavautilities.guice.objects;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class AnnotatedBinding<T> extends Binding<T> {
    private final Object annotation;

    public AnnotatedBinding(@NotNull final Binding<T> binding, @NotNull final Annotation annotation) {
        super(binding);
        this.annotation = annotation;
    }

    public AnnotatedBinding(@NotNull final Binding<T> binding, @NotNull final Class<? extends Annotation> annotation) {
        super(binding);
        this.annotation = annotation;
    }

    @NotNull
    public Annotation getAnnotationInstance() {
        return (Annotation) annotation;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public Class<? extends Annotation> getAnnotationClass() {
        return (Class<? extends Annotation>) annotation;
    }

    public boolean isAnnotationInstance() {
        return annotation instanceof Annotation;
    }
}
