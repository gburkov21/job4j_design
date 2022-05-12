package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    private <T> T findByPredicate(List<T> list, Comparator<T> comparator, Predicate<Integer> predicate) {
        T result = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            T nextValue = list.get(i);
            if (predicate.test(comparator.compare(result, nextValue))) {
                result = nextValue;
            }
        }
        return result;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findByPredicate(value, comparator, predicate -> predicate < 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findByPredicate(value, comparator, predicate -> predicate > 0);
    }
}
