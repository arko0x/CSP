import javax.naming.OperationNotSupportedException;

public class Main {
    public static void main(String[] args) {
        long startTime = 0;

        System.out.println("6x6");
        ProblemSolver problemSolver = new ProblemSolver();
        BinarySolution binarySolution6x6Backtrack = new BinarySolution();
        NextVariableStrategy nextVariableStrategy = new DefaultNextVariableStrategy(binarySolution6x6Backtrack);
        binarySolution6x6Backtrack.setNextVariableStrategy(nextVariableStrategy);
        DomainOrderingStrategy domainOrderingStrategy = new DefaultOrderingStrategy();
        binarySolution6x6Backtrack.setDomainOrderingStrategy(domainOrderingStrategy);
        binarySolution6x6Backtrack.readProblemFromFile("src/main/resources/data/binary_6x6");
        startTime = System.currentTimeMillis();
        problemSolver.backtrack(binarySolution6x6Backtrack, binarySolution6x6Backtrack.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;

        BinarySolution binarySolution6x6ForwardChecking = new BinarySolution();
        nextVariableStrategy = new LeastDomainSizeStrategy(binarySolution6x6ForwardChecking);
        binarySolution6x6ForwardChecking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new MinimalConflictsOrderingStrategy();
        binarySolution6x6ForwardChecking.setDomainOrderingStrategy(domainOrderingStrategy);
        binarySolution6x6ForwardChecking.readProblemFromFile("src/main/resources/data/binary_6x6");
        startTime = System.currentTimeMillis();
        problemSolver.forwardChecking(binarySolution6x6ForwardChecking, binarySolution6x6ForwardChecking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;

        BinarySolution binarySolution8x8Backtracking = new BinarySolution();
        nextVariableStrategy = new LeastDomainSizeStrategy(binarySolution8x8Backtracking);
        binarySolution8x8Backtracking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new MinimalConflictsOrderingStrategy();
        binarySolution8x8Backtracking.setDomainOrderingStrategy(domainOrderingStrategy);
        binarySolution8x8Backtracking.readProblemFromFile("src/main/resources/data/binary_8x8");
        startTime = System.currentTimeMillis();
        problemSolver.backtrack(binarySolution8x8Backtracking, binarySolution8x8Backtracking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;

        BinarySolution binarySolution8x8ForwardChecking = new BinarySolution();
        nextVariableStrategy = new LeastDomainSizeStrategy(binarySolution8x8ForwardChecking);
        binarySolution8x8ForwardChecking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new MinimalConflictsOrderingStrategy();
        binarySolution8x8ForwardChecking.setDomainOrderingStrategy(domainOrderingStrategy);
        binarySolution8x8ForwardChecking.readProblemFromFile("src/main/resources/data/binary_8x8");
        startTime = System.currentTimeMillis();
        problemSolver.forwardChecking(binarySolution8x8ForwardChecking, binarySolution8x8ForwardChecking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;

        BinarySolution binarySolution10x10Backtracking = new BinarySolution();
        nextVariableStrategy = new LeastDomainSizeStrategy(binarySolution10x10Backtracking);
        binarySolution10x10Backtracking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new MinimalConflictsOrderingStrategy();
        binarySolution10x10Backtracking.setDomainOrderingStrategy(domainOrderingStrategy);
        binarySolution10x10Backtracking.readProblemFromFile("src/main/resources/data/binary_10x10");
        startTime = System.currentTimeMillis();
        problemSolver.backtrack(binarySolution10x10Backtracking, binarySolution10x10Backtracking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;

        BinarySolution binarySolution10x10ForwardChecking = new BinarySolution();
        nextVariableStrategy = new LeastDomainSizeStrategy(binarySolution10x10ForwardChecking);
        binarySolution10x10ForwardChecking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new MinimalConflictsOrderingStrategy();
        binarySolution10x10ForwardChecking.setDomainOrderingStrategy(domainOrderingStrategy);
        binarySolution10x10ForwardChecking.readProblemFromFile("src/main/resources/data/binary_10x10");
        startTime = System.currentTimeMillis();
        problemSolver.forwardChecking(binarySolution10x10ForwardChecking, binarySolution10x10ForwardChecking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;

//        ProblemSolver.forwardCheckingNodesVisits = 0;
//        FutoshikiSolution futoshikiSolution = new FutoshikiSolution();
//        futoshikiSolution.readProblemFromFile("src/main/resources/data/futoshiki_6x6");
//        long startTime = System.currentTimeMillis();
//        problemSolver.forwardChecking(futoshikiSolution, futoshikiSolution.getFirstIndex());
//        System.out.println(ProblemSolver.forwardCheckingNodesVisits);
//        System.out.println(System.currentTimeMillis() - startTime);
//        System.out.println(ProblemSolver.numberOfSolutionsFutoshiki);
//        System.out.println(ProblemSolver.goingBacksForwardChecking);

//        ProblemSolver problemSolver1 = new ProblemSolver();
//        BinarySolution binarySolution1 = new BinarySolution();
//        binarySolution1.readProblemFromFile("src/main/resources/data/binary_6x6");
//        startTime = System.currentTimeMillis();
//        problemSolver1.backtrack(binarySolution1, binarySolution1.getFirstIndex());
//        System.out.println(ProblemSolver.backtrackNodesVisits);
//        System.out.println(System.currentTimeMillis() - startTime);

//        long startTime = System.currentTimeMillis();
//        FutoshikiSolution futoshikiSolution = new FutoshikiSolution();
//        futoshikiSolution.readProblemFromFile("src/main/resources/data/futoshiki_6x6");
//        problemSolver.backtrack(futoshikiSolution, futoshikiSolution.getFirstIndex());
//        long endTime = System.currentTimeMillis() - startTime;
//        System.out.println(endTime / 1000);
    }
}
