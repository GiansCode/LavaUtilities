package me.piggypiglet.lavautilities.commands.implementations;

import com.google.inject.Inject;
import me.piggypiglet.lavautilities.commands.CommandHandler;
import me.piggypiglet.lavautilities.commands.framework.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Optional;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class HelpCommand extends Command {
    @Inject private CommandHandler commandHandler;

    public HelpCommand() {
        super("help");
        options
                .description("This page.")
                .permissions("lavautilities.admin", "lavautilities.")
                .usage("[command]")
                .def(true);
    }

    @Override
    public boolean execute(@NotNull final CommandSender sender, @NotNull final String[] args) {
        if (args.length > 0) {
            final Optional<Command> optionalCommand = commandHandler.getCommands().stream()
                    .filter(command -> command.getPrefix().equalsIgnoreCase(args[0]))
                    .findAny();

            if (!optionalCommand.isPresent()) return false;

            final Command command = optionalCommand.get();

            sender.sendMessage("&c/lu " + command.getPrefix() + " " + command.getUsage() + " &8- &7" + command.getDescription());
            return true;
        }

        final StringBuilder builder = new StringBuilder("&7-------- &cLavaUtilities &7--------");

        commandHandler.getCommands().stream()
                .sorted(Comparator.comparing(Command::getPrefix, String::compareToIgnoreCase))
                .forEach(command -> builder.append("\n").append("&c/lu ").append(command.getPrefix()).append(" ")
                        .append(command.getUsage()).append(" &8- &7")
                        .append(command.getDescription()));

        builder.append("\n&7---------------------------");

        sender.sendMessage(builder.toString());
        return true;
    }
}
