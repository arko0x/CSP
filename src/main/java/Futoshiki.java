import org.javatuples.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class Futoshiki {
    private List<Integer> variables;
    private final Map<Integer, List<Integer>> domain;
    private int n;
    private final List<Integer> unchangeableIndexes;
    private Map<Pair<Integer, Integer>, Comparator<Integer>> constraints;

    public Futoshiki() {
        this.domain = new HashMap<>();
        this.unchangeableIndexes = new ArrayList<>();
        this.constraints = new HashMap<>();
    }

    public Futoshiki(Map<Integer, List<Integer>> domain, List<Integer> unchangeableIndexes) {
        this.domain = domain;
        this.unchangeableIndexes = unchangeableIndexes;
    }

    public void readProblemFromFile(String filePath) {
        Path path = Path.of(filePath);
        try {
            String constraintChars = Files.readString(path).replaceAll("[a-zA-Z0-9\r\n]", "");
            String numericChars = Files.readString(path).replaceAll("[^a-zA-Z0-9]", "");
            int numberOfLines = (int) Files.readString(path).lines().count();
            this.n = (int) Math.sqrt(numericChars.length());
            this.variables = new ArrayList<>();

            for (int i = 0; i < numericChars.length(); i++) {
                char character = numericChars.charAt(i);
                variables.add(character == 'x' ? null : Character.getNumericValue(character));
            }
            System.out.println();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n - 1; j++) {
                    System.out.println(i * numberOfLines + j);
                    if (constraintChars.charAt(i * numberOfLines + j) == '>') {
                        constraints.put(Pair.with(i * n + j, i * n + j + 1), Comparator.comparingInt(e -> e));
                    }
                    if (constraintChars.charAt(i * numberOfLines + j) == '<') {
                        constraints.put(Pair.with(i * n + j, i * n + j + 1), Comparator.comparingInt(e -> e));
                    }
                }
            }

            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n; j++) {
                    if (constraintChars.charAt(numberOfLines - n + i * numberOfLines + j) == '>') {
                        constraints.put(Pair.with(i * n + j, i * n + n + j), Comparator.comparingInt(e -> e));
                    }
                    if (constraintChars.charAt(numberOfLines - n + i * numberOfLines + j) == '<') {
                        constraints.put(Pair.with(i * n + j, i * n + n + j), Comparator.comparingInt(e -> e));
                    }
                }
            }
            System.out.println();

//            char[] charArray = numericChars.toCharArray();
//
//            int index = 0;
//            for (int i = 0; i < n; i += 2) {
//                for (int j = 0; j < n; j += 2) {
//                    if (charArray[i * n + j] == 'x') {
//                        variables.set(index, null);
//                    }
//                    else {
//                        variables.set(index, Character.getNumericValue(charArray[i * n + j]));
//                    }
//                    if (i < n) {
//                        if (charArray[i + 1] == '>') {
//                            constraints.put(Pair.with(index, index + 1), Comparator.comparingInt(e -> e));
//                        }
//                        if (charArray[i + 1] == '<') {
//                            constraints.put(Pair.with(index, index + 1), Comparator.comparingInt(e -> e));
//                        }
//                        if (charArray[(i + n) / 2] == '>') {
//                            constraints.put(Pair.with(index, index + n), Comparator.comparingInt(e -> e));
//                        }
//                        if (charArray[(i + n) / 2] == '<') {
//                            constraints.put(Pair.with(index, index + n), Comparator.comparingInt(e -> e));
//                        }
//                    }
//                }
//            }
//            int i = 0;
//            for (Character c : numericChars.toCharArray()) {
//                if (c.equals('x')) {
//                    variables.set(i, null);
//                } else {
//                    variables.set(i, Character.getNumericValue(c));
//                    unchangeableIndexes.add(i);
//                }
//                domain.put(i, List.of(0, 1));
//                i++;
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isGreaterThan(int x, int y) {
        return true;
    }
}
