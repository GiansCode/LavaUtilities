package me.piggypiglet.lavautilities.commands;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Singleton;
import me.piggypiglet.lavautilities.commands.exceptions.NoDefaultCommandException;
import me.piggypiglet.lavautilities.commands.framework.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class CommandHandler implements CommandExecutor {
    private Set<Command> commands;
    private Command defaultCommand;

    public void populate(@NotNull final Set<Command> commands) {
        this.commands = commands;
        defaultCommand = commands.stream()
                .filter(Command::isDefault)
                .findAny().orElseThrow(() -> new NoDefaultCommandException("No default command is present in the plugin."));
    }

    @Override
    public boolean onCommand(@NotNull final CommandSender sender, @NotNull final org.bukkit.command.Command bukkitCommand,
                             @NotNull final String label, @NotNull final String[] args) {
        if (args.length == 0) {
            if (performChecks(sender, defaultCommand)) {
                defaultCommand.execute(sender, args);
            }

            return true;
        }

        final Optional<Command> optionalCommand = commands.stream()
                .filter(cmd -> cmd.getPrefix().equalsIgnoreCase(args[0]))
                .findAny();

        if (!optionalCommand.isPresent()) {
            sender.sendMessage("Unknown command.");
            return true;
        }

        final Command command = optionalCommand.get();

        if (!performChecks(sender, command)) return true;

        if (!command.execute(sender, Arrays.copyOfRange(args, 1, args.length))) {
            sender.sendMessage("Incorrect usage, correct usage is /lu " + args[0] + " " + command.getUsage());
        }

        return true;
    }

    @NotNull
    public Set<Command> getCommands() {
        return ImmutableSet.copyOf(commands);
    }

    private boolean performChecks(@NotNull final CommandSender sender, @NotNull final Command command) {
        if (command.isPlayerOnly() && !(sender instanceof Player)) {
            sender.sendMessage("This command can only be ran by a player.");
            return false;
        }

        if (!command.getPermissions().isEmpty() && command.getPermissions().stream().noneMatch(sender::hasPermission)) {
            sender.sendMessage("You do not have permission to use this command.");
            return false;
        }

        return true;
    }
}
