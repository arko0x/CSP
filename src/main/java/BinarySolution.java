import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Getter
@Setter
public class BinarySolution implements Solution {
    private List<Integer> variables;
    private Map<Integer, List<Integer>> domain;
    private int n;
    private List<Integer> unchangeableIndexes;
    private int numberOfSolutionsFound = 0;
    public static int numberOfSolutions = 0;

    public BinarySolution() {
        this.domain = new HashMap<>();
        this.unchangeableIndexes = new LinkedList<>();
    }

    @Override
    public void readProblemFromFile(String filePath) {
        Path path = Path.of(filePath);

        try {
            String fileContent = Files.readString(path);
            fileContent = fileContent.replaceAll("[^a-zA-Z0-9]", "");
            this.n = (int) Files.lines(path).count();
            this.variables = new ArrayList<>();
            for (int i = 0; i < n * n; i++) {
                variables.add(null);
            }

            int i = 0;
            for (Character c : fileContent.toCharArray()) {
                if (c.equals('x')) {
                    variables.set(i, null);
                }
                else {
                    variables.set(i, Character.getNumericValue(c));
                    unchangeableIndexes.add(i);
                }
                domain.put(i, List.of(0, 1));
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isSolutionValid(int index, int value) {
        int row = index / this.n;
        int column = index % this.n;
        return (countValuesInRow(row, value) + countValuesInRow(row, Math.abs(value - 1)) != this.n
                || countValuesInRow(row, value) == countValuesInRow(row, Math.abs(value - 1))) &&
                (countValuesInColumn(column, value) + countValuesInColumn(column, Math.abs(value - 1)) != this.n
                        || countValuesInColumn(column, value) == countValuesInColumn(column, Math.abs(value - 1))) &&
                countValuesInRow(row, value) <= this.n / 2 &&
                countValuesInColumn(column, value) <= this.n / 2 &&
                !thereAreThreeValuesInARowInARow(row) &&
                !thereAreThreeValuesInARowInAColumn(column) &&
                isRowUnique(row) &&
                isColumnUnique(column);
    }

    @Override
    public boolean isSolutionFinal() {
        return !variables.contains(null);
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
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(variables.get(i * n + j) + " ");
            }
            System.out.println();
        }
    }

    @Override
    public Solution getCopy() {
        BinarySolution binarySolution = new BinarySolution();
        binarySolution.variables = new ArrayList<>(variables);
        binarySolution.n = n;
        binarySolution.domain = domain;
        binarySolution.unchangeableIndexes = unchangeableIndexes;
        return binarySolution;
    }

    @Override
    public int getNextIndex(int index) {
        for (int i = 0; i < variables.size(); i++) {
            if (i > index && !unchangeableIndexes.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getFirstIndex() {
        for (int i = 0; i < variables.size(); i++) {
            if (!unchangeableIndexes.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return this.variables.size();
    }

    private boolean thereAreThreeValuesInARowInARow(int row) {
        int startIndex = this.n * row;
        int endIndex = this.n * row + n - 1;

        for (int i = startIndex + 2; i <= endIndex; i++) {
            if (Objects.nonNull(variables.get(i - 2)) && Objects.equals(variables.get(i - 2), variables.get(i - 1)) && Objects.equals(variables.get(i - 1), variables.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean thereAreThreeValuesInARowInAColumn(int column) {
        int startIndex = column;
        int endIndex = this.n * (this.n - 1) + column;

        for (int i = startIndex + n * 2; i <= endIndex; i += n) {
            if (Objects.nonNull(variables.get(i - n * 2)) && Objects.equals(variables.get(i - n * 2), variables.get(i - n)) && Objects.equals(variables.get(i - n), variables.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean isRowUnique(int row) {
        int startIndex = this.n * row;
        int endIndex = this.n * row + n - 1;

        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i <= endIndex; i++) {
            sb.append(variables.get(i));
        }
        String rowString = sb.toString();

        for (int i = 0; i <= n * n - n; i += n) {
            if (i != startIndex) {
                StringBuilder rowBuilder = new StringBuilder();
                for (int j = i; j < i + n; j++) {
                    rowBuilder.append(variables.get(j));
                }
                if (rowBuilder.toString().equals(rowString)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isColumnUnique(int column) {
        int startIndex = column;
        int endIndex = this.n * (this.n - 1) + column;

        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i <= endIndex; i += n) {
            sb.append(variables.get(i));
        }
        String columnString = sb.toString();

        for (int i = 0; i < n; i++) {
            if (i != column) {
                StringBuilder colBuilder = new StringBuilder();
                for (int j = i; j < i + n; j += n) {
                    colBuilder.append(variables.get(j));
                }
                if (colBuilder.toString().equals(columnString)) {
                    return false;
                }
            }
        }
        return true;
    }

    private int countValuesInRow(Integer row, int value) {
        int numberOfValues = 0;
        for (int i = 0; i < variables.size(); i++) {
            if (i / n == row && Objects.equals(variables.get(i), value)) {
                numberOfValues++;
            }
        }
        return numberOfValues;
    }

    private int countValuesInColumn(Integer column, Integer value) {
        int numberOfValues = 0;
        for (int i = 0; i < variables.size(); i++) {
            if (i % n == column && Objects.equals(variables.get(i), value)) {
                numberOfValues++;
            }
        }
        return numberOfValues;
    }
}
