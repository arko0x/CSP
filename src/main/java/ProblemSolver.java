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

    public void initialDomainRemoval(Solution solution) {
        List<Integer> solutionVariables = solution.getVariables();
        for (int i = 0; i < solutionVariables.size(); i++) {
            List<Integer> variableDomain = solution.getDomainForVariable(i);
            if (solutionVariables.get(i) == null) {
                for (int j = 0; j < variableDomain.size(); j++) {
                    if (i == 27 && j == 1) {
                        System.out.println();
                    }
                    solution.set(i, variableDomain.get(j));
                    if (!solution.isSolutionValid(i, variableDomain.get(j))) {
                        variableDomain.remove(Integer.valueOf(variableDomain.get(j)));
                    }
                    solutionVariables.set(i, null);
                }
            }
        }
    }

    public void forwardChecking(Solution solution, int index) {
        for (Integer value : solution.getDomainForVariable(index)) {
            solution.set(index, value);
            nodesVisited++;
            boolean solutionValid = solution.isSolutionValid(index, value);

            if (solutionValid) {
                if (solution.isSolutionFinal()) {
                    //solution.print();
                    numberOfSolutions++;
                }
                else {
                    Solution prevSolution = solution.getCopy();
                    solution.removeImpossibleDomains(index, value);
                    if (!solution.hasEmptyDomain()) {
                        forwardChecking(solution, solution.getNextIndex(index));
                    } else {
                        goingBacks++;
                    }
                    solution = prevSolution;
                }
            }
            else {
                goingBacks++;
            }
        }
    }

    public void ff(Solution solution, int index) {
        initialDomainRemoval(solution);
        fc(solution, index);
    }

    public void fc(Solution solution, int index) {
        for (Integer value : solution.getDomainForVariable(index)) {
            Solution copy = solution.getCopy();
            copy.set(index, value);
            nodesVisited++;

            copy.removeImpossibleDomains(index, value);
            if (!copy.hasEmptyDomain()) {
                fc(copy, copy.getNextIndex(index));
            }
            else {
                goingBacks++;
            }

            if (copy.isSolutionFinal()) {
                copy.print();
                numberOfSolutions++;
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

            if (solutionValid) {
                if (solution.isSolutionFinal()) {
                    //                solution.print();
                    numberOfSolutions++;
                    return true;
                }
                else {
                    Solution prevSolution = solution.getCopy();
                    solution.removeImpossibleDomains(index, domain.get(i));
                    if (!solution.hasEmptyDomain()) {
                        solutionFound = forwardCheckingUntilFirstSolution(solution, solution.getNextIndex(index));
                    } else {
                        goingBacks++;
                    }
                    solution = prevSolution;
                }
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
