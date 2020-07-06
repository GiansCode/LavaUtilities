package me.piggypiglet.lavautilities.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.binder.LinkedBindingBuilder;
import me.piggypiglet.lavautilities.guice.objects.AnnotatedBinding;
import me.piggypiglet.lavautilities.guice.objects.Binding;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class DynamicModule extends AbstractModule {
    private final Set<Binding<?>> bindings;
    private final Class<?>[] staticInjections;

    public DynamicModule(@NotNull final Set<Binding<?>> bindings, @NotNull final Class<?>... staticInjections) {
        this.bindings = bindings;
        this.staticInjections = staticInjections;
    }

    @Override
    protected void configure() {
        bindings.forEach(this::bind);
        requestStaticInjection(staticInjections);
    }

    private <T> void bind(@NotNull final Binding<T> binding) {
        final AnnotatedBindingBuilder<? super T> bind;

        if (binding.isKey()) {
            bind = bind(binding.getKey());
        } else {
            bind = bind(binding.getType());
        }

        final AnnotatedBinding<T> annotatedBinding = binding instanceof AnnotatedBinding ? (AnnotatedBinding<T>) binding : null;
        final LinkedBindingBuilder<? super T> link;

        if (annotatedBinding != null) {
            if (annotatedBinding.isAnnotationInstance()) {
                link = bind.annotatedWith(annotatedBinding.getAnnotationInstance());
            } else {
                link = bind.annotatedWith(annotatedBinding.getAnnotationClass());
            }
        } else {
            link = bind;
        }

        link.toInstance(binding.getInstance());
    }
}
