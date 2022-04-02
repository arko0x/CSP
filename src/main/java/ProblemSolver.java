import javax.naming.OperationNotSupportedException;

public class ProblemSolver {
    public static int numberOfSolutionsBinary = 0;
    public static int numberOfSolutionsFutoshiki = 0;

    public void backtrack(Solution solution, int index) throws OperationNotSupportedException {
        for (Integer value : solution.getDomainForVariable(index)) {
            solution.set(index, value);
            boolean solutionValid = solution.isSolutionValid(index, value);
            if (solutionValid && solution.isSolutionFinal()) {
                solution.print();
                System.out.println("___________________________________");
            }
            else if (solutionValid) {
                backtrack(solution.getCopy(), solution.getNextIndex(index));
            }
        }
    }
}
