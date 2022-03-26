import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Binary {
    private List<Integer> variables;
    private Map<Integer, List<Integer>> domain;
    private int n;
    private List<Integer> unchangeableIndexes;

    public Binary() {
        this.domain = new HashMap<>();
        this.unchangeableIndexes = new ArrayList<>();
    }

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

//    public boolean possibleToSetValueInIndex(int index, int value) {
//        this.variables.set(index, value);
//        int row = index / this.n;
//        int column = index % this.n;
//        if ((countValuesInRow(row, value) + countValuesInRow(row, Math.abs(value - 1)) == this.n
//                && countValuesInRow(row, value) != countValuesInRow(row, Math.abs(value - 1))) ||
//                (countValuesInColumn(column, value) + countValuesInColumn(column, Math.abs(value - 1)) == this.n
//                && countValuesInColumn(column, value) != countValuesInColumn(column, Math.abs(value - 1))) ||
//                countValuesInRow(row, value) > this.n / 2 ||
//                countValuesInColumn(column, value) > this.n / 2 ||
//                thereAreThreeValuesInARowInARow(row) ||
//                thereAreThreeValuesInARowInAColumn(column) ||
//                !isRowUnique(row) ||
//                !isColumnUnique(column)) {
//            this.variables.set(index, null);
//            return false;
//        }
//
//        this.variables.set(index, null);
//        return true;
//    }

    private int getFirstIndex() {
        for (int i = 0; i < variables.size(); i++) {
            if (!unchangeableIndexes.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    private int getNextIndex(int index, List<Integer> solution) {
        for (int i = 0; i < solution.size(); i++) {
            if (i > index && !unchangeableIndexes.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean thereAreThreeValuesInARowInARow(int row, List<Integer> solution) {
        int startIndex = this.n * row;
        int endIndex = this.n * row + n - 1;

        for (int i = startIndex + 2; i <= endIndex; i++) {
            if (Objects.nonNull(solution.get(i - 2)) && Objects.equals(solution.get(i - 2), solution.get(i - 1)) && Objects.equals(solution.get(i - 1), solution.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean thereAreThreeValuesInARowInAColumn(int column, List<Integer> solution) {
        int startIndex = column;
        int endIndex = this.n * (this.n - 1) + column;

        for (int i = startIndex + n * 2; i <= endIndex; i += n) {
            if (Objects.nonNull(solution.get(i - n * 2)) && Objects.equals(solution.get(i - n * 2), solution.get(i - n)) && Objects.equals(solution.get(i - n), solution.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean isRowUnique(int row, List<Integer> solution) {
        int startIndex = this.n * row;
        int endIndex = this.n * row + n - 1;

        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i <= endIndex; i++) {
            sb.append(solution.get(i));
        }
        String rowString = sb.toString();

        for (int i = 0; i <= n * n - n; i += n) {
            if (i != startIndex) {
                StringBuilder rowBuilder = new StringBuilder();
                for (int j = i; j < i + n; j++) {
                    rowBuilder.append(solution.get(j));
                }
                if (rowBuilder.toString().equals(rowString)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isColumnUnique(int column, List<Integer> solution) {
        int startIndex = column;
        int endIndex = this.n * (this.n - 1) + column;

        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i <= endIndex; i += n) {
            sb.append(solution.get(i));
        }
        String columnString = sb.toString();

        for (int i = 0; i < n; i++) {
            if (i != column) {
                StringBuilder colBuilder = new StringBuilder();
                for (int j = i; j < i + n; j += n) {
                    colBuilder.append(solution.get(j));
                }
                if (colBuilder.toString().equals(columnString)) {
                    return false;
                }
            }
        }
        return true;
    }

    private int countValuesInRow(Integer row, Integer value, List<Integer> solution) {
        int numberOfValues = 0;
        for (int i = 0; i < solution.size(); i++) {
            if (i / n == row && Objects.equals(solution.get(i), value)) {
                numberOfValues++;
            }
        }
        return numberOfValues;
    }

    private int countValuesInColumn(Integer column, Integer value, List<Integer> solution) {
        int numberOfValues = 0;
        for (int i = 0; i < solution.size(); i++) {
            if (i % n == column && Objects.equals(solution.get(i), value)) {
                numberOfValues++;
            }
        }
        return numberOfValues;
    }

    public void backtrack() {
        backtrack(variables, getFirstIndex());
    }

    public void backtrack(List<Integer> solution, int index) {
        for (Integer value : this.domain.get(index)) {
            solution.set(index, value);
            if (isSolutionValid(new ArrayList<>(solution), index, value) && isFinalSolution(solution)) {
                printSolution(solution);
                System.out.println();
            }
            else if (isSolutionValid(new ArrayList<>(solution), index, value)) {
                backtrack(new ArrayList<>(solution), getNextIndex(index, new ArrayList<>(solution)));
            }
        }
    }

    private boolean isFinalSolution(List<Integer> solution) {
        return !solution.contains(null);
    }

    private boolean isSolutionValid(List<Integer> solution, int index, int value) {
        int row = index / this.n;
        int column = index % this.n;
        if ((countValuesInRow(row, value, solution) + countValuesInRow(row, Math.abs(value - 1), solution) == this.n
                && countValuesInRow(row, value, solution) != countValuesInRow(row, Math.abs(value - 1), solution)) ||
                (countValuesInColumn(column, value, solution) + countValuesInColumn(column, Math.abs(value - 1), solution) == this.n
                && countValuesInColumn(column, value, solution) != countValuesInColumn(column, Math.abs(value - 1), solution)) ||
                countValuesInRow(row, value, solution) > this.n / 2 ||
                countValuesInColumn(column, value, solution) > this.n / 2 ||
                thereAreThreeValuesInARowInARow(row, solution) ||
                thereAreThreeValuesInARowInAColumn(column, solution) ||
                !isRowUnique(row, solution) ||
                !isColumnUnique(column, solution)) {
            return false;
        }

        return true;
    }

    private void printSolution(List<Integer> solution) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(solution.get(i * n + j));
            }
            System.out.println();
        }
    }
}
