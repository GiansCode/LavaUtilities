package me.piggypiglet.lavautilities.commands.framework;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public abstract class Command {
    private final String prefix;

    private boolean playerOnly = false;
    private String description = "null";
    private String usage = "<required args> [optional args]";
    private List<String> permissions = Collections.emptyList();
    private boolean def = false;

    protected final Options options = new Options();

    protected Command(@NotNull final String prefix) {
        this.prefix = prefix;
    }

    public abstract boolean execute(@NotNull final CommandSender sender, @NotNull final String[] args);

    @NotNull
    public String getPrefix() {
        return prefix;
    }

    public boolean isPlayerOnly() {
        return playerOnly;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    @NotNull
    public String getUsage() {
        return usage;
    }

    @NotNull
    public List<String> getPermissions() {
        return permissions;
    }

    public boolean isDefault() {
        return def;
    }

    protected final class Options {
        @NotNull
        public Options playerOnly(final boolean value) {
            playerOnly = value;
            return this;
        }

        @NotNull
        public Options description(@NotNull final String value) {
            description = value;
            return this;
        }

        @NotNull
        public Options usage(@NotNull final String value) {
            usage = value;
            return this;
        }

        @NotNull
        public Options permissions(@NotNull final String... values) {
            permissions = Arrays.asList(values);
            return this;
        }

        @NotNull
        public Options def(final boolean value) {
            def = value;
            return this;
        }
    }
}
