package me.piggypiglet.lavautilities.boot.framework;

import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import me.piggypiglet.lavautilities.guice.objects.AnnotatedBinding;
import me.piggypiglet.lavautilities.guice.objects.Binding;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public abstract class Registerable {
    protected Injector injector;

    private final Set<Binding<?>> bindings = new HashSet<>();
    private final Set<Class<?>> staticInjections = new HashSet<>();

    protected abstract void execute();

    protected <T> void addBinding(@NotNull final Class<? super T> interfaze, @NotNull final T instance) {
        bindings.add(new Binding<>(interfaze, instance));
    }

    protected <T> void addBinding(@NotNull final TypeLiteral<? super T> type, @NotNull final T instance) {
        bindings.add(new Binding<>(type, instance));
    }

    protected <T> void addBinding(@NotNull final Class<? super T> interfaze, @NotNull final Annotation annotation,
                                  @NotNull final T instance) {
        bindings.add(new AnnotatedBinding<>(new Binding<>(interfaze, instance), annotation));
    }

    protected <T> void addBinding(@NotNull final Class<? super T> interfaze, @NotNull final Class<? extends Annotation> annotation,
                                  @NotNull final T instance) {
        bindings.add(new AnnotatedBinding<>(new Binding<>(interfaze, instance), annotation));
    }

    protected <T> void addBinding(@NotNull final TypeLiteral<? super T> type, @NotNull final Annotation annotation,
                                  @NotNull final T instance) {
        bindings.add(new AnnotatedBinding<>(new Binding<>(type, instance), annotation));
    }

    protected <T> void addBinding(@NotNull final TypeLiteral<? super T> type, @NotNull final Class<? extends Annotation> annotation,
                                  @NotNull final T instance) {
        bindings.add(new AnnotatedBinding<>(new Binding<>(type, instance), annotation));
    }

    protected void requestStaticInjections(@NotNull final Class<?>... classes) {
        staticInjections.addAll(Arrays.asList(classes));
    }

    public void run(@NotNull final Injector injector) {
        this.injector = injector;
        execute();
    }

    @NotNull
    public Set<Binding<?>> getBindings() {
        return bindings;
    }

    @NotNull
    public Class<?>[] getStaticInjections() {
        return staticInjections.toArray(new Class<?>[]{});
    }
}
