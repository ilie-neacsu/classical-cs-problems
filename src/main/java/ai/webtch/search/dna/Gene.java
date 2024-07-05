package ai.webtch.search.dna;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Gene {

    public enum Nucleotide {
        A, C, G, T
    }

    public static class Codon implements Comparable<Codon> {

        public final Nucleotide first, second, third;

        private final Comparator<Codon> comparator =
                Comparator.comparing((Codon codon) -> codon.first)
                        .thenComparing((Codon codon) -> codon.second)
                        .thenComparing((Codon codon) -> codon.third);

        public Codon(String codonStr) {
            first = Nucleotide.valueOf(codonStr.substring(0,1));
            second = Nucleotide.valueOf(codonStr.substring(1,2));
            third = Nucleotide.valueOf(codonStr.substring(2,3));
        }

        @Override
        public int compareTo(Codon other) {
            return comparator.compare(this, other);
        }
    }

    private ArrayList<Codon> codons = new ArrayList<>();

    public Gene(String geneStr) {
        for (int i = 0; i < geneStr.length() - 3; i += 3) {
            codons.add(new Codon(geneStr.substring(i, i + 3)));
        }
    }

    // This function is for illustrative purposes only.All the classes in the Java standard library that
    // implement the Collection interface (like ArrayList and LinkedList) have a contains() method that
    // will likely be better optimized than anything we write.

    public boolean linearContains(Codon key) {
        for (Codon codon : codons) {
            if (codon.compareTo(key) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean binaryContains(Codon key) {

        ArrayList<Codon> sortedCodons = new ArrayList<>(codons);
        Collections.sort(sortedCodons);

        int low = 0;
        int high = sortedCodons.size() - 1;

        while (low <= high) {

            int middle = (low + high) / 2;
            int comparison = codons.get(middle).compareTo(key);

            if (comparison < 0) {
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
