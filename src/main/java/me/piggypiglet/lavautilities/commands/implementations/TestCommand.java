package me.piggypiglet.lavautilities.commands.implementations;

import me.piggypiglet.lavautilities.commands.framework.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class TestCommand extends Command {
    public TestCommand() {
        super("test");
    }

    @Override
    public boolean execute(final @NotNull CommandSender sender, @NotNull final String[] args) {
        sender.sendMessage("test");
        return true;
    }
}
