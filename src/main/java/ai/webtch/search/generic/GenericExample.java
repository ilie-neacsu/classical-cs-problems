package ai.webtch.search.generic;

import java.util.List;

import static ai.webtch.search.generic.GenericSearch.linearContains;
import static ai.webtch.search.generic.GenericSearch.binaryContains;

public class GenericExample {
    public static void main(String[] args) {
        System.out.println(linearContains(List.of(1, 5, 15, 15, 15, 15, 20), 5)); // true
        System.out.println(binaryContains(List.of("a", "d", "e", "f", "z"), "f")); // true
        System.out.println(binaryContains(List.of("john", "mark", "ronald", "sarah"), "sheila")); // false
    }
}
