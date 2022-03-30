import javax.naming.OperationNotSupportedException;

public class ProblemSolver {
    public void backtrackBinary(Solution solution, int index) throws OperationNotSupportedException {
        for (Integer value : solution.getDomainForVariable(index)) {
            solution.set(index, value);
            boolean solutionValid = solution.isSolutionValid(index, value);
            if (solutionValid && solution.isSolutionFinal()) {
                solution.print();
                System.out.println();
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
//                validSolutions++;
            }
            else if (solutionValid && index < solution.size() - 1) {
                backtrack(solution.getCopy(), solution.getNextIndex(index));
            }
        }
    }
}
