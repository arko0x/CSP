import solutions.Solution;

import java.util.List;

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
//                solution.print();
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

    public boolean backtrackUntilFirstSolution(Solution solution, int index) {
        boolean solutionFound = false;
        for (Integer value : solution.getDomainForVariable(index)) {
            solution.set(index, value);
            nodesVisited++;
            boolean solutionValid = solution.isSolutionValid(index, value);
            if (solutionValid && solution.isSolutionFinal()) {
//                solution.print();
                numberOfSolutions++;
                solutionFound = true;
            }
            else if (solutionValid) {
                solutionFound = backtrackUntilFirstSolution(solution.getCopy(), solution.getNextIndex(index));
            }
            else {
                goingBacks++;
            }
            if (solutionFound) {
                return true;
            }
        }
        return false;
    }

    public void forwardChecking(Solution solution, int index) {
        for (Integer value : solution.getDomainForVariable(index)) {
            solution.set(index, value);
            nodesVisited++;
            boolean solutionValid = solution.isSolutionValid(index, value);

            if (solutionValid && solution.isSolutionFinal()) {
//                solution.print();
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

    public boolean forwardCheckingUntilFirstSolution(Solution solution, int index) {
        boolean solutionFound = false;
        List<Integer> domain = solution.getDomainForVariable(index);
        for (int i = 0; i < domain.size(); i++) {
//        for (Integer value : domain) {
            solution.set(index, domain.get(i));
            nodesVisited++;
            boolean solutionValid = solution.isSolutionValid(index, domain.get(i));

            if (solutionValid && solution.isSolutionFinal()) {
//                solution.print();
                numberOfSolutions++;
                return true;
            }
            else if (solutionValid) {
                Solution prevSolution = solution.getCopy();
                solution.removeImpossibleDomains(index, domain.get(i));
                if (!solution.hasEmptyDomain()) {
                    solutionFound = forwardCheckingUntilFirstSolution(solution, solution.getNextIndex(index));
                }
                solution = prevSolution;
            }
            else {
                goingBacks++;
            }

            if (solutionFound) {
                return true;
            }
        }
        return false;
    }
}
