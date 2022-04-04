
public class ProblemSolver {
    public static int nodesVisited = 0;
    public static int numberOfSolutions = 0;
    public static int goingBacks = 0;

    public void backtrack(Solution solution, int index) {
        for (Integer value : solution.getDomainForVariable(index)) {
            solution.set(index, value);
            nodesVisited++;
            boolean solutionValid = solution.isSolutionValid(index, value);
            if (solutionValid && solution.isSolutionFinal()) {
                solution.print();
                numberOfSolutions++;
            }
            else if (solutionValid) {
                backtrack(solution.getCopy(), solution.getNextIndex(index));
            }
            else {
                goingBacks++;
            }
        }
    }

    public void forwardChecking(Solution solution, int index) {
        for (Integer value : solution.getDomainForVariable(index)) {
            solution.set(index, value);
            nodesVisited++;
            boolean solutionValid = solution.isSolutionValid(index, value);

            if (solutionValid && solution.isSolutionFinal()) {
                solution.print();
                numberOfSolutions++;
            }
            else if (solutionValid) {
                Solution prevSolution = solution.getCopy();
                solution.removeImpossibleDomains(index, value);
                if (!solution.hasEmptyDomain()) {
                    forwardChecking(solution, solution.getNextIndex(index));
                }
                solution = prevSolution;
            }
            else {
                goingBacks++;
            }
        }
    }
}
