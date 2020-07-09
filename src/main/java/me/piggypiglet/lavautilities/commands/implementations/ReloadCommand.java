package me.piggypiglet.lavautilities.commands.implementations;

import com.google.inject.Inject;
import me.piggypiglet.lavautilities.commands.framework.Command;
import me.piggypiglet.lavautilities.file.FileManager;
import me.piggypiglet.lavautilities.file.annotations.File;
import me.piggypiglet.lavautilities.file.objects.Config;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class ReloadCommand extends Command {
    @Inject private FileManager fileManager;

    public ReloadCommand() {
        super("reload");

        options
                .permissions("lavautilities.admin", "lavautilities.reload")
                .description("Reload the plugin config.")
                .usage("");
    }

    @Override
    public boolean execute(@NotNull final CommandSender sender, @NotNull final String[] args) {
        final File data = Config.class.getAnnotation(File.class);
        fileManager.loadFile(Config.class, data.internalPath(), data.externalPath());
        sender.sendMessage("&7Successfully loaded latest config values into memory.");
        return true;
    }
}
