import lombok.Getter;
import lombok.Setter;
import org.javatuples.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class FutoshikiSolution implements Solution {
    private List<Integer> variables;
    private Map<Integer, List<Integer>> domain;
    private Map<Pair<Integer, Integer>, Comparator<Integer>> constraints;
    private int n;
    public static int numberOfSolutions = 0;

    public FutoshikiSolution() {
        this.domain = new HashMap<>();
        this.constraints = new HashMap<>();
    }

    @Override
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
                    if (variables.get(i) == null) {
                        domainValuesList.add(j + 1);
                    }
                }
                if (variables.get(i) == null) {
                    domain.put(i, domainValuesList);
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (constraintChars.charAt(i * numberOfLines + j) == '>') {
                        constraints.put(Pair.with(i * n + j, i * n + j + 1), Comparator.comparingInt(Integer::intValue).reversed());
                    }
                    if (constraintChars.charAt(i * numberOfLines + j) == '<') {
                        constraints.put(Pair.with(i * n + j, i * n + j + 1), Comparator.comparingInt(Integer::intValue));
                    }
                }
            }

            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n; j++) {
                    if (constraintChars.charAt(numberOfLines - n + i * numberOfLines + j) == '>') {
                        constraints.put(Pair.with(i * n + j, i * n + n + j), Comparator.comparingInt(Integer::intValue).reversed());
                    }
                    if (constraintChars.charAt(numberOfLines - n + i * numberOfLines + j) == '<') {
                        constraints.put(Pair.with(i * n + j, i * n + n + j), Comparator.comparingInt(Integer::intValue));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int countDomainRemovals(int index, int value) {
        return 0;
    }

    @Override
    public void printDomain() {
        Solution.super.printDomain();
    }

    @Override
    public boolean isSolutionValid(int index, int value) {
        return isRowValid(index / n) && isColumnValid(index % n) && checkConstraints();
    }

    @Override
    public boolean isSolutionFinal() {
        return variables.stream().filter(Objects::nonNull).count() == variables.size();
    }

    @Override
    public List<Integer> getDomainForVariable(int index) {
        return domain.get(index);
    }

    @Override
    public void set(int index, int value) {
        this.variables.set(index, value);
    }

    @Override
    public void print() {
        int size = 2 * n - 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    System.out.print(this.variables.get((i / 2) * n + j / 2) + " ");
                }
                else if (i % 2 == 0 && j % 2 != 0) {
                    if (constraints.containsKey(Pair.with((i / 2) * n + j / 2, (i / 2) * n + j / 2 + 1))) {
                        if (isComparatorReversed(constraints.get(Pair.with((i / 2) * n + j / 2, (i / 2) * n + j / 2 + 1)))) {
                            System.out.print("> ");
                        }
                        else {
                            System.out.print("< ");
                        }
                    }
                    else {
                        System.out.print("  ");
                    }
                }
                else if (i % 2 != 0 && j % 2 == 0) {
                    if (constraints.containsKey(Pair.with(((i - 1) / 2) * n + j / 2, ((i + 1) / 2) * n + j / 2))) {
                        if (isComparatorReversed(constraints.get(Pair.with(((i - 1) / 2) * n + j / 2, ((i + 1) / 2) * n + j / 2)))) {
                            System.out.print(">   ");
                        }
                        else {
                            System.out.print("<   ");
                        }
                    }
                    else {
                        System.out.print("    ");
                    }
                }
            }
            System.out.println();
        }
    }

    @Override
    public Solution getCopy() {
        FutoshikiSolution solution = new FutoshikiSolution();
        solution.setConstraints(constraints);
        solution.domain = domain.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> new ArrayList<>(e.getValue())));
        solution.setN(n);
        solution.setVariables(new ArrayList<>(variables));
        return solution;
    }

    @Override
    public int getFirstIndex() {
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i) == null) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getNextIndex(int index) {
        for (int i = 0; i < variables.size(); i++) {
            if (i > index && variables.get(i) == null) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return this.variables.size();
    }

    @Override
    public List<Integer> chooseValuesForIndex(int index) {
        return null;
    }

    @Override
    public void removeImpossibleDomains(int index, int value) {
        int row = index / this.n;
        int column = index % this.n;

        for (int i = n * row; i < n * row + n; i++) {
            if (variables.get(i) == null) {
                List<Integer> localDomain = domain.get(i);
                for (int j = 0; j < localDomain.size(); j++) {
                    set(i, localDomain.get(j));
                    if (!isSolutionValid(i, localDomain.get(j))) {
                        List<Integer> newDomain = domain.get(i);
                        newDomain.remove(Integer.valueOf(localDomain.get(j)));
                    }
                    variables.set(i, null);
                }
            }
        }

        for (int i = column; i <= this.n; i += n) {
            if (variables.get(i) == null) {
                List<Integer> localDomain = domain.get(i);
                for (int j = 0; j < localDomain.size(); j++) {
                    set(i, localDomain.get(j));
                    if (!isSolutionValid(i, localDomain.get(j))) {
                        List<Integer> newDomain = domain.get(i);
                        newDomain.remove(Integer.valueOf(localDomain.get(j)));
                    }
                    variables.set(i, null);
                }
            }
        }
    }

    @Override
    public boolean hasEmptyDomain() {
        return this.domain.values().stream().anyMatch(list -> list.size() == 0);
    }

    private boolean isRowValid(int rowNumber) {
        List<Integer> row = variables.subList(rowNumber * n, rowNumber * n + n);
        Stream<Integer> selectedRow = row.stream().filter(Objects::nonNull);
        Stream<Integer> selectedRowDistinct = row.stream().filter(Objects::nonNull).distinct();
        return selectedRow.count() == selectedRowDistinct.count();
    }

    private boolean isColumnValid(int colNumber) {
        List<Integer> column = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            column.add(variables.get(i * n + colNumber));
        }
        Stream<Integer> col = column.stream().filter(Objects::nonNull);
        Stream<Integer> colDistinct = column.stream().filter(Objects::nonNull).distinct();
        return col.count() == colDistinct.count();
    }

    private boolean checkConstraints() {
        int matched = 0;
        int notFilled = 0;
        for (Pair<Integer, Integer> pair : constraints.keySet()) {
            if (variables.get(pair.getValue0()) == null || variables.get(pair.getValue1()) == null) notFilled++;
            else if (constraints.get(pair).compare(variables.get(pair.getValue0()), variables.get(pair.getValue1())) < 0) {
                matched++;
            }
        }
        return matched + notFilled == constraints.size();
    }

    private boolean isComparatorReversed(Comparator<Integer> comparator) {
        return comparator.compare(1, 2) > 0;
    }
}
