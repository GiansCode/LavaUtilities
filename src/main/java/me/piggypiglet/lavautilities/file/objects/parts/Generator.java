package me.piggypiglet.lavautilities.file.objects.parts;

import com.google.gson.annotations.JsonAdapter;
import me.piggypiglet.lavautilities.file.objects.parts.json.BlocksDeserializer;
import me.piggypiglet.lavautilities.utils.collection.ProbabilityCollection;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class Generator {
    private String name;
    private int priority;
    @JsonAdapter(BlocksDeserializer.class) private ProbabilityCollection<Material> blocks;

    @NotNull
    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(final int priority) {
        this.priority = priority;
    }

    @NotNull
    public ProbabilityCollection<Material> getBlocks() {
        return blocks;
    }

    @Override
    public String toString() {
        return "Generation{" +
                "priority=" + priority +
                ", blocks=" + blocks +
                '}';
    }
}
