package me.piggypiglet.lavautilities.utils.collection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Lewys Davies
 */
public final class ProbabilityCollection<E> {
    private static final Comparator<ProbabilitySetElement<?>> COMPARATOR =
            Comparator.comparingInt(ProbabilitySetElement::getIndex);

    private final TreeSet<ProbabilitySetElement<E>> collection = new TreeSet<>(COMPARATOR);

    private int totalProbability = 0;

    public ProbabilityCollection(@NotNull final Map<E, Integer> elements) {
        elements.forEach(this::add);
    }

    public int size() {
        return collection.size();
    }

    public boolean isEmpty() {
        return collection.isEmpty();
    }

    public boolean contains(@NotNull final E object) {
        return collection.stream()
                .anyMatch(entry -> entry.getObject().equals(object));
    }

    @NotNull
    public Iterator<ProbabilitySetElement<E>> iterator() {
        return collection.iterator();
    }

    public void add(@NotNull final E object, final int probability) {
        if(probability <= 0) {
            throw new IllegalArgumentException("Probability must be greater than 0");
        }

        collection.add(new ProbabilitySetElement<E>(object, probability));
        totalProbability += probability;

        updateIndexes();
    }

    public boolean remove(@NotNull final E object) {
        final Iterator<ProbabilitySetElement<E>> it = iterator();
        final boolean removed = it.hasNext();

        while (it.hasNext()) {
            final ProbabilitySetElement<E> entry = it.next();

            if (entry.getObject().equals(object)) {
                totalProbability -= entry.getProbability();
                it.remove();
            }
        }

        updateIndexes();

        return removed;
    }

    @NotNull
    public E get() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot get an element out of a empty set");
        }

        final ProbabilitySetElement<E> toFind = new ProbabilitySetElement<>(null, 0);
        toFind.setIndex(ThreadLocalRandom.current().nextInt(1, totalProbability + 1));

        return Objects.requireNonNull(Objects.requireNonNull(collection.floor(toFind)).getObject());
    }

    public int getTotalProbability() {
        return totalProbability;
    }

    private void updateIndexes() {
        int previousIndex = 0;

        for (final ProbabilitySetElement<E> entry : collection) {
            previousIndex = entry.setIndex(previousIndex + 1) + (entry.getProbability() - 1);
        }
    }

    /**
     * @author Lewys Davies
     */
    private final static class ProbabilitySetElement<T> {
        private final T object;
        private final int probability;
        private int index;

        protected ProbabilitySetElement(@Nullable final T object, final int probability) {
            this.object = object;
            this.probability = probability;
        }

        @Nullable
        public T getObject() {
            return object;
        }

        public int getProbability() {
            return probability;
        }

        private int getIndex() {
            return index;
        }

        private int setIndex(final int index) {
            this.index = index;
            return index;
        }
    }
}
