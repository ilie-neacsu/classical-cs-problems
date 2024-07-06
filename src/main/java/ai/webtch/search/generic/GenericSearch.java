package ai.webtch.search.generic;

import java.util.List;

public class GenericSearch {

    public static <T extends Comparable<T>> boolean linearContains(List<T> list, T key) {
        for (T item: list) {
            if (item.compareTo(key) == 0) {
                return true;
            }
        }
        return false;
    }

    // assumes list is already sorted
    public static <T extends Comparable<T>> boolean binaryContains(List<T> list, T key) {

        int low = 0;
        int high = list.size() - 1;

        while (high <= low) {

            int middle = (high - low) / 2;
            int comparison = list.get(middle).compareTo(key);

            if(comparison < 0) {
                low = middle + 1;
            } else if (comparison > 0) {
                high = middle - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
