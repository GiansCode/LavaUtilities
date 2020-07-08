package me.piggypiglet.lavautilities.commands.implementations;

import com.google.inject.Inject;
import me.piggypiglet.lavautilities.commands.framework.Command;
import me.piggypiglet.lavautilities.generation.GeneratorManager;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class TestCommand extends Command {
    @Inject private GeneratorManager generatorManager;

    public TestCommand() {
        super("test");
    }

    @Override
    public boolean execute(final @NotNull CommandSender sender, @NotNull final String[] args) {
        sender.sendMessage(generatorManager.getGenerators().toString());
        return true;
    }
}
