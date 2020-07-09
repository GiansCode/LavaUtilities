package me.piggypiglet.lavautilities.boot;

import com.google.common.collect.Lists;
import com.google.inject.Injector;
import me.piggypiglet.lavautilities.boot.framework.Registerable;
import me.piggypiglet.lavautilities.commands.registerables.CommandHandlerRegisterable;
import me.piggypiglet.lavautilities.commands.registerables.CommandsRegisterable;
import me.piggypiglet.lavautilities.events.EventRegisterable;
import me.piggypiglet.lavautilities.file.registerables.FileObjectsRegisterable;
import me.piggypiglet.lavautilities.file.registerables.FilesRegisterable;
import me.piggypiglet.lavautilities.generation.data.DataSaver;
import me.piggypiglet.lavautilities.generation.data.registerables.DataSaveRegisterable;
import me.piggypiglet.lavautilities.generation.destruction.registerables.DestructionLoopRegisterable;
import me.piggypiglet.lavautilities.guice.ExceptionalInjector;
import me.piggypiglet.lavautilities.guice.modules.DynamicModule;
import me.piggypiglet.lavautilities.guice.modules.InitialModule;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class LavaUtilitiesBootstrap extends JavaPlugin {
    private static final List<Class<? extends Registerable>> STARTUP = Lists.newArrayList(
            FileObjectsRegisterable.class,
            FilesRegisterable.class,
            CommandHandlerRegisterable.class,
            CommandsRegisterable.class,
            EventRegisterable.class,
            DestructionLoopRegisterable.class,
            DataSaveRegisterable.class
    );

    private static final List<Class<? extends Runnable>> SHUTDOWN = Lists.newArrayList(
            DataSaver.class
    );

    private final AtomicReference<Injector> injectorReference = new AtomicReference<>();

    @Override
    public void onEnable() {
        injectorReference.set(
                new ExceptionalInjector(new InitialModule(this).createInjector())
        );

        for (final Class<? extends Registerable> registerableClass : STARTUP) {
            final Injector injector = injectorReference.get();
            final Registerable registerable = injector.getInstance(registerableClass);
            registerable.run(injector);

            if (!registerable.getBindings().isEmpty() || registerable.getStaticInjections().length > 0) {
                injectorReference.set(injector.createChildInjector(new DynamicModule(
                        registerable.getBindings(),
                        registerable.getStaticInjections()
                )));
            }
        }
    }

    @Override
    public void onDisable() {
        SHUTDOWN.stream()
                .map(injectorReference.get()::getInstance)
                .forEach(Runnable::run);
    }
}
