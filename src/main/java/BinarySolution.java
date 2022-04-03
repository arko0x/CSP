import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

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
                ArrayList<Integer> list = new ArrayList<>();
                list.add(0);
                list.add(1);
                domain.put(i, list);
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
                if (variables.get(i * n + j) == null) {
                    System.out.print("x ");
                }
                else {
                    System.out.print(variables.get(i * n + j) + " ");
                }
            }
            System.out.println();
        }
    }

    @Override
    public Solution getCopy() {
        BinarySolution binarySolution = new BinarySolution();
        binarySolution.variables = new ArrayList<>(variables);
        binarySolution.n = n;
        binarySolution.domain = domain.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> new ArrayList<>(e.getValue())));
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

    @Override
    public List<Integer> chooseValuesForIndex(int index) {
        return null;
    }

    @Override
    public void printDomain() {
        for (Integer val : domain.keySet()) {
            System.out.print(domain.get(val) + " ");
        }
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

//        if (countValuesInRow(row, value) == this.n / 2) {
//            for (int i = n * row; i < n * row + n; i++) {
//                if (variables.get(i) == null) {
//                    List<Integer> newDomain = domain.get(i);
//                    newDomain.remove(Integer.valueOf(value));
//                }
//            }
//        }
//
//        if (countValuesInColumn(column, value) == this.n / 2) {
//            for (int i = column; i <= this.n; i += n) {
//                if (variables.get(i) == null) {
//                    List<Integer> newDomain = domain.get(i);
//                    newDomain.remove(Integer.valueOf(value));
//                }
//            }
//        }

//        if (thereAreThreeValuesInARowInARow(row)) {
//            int startIndex = getStartIndexOfThreeValuesInARowInARow(row);
//            int endIndex = getEndIndexOfThreeValuesInARowInARow(row);
//            if (startIndex % n - 1 > 0) {
//                if (variables.get(startIndex - 1) == null) {
//                    List<Integer> newDomain = domain.get(startIndex - 1);
//                    newDomain.remove(Integer.valueOf(value));
//                }
//            }
//            if (endIndex % n + 1 < n - 1) {
//                if (variables.get(endIndex + 1) == null) {
//                    List<Integer> newDomain = domain.get(endIndex + 1);
//                    newDomain.remove(Integer.valueOf(value));
//                }
//            }
//        }
//
//        if (thereAreThreeValuesInARowInAColumn(column)) {
//            int startIndex = getStartIndexOfThreeValuesInARowInAColumn(column);
//            int endIndex = getEndIndexOfThreeValuesInARowInAColumn(column);
//            if (startIndex / n - 1 > 0) {
//                if (variables.get(startIndex - n) == null) {
//                    List<Integer> newDomain = domain.get(startIndex - n);
//                    newDomain.remove(Integer.valueOf(value));
//                }
//            }
//            if (endIndex / n + 1 < n - 1) {
//                if (variables.get(endIndex + 1) == null) {
//                    List<Integer> newDomain = domain.get(endIndex + 1);
//                    newDomain.remove(Integer.valueOf(value));
//                }
//            }
//        }
    }

    @Override
    public boolean hasEmptyDomain() {
        return this.domain.containsValue(new ArrayList<Integer>());
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

    private int getStartIndexOfThreeValuesInARowInARow(int row) {
        int startIndex = this.n * row;
        int endIndex = this.n * row + n - 1;

        for (int i = startIndex + 2; i <= endIndex; i++) {
            if (Objects.nonNull(variables.get(i - 2)) && Objects.equals(variables.get(i - 2), variables.get(i - 1)) && Objects.equals(variables.get(i - 1), variables.get(i))) {
                return i - 2;
            }
        }
        return -1;
    }

    private int getEndIndexOfThreeValuesInARowInARow(int row) {
        int startIndex = this.n * row;
        int endIndex = this.n * row + n - 1;

        for (int i = startIndex + 2; i <= endIndex; i++) {
            if (Objects.nonNull(variables.get(i - 2)) && Objects.equals(variables.get(i - 2), variables.get(i - 1)) && Objects.equals(variables.get(i - 1), variables.get(i))) {
                return i;
            }
        }
        return -1;
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

    private int getStartIndexOfThreeValuesInARowInAColumn(int column) {
        int startIndex = column;
        int endIndex = this.n * (this.n - 1) + column;

        for (int i = startIndex + n * 2; i <= endIndex; i += n) {
            if (Objects.nonNull(variables.get(i - n * 2)) && Objects.equals(variables.get(i - n * 2), variables.get(i - n)) && Objects.equals(variables.get(i - n), variables.get(i))) {
                return i - n * 2;
            }
        }
        return -1;
    }

    private int getEndIndexOfThreeValuesInARowInAColumn(int column) {
        int startIndex = column;
        int endIndex = this.n * (this.n - 1) + column;

        for (int i = startIndex + n * 2; i <= endIndex; i += n) {
            if (Objects.nonNull(variables.get(i - n * 2)) && Objects.equals(variables.get(i - n * 2), variables.get(i - n)) && Objects.equals(variables.get(i - n), variables.get(i))) {
                return i - n;
            }
        }
        return -1;
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
