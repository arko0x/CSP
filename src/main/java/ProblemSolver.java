import javax.naming.OperationNotSupportedException;

public class ProblemSolver {
    public static int numberOfSolutionsBinary = 0;
    public static int numberOfSolutionsFutoshiki = 0;

    public void backtrackBinary(Solution solution, int index) throws OperationNotSupportedException {
        for (Integer value : solution.getDomainForVariable(index)) {
            solution.set(index, value);
            boolean solutionValid = solution.isSolutionValid(index, value);
            if (solutionValid && solution.isSolutionFinal()) {
                solution.print();
                System.out.println();
                numberOfSolutionsBinary++;
            }
            else if (solutionValid) {
                backtrackBinary(solution.getCopy(), solution.getNextIndex(index));
            }
        }
    }

    public void backtrack(Solution solution, int index) throws OperationNotSupportedException {
        for (Integer value : solution.getDomainForVariable(index)) {
            solution.set(index, value);
            boolean solutionValid = solution.isSolutionValid(index);
            if (solutionValid && solution.isSolutionFinal()) {
                solution.print();
                System.out.println();
                numberOfSolutionsFutoshiki++;
            }
            else if (solutionValid && index < solution.size() - 1) {
                backtrack(solution.getCopy(), solution.getNextIndex(index));
            }
        }
    }
}
