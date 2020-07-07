package me.piggypiglet.lavautilities.utils.collection;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class WeightedList<E> {
    private final List<E> composition = new ArrayList<>();
    private final Map<E, Integer> weights = new HashMap<>();

    public WeightedList() {}

    public WeightedList(@NotNull final Map<E, Integer> weights) {
        weights.forEach(this::add);
    }

    public void add(@NotNull final E element, final int weight) {
        IntStream.range(0, weight).forEach(i -> composition.add(element));
        weights.put(element, weight);
    }

    @NotNull
    public E get() {
        return composition.get(ThreadLocalRandom.current().nextInt(composition.size()));
    }

    @NotNull
    public Map<E, Integer> getWeights() {
        return weights;
    }
}
