package me.piggypiglet.lavautilities.generation.destruction;

import com.google.inject.Inject;
import me.piggypiglet.lavautilities.generation.GeneratorManager;
import me.piggypiglet.lavautilities.generation.formation.GeneratorBlockFormer;
import me.piggypiglet.lavautilities.generation.objects.GeneratorLocation;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class GeneratorDestructor {
    @Inject private GeneratorManager generatorManager;
    @Inject private GeneratorBlockFormer former;

    public void destruct(@NotNull final GeneratorLocation location) {
        former.cancel(location);
        generatorManager.unregister(location);
    }
}
