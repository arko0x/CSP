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
            for (int i = 0; i < numericChars.length(); i++) {
                List<Integer> domainValuesList = new LinkedList<>();
                for (int j = 0; j < n; j++) {
                    domainValuesList.add(j + 1);
                }
                domain.put(i, domainValuesList);
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (constraintChars.charAt(i * numberOfLines + j) == '>') {
                        constraints.put(Pair.with(i * n + j, i * n + j + 1), Comparator.comparingInt(Integer::intValue));
                    }
                    if (constraintChars.charAt(i * numberOfLines + j) == '<') {
                        constraints.put(Pair.with(i * n + j, i * n + j + 1), Comparator.comparingInt(Integer::intValue).reversed());
                    }
                }
            }

            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n; j++) {
                    if (constraintChars.charAt(numberOfLines - n + i * numberOfLines + j) == '>') {
                        constraints.put(Pair.with(i * n + j, i * n + n + j), Comparator.comparingInt(Integer::intValue));
                    }
                    if (constraintChars.charAt(numberOfLines - n + i * numberOfLines + j) == '<') {
                        constraints.put(Pair.with(i * n + j, i * n + n + j), Comparator.comparingInt(Integer::intValue).reversed());
                    }
                }
            }

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

    private boolean areRowsValid(List<Integer> solution) {
        boolean validity;
        for (int i = 0; i < n; i++) {
            List<Integer> row = solution.subList(i, i * n + n);
            validity = row.stream().distinct().count() <= n
                    && row.stream().filter(Objects::nonNull).count() == row.stream().filter(Objects::nonNull).distinct().count()
                    && row.stream().filter(Objects::nonNull).max(Comparator.comparingInt(Integer::intValue)).orElse(1) <= n
                    && row.stream().filter(Objects::nonNull).min(Comparator.comparingInt(Integer::intValue)).orElse(n) >= 1;
            if (!validity) {
                return false;
            }
        }
        return true;
    }

    private boolean areColumnsValid(List<Integer> solution) {
        boolean validity;
        for (int i = 0; i < n; i++) {
            List<Integer> column = new LinkedList<>();
            for (int j = 0; j < n; j++) {
                column.add(solution.get(j * n + i));
            }
            validity = column.stream().distinct().count() <= n
                    && column.stream().filter(Objects::nonNull).count() == column.stream().filter(Objects::nonNull).distinct().count()
                    && column.stream().filter(Objects::nonNull).max(Comparator.comparingInt(Integer::intValue)).orElse(1) <= n
                    && column.stream().filter(Objects::nonNull).min(Comparator.comparingInt(Integer::intValue)).orElse(n) >= 1;
            if (!validity) {
                return false;
            }
        }
        return true;
    }

    private boolean checkConstraints(List<Integer> solution) {
        return constraints.keySet()
                .stream()
                .filter(indexPair -> constraints.get(indexPair).compare(solution.get(indexPair.getValue0()), solution.get(indexPair.getValue1())) > 0)
                .count() == constraints.keySet().size();
    }

    private boolean isSolutionValid(List<Integer> solution) {
        return areRowsValid(solution) && areColumnsValid(solution) && checkConstraints(solution);
    }

    private boolean isFinalSolution(List<Integer> solution) {
        if (isSolutionValid(solution)) {
            return solution.stream().filter(Objects::nonNull).count() == solution.size();
        }
        return false;
    }

    public void backtrack() {
        backtrack(variables, 0);
    }

    public void backtrack(List<Integer> solution, int index) {
        for (Integer value : this.domain.get(index)) {
            solution.set(index, value);
            if (isSolutionValid(new ArrayList<>(solution)) && isFinalSolution(solution)) {
//                printSolution(solution);
                System.out.println("Valid solution");
            }
            else if (isSolutionValid(new ArrayList<>(solution)) && index < solution.size() - 1) {
                backtrack(new ArrayList<>(solution), index + 1);
            }
        }
    }
}
