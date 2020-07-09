package me.piggypiglet.lavautilities.commands.implementations;

import com.google.inject.Inject;
import me.piggypiglet.lavautilities.commands.framework.Command;
import me.piggypiglet.lavautilities.generation.data.DataSaver;
import me.piggypiglet.lavautilities.schedule.Task;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class SaveDataCommand extends Command {
    @Inject private Task task;
    @Inject private DataSaver dataSaver;

    public SaveDataCommand() {
        super("savedata");

        options
                .usage("")
                .permissions("lavautilities.admin", "lavautilities.savedata")
                .description("Manually save generator data to disk.");
    }

    @Override
    public boolean execute(@NotNull final CommandSender sender, @NotNull final String[] args) {
        task.async(dataSaver);
        sender.sendMessage("&7Data successfully saved.");

        return true;
    }
}
