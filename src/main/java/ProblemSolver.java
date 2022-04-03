import javax.naming.OperationNotSupportedException;

public class ProblemSolver {
    public static int backtrackNodesVisits = 0;
    public static int forwardCheckingNodesVisits = 0;
    public static int numberOfSolutionsBinary = 0;
    public static int numberOfSolutionsFutoshiki = 0;

    public void backtrack(Solution solution, int index) {
        for (Integer value : solution.getDomainForVariable(index)) {
            solution.set(index, value);
            backtrackNodesVisits++;
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

    public void forwardChecking(Solution solution, int index) {
        for (Integer value : solution.getDomainForVariable(index)) {
            Solution prevSolution = solution.getCopy();
            solution.set(index, value);
            forwardCheckingNodesVisits++;
            boolean solutionValid = solution.isSolutionValid(index, value);

//            solution.print();
//            System.out.println("Valid: " + solutionValid);
//            System.out.println("Index: " + index);
//            System.out.println("Has empty domain: " + solution.hasEmptyDomain());
//            solution.printDomain();
//            System.out.println();
//            System.out.println();
            if (solution.hasEmptyDomain()) {
                solution = prevSolution;
            }
            else if (solutionValid && solution.isSolutionFinal()) {
                solution.print();
            }
            else if (solutionValid) {
                solution.removeImpossibleDomains(index, value);
                forwardChecking(solution.getCopy(), solution.getNextIndex(index));
//                System.out.println("Returned from recursion");
                solution = prevSolution;
            } else {
                solution = prevSolution;
            }
        }
    }
}
