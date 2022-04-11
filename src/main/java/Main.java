import nextvariablestrategy.DefaultNextVariableStrategy;
import nextvariablestrategy.LeastDomainSizeStrategy;
import nextvariablestrategy.NextVariableStrategy;
import solutions.BinarySolution;
import solutions.FutoshikiSolution;
import solutions.Solution;
import valueorderingstrategy.DefaultOrderingStrategy;
import valueorderingstrategy.DomainOrderingStrategy;
import valueorderingstrategy.MinimalConflictsOrderingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Solution solution = new FutoshikiSolution();
        researchForwardCheckingUntilFirstSolution(solution,"src/main/resources/data/futoshiki_6x6", 10, new MinimalConflictsOrderingStrategy(), new DefaultNextVariableStrategy(solution));
//        startTestsForFindingOneSolution();
//        startTestsForFindingAllSolutions();
    }
    
    private static void startTestsForFindingAllSolutions() {
        long startTime = 0;
        System.out.println("6x6 backtracking");
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

        System.out.println("6x6 forward search");
        solutions.BinarySolution binarySolution6x6ForwardChecking = new solutions.BinarySolution();
        nextVariableStrategy = new nextvariablestrategy.LeastDomainSizeStrategy(binarySolution6x6ForwardChecking);
        binarySolution6x6ForwardChecking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new valueorderingstrategy.DefaultOrderingStrategy();
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

        solutions.BinarySolution binarySolution8x8Backtracking = new solutions.BinarySolution();
        nextVariableStrategy = new nextvariablestrategy.DefaultNextVariableStrategy(binarySolution8x8Backtracking);
        binarySolution8x8Backtracking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new valueorderingstrategy.DefaultOrderingStrategy();
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

        solutions.BinarySolution binarySolution8x8ForwardChecking = new solutions.BinarySolution();
        nextVariableStrategy = new nextvariablestrategy.LeastDomainSizeStrategy(binarySolution8x8ForwardChecking);
        binarySolution8x8ForwardChecking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new valueorderingstrategy.DefaultOrderingStrategy();
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

        solutions.BinarySolution binarySolution10x10Backtracking = new solutions.BinarySolution();
        nextVariableStrategy = new nextvariablestrategy.DefaultNextVariableStrategy(binarySolution10x10Backtracking);
        binarySolution10x10Backtracking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new valueorderingstrategy.DefaultOrderingStrategy();
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

        solutions.BinarySolution binarySolution10x10ForwardChecking = new solutions.BinarySolution();
        nextVariableStrategy = new nextvariablestrategy.LeastDomainSizeStrategy(binarySolution10x10ForwardChecking);
        binarySolution10x10ForwardChecking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new valueorderingstrategy.DefaultOrderingStrategy();
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

        solutions.FutoshikiSolution futoshikiSolution4x4Backtracking = new solutions.FutoshikiSolution();
        nextVariableStrategy = new nextvariablestrategy.DefaultNextVariableStrategy(futoshikiSolution4x4Backtracking);
        futoshikiSolution4x4Backtracking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new valueorderingstrategy.DefaultOrderingStrategy();
        futoshikiSolution4x4Backtracking.setDomainOrderingStrategy(domainOrderingStrategy);
        futoshikiSolution4x4Backtracking.readProblemFromFile("src/main/resources/data/futoshiki_4x4");
        startTime = System.currentTimeMillis();
        problemSolver.backtrack(futoshikiSolution4x4Backtracking, futoshikiSolution4x4Backtracking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;

        solutions.FutoshikiSolution futoshikiSolution4x4ForwardChecking = new solutions.FutoshikiSolution();
        nextVariableStrategy = new nextvariablestrategy.LeastDomainSizeStrategy(futoshikiSolution4x4ForwardChecking);
        futoshikiSolution4x4ForwardChecking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new valueorderingstrategy.DefaultOrderingStrategy();
        futoshikiSolution4x4ForwardChecking.setDomainOrderingStrategy(domainOrderingStrategy);
        futoshikiSolution4x4ForwardChecking.readProblemFromFile("src/main/resources/data/futoshiki_4x4");
        startTime = System.currentTimeMillis();
        problemSolver.forwardChecking(futoshikiSolution4x4ForwardChecking, futoshikiSolution4x4ForwardChecking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;

        solutions.FutoshikiSolution futoshikiSolution5x5Backtracking = new solutions.FutoshikiSolution();
        nextVariableStrategy = new nextvariablestrategy.DefaultNextVariableStrategy(futoshikiSolution5x5Backtracking);
        futoshikiSolution5x5Backtracking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new valueorderingstrategy.DefaultOrderingStrategy();
        futoshikiSolution5x5Backtracking.setDomainOrderingStrategy(domainOrderingStrategy);
        futoshikiSolution5x5Backtracking.readProblemFromFile("src/main/resources/data/futoshiki_5x5");
        startTime = System.currentTimeMillis();
        problemSolver.backtrack(futoshikiSolution5x5Backtracking, futoshikiSolution5x5Backtracking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;

        solutions.FutoshikiSolution futoshikiSolution5x5ForwardChecking = new solutions.FutoshikiSolution();
        nextVariableStrategy = new nextvariablestrategy.LeastDomainSizeStrategy(futoshikiSolution5x5ForwardChecking);
        futoshikiSolution5x5ForwardChecking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new valueorderingstrategy.DefaultOrderingStrategy();
        futoshikiSolution5x5ForwardChecking.setDomainOrderingStrategy(domainOrderingStrategy);
        futoshikiSolution5x5ForwardChecking.readProblemFromFile("src/main/resources/data/futoshiki_5x5");
        startTime = System.currentTimeMillis();
        problemSolver.forwardChecking(futoshikiSolution5x5ForwardChecking, futoshikiSolution5x5ForwardChecking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;

        FutoshikiSolution futoshikiSolution6x6Backtracking = new FutoshikiSolution();
        nextVariableStrategy = new DefaultNextVariableStrategy(futoshikiSolution6x6Backtracking);
        futoshikiSolution6x6Backtracking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new DefaultOrderingStrategy();
        futoshikiSolution6x6Backtracking.setDomainOrderingStrategy(domainOrderingStrategy);
        futoshikiSolution6x6Backtracking.readProblemFromFile("src/main/resources/data/futoshiki_6x6");
        startTime = System.currentTimeMillis();
        problemSolver.backtrack(futoshikiSolution6x6Backtracking, futoshikiSolution6x6Backtracking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;

        FutoshikiSolution futoshikiSolution6x6ForwardChecking = new FutoshikiSolution();
        nextVariableStrategy = new LeastDomainSizeStrategy(futoshikiSolution6x6ForwardChecking);
        futoshikiSolution6x6ForwardChecking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new DefaultOrderingStrategy();
        futoshikiSolution6x6ForwardChecking.setDomainOrderingStrategy(domainOrderingStrategy);
        futoshikiSolution6x6ForwardChecking.readProblemFromFile("src/main/resources/data/futoshiki_6x6");
        startTime = System.currentTimeMillis();
        problemSolver.initialDomainRemoval(futoshikiSolution6x6ForwardChecking);
        problemSolver.forwardChecking(futoshikiSolution6x6ForwardChecking, futoshikiSolution6x6ForwardChecking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;
    }

    private static void startTestsForFindingOneSolution() {
        long startTime = 0;
        double avgTime = 0;
        long bestTime = Long.MAX_VALUE;
        long worstTime = Long.MIN_VALUE;
        long time = 0;
        List<Long> executionTimes = new ArrayList<>();
        double standardDeviation = 0;
        ProblemSolver problemSolver = new ProblemSolver();

        System.out.println("6x6 binary backtracking until first solution");
        BinarySolution binarySolution6x6Backtrack = new BinarySolution();
        NextVariableStrategy nextVariableStrategy = new DefaultNextVariableStrategy(binarySolution6x6Backtrack);
        binarySolution6x6Backtrack.setNextVariableStrategy(nextVariableStrategy);
        DomainOrderingStrategy domainOrderingStrategy = new DefaultOrderingStrategy();
        binarySolution6x6Backtrack.setDomainOrderingStrategy(domainOrderingStrategy);
        binarySolution6x6Backtrack.readProblemFromFile("src/main/resources/data/binary_6x6");
        startTime = System.currentTimeMillis();
        problemSolver.backtrackUntilFirstSolution(binarySolution6x6Backtrack, binarySolution6x6Backtrack.getFirstIndex());
        time = System.currentTimeMillis() - startTime;
        avgTime += time;
        if (time < bestTime) {
            bestTime = time;
        }
        if (time > worstTime) {
            worstTime = time;
        }
        executionTimes.add(time);
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;


        System.out.println("6x6 binary forward search until first solution");
        solutions.BinarySolution binarySolution6x6ForwardChecking = new solutions.BinarySolution();
        nextVariableStrategy = new nextvariablestrategy.LeastDomainSizeStrategy(binarySolution6x6ForwardChecking);
        binarySolution6x6ForwardChecking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new valueorderingstrategy.DefaultOrderingStrategy();
        binarySolution6x6ForwardChecking.setDomainOrderingStrategy(domainOrderingStrategy);
        binarySolution6x6ForwardChecking.readProblemFromFile("src/main/resources/data/binary_6x6");
        startTime = System.currentTimeMillis();
        problemSolver.forwardCheckingUntilFirstSolution(binarySolution6x6ForwardChecking, binarySolution6x6ForwardChecking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;

        System.out.println("8x8 binary backtracking until first solution");
        solutions.BinarySolution binarySolution8x8Backtracking = new solutions.BinarySolution();
        nextVariableStrategy = new nextvariablestrategy.DefaultNextVariableStrategy(binarySolution8x8Backtracking);
        binarySolution8x8Backtracking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new valueorderingstrategy.DefaultOrderingStrategy();
        binarySolution8x8Backtracking.setDomainOrderingStrategy(domainOrderingStrategy);
        binarySolution8x8Backtracking.readProblemFromFile("src/main/resources/data/binary_8x8");
        startTime = System.currentTimeMillis();
        problemSolver.backtrackUntilFirstSolution(binarySolution8x8Backtracking, binarySolution8x8Backtracking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;

        System.out.println("8x8 binary forward search until first solution");
        solutions.BinarySolution binarySolution8x8ForwardChecking = new solutions.BinarySolution();
        nextVariableStrategy = new nextvariablestrategy.LeastDomainSizeStrategy(binarySolution8x8ForwardChecking);
        binarySolution8x8ForwardChecking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new valueorderingstrategy.DefaultOrderingStrategy();
        binarySolution8x8ForwardChecking.setDomainOrderingStrategy(domainOrderingStrategy);
        binarySolution8x8ForwardChecking.readProblemFromFile("src/main/resources/data/binary_8x8");
        startTime = System.currentTimeMillis();
        problemSolver.initialDomainRemoval(binarySolution8x8ForwardChecking);
        problemSolver.forwardCheckingUntilFirstSolution(binarySolution8x8ForwardChecking, binarySolution8x8ForwardChecking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;

        System.out.println("10x10 binary backtracking until first solution");
        solutions.BinarySolution binarySolution10x10Backtracking = new solutions.BinarySolution();
        nextVariableStrategy = new nextvariablestrategy.DefaultNextVariableStrategy(binarySolution10x10Backtracking);
        binarySolution10x10Backtracking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new valueorderingstrategy.DefaultOrderingStrategy();
        binarySolution10x10Backtracking.setDomainOrderingStrategy(domainOrderingStrategy);
        binarySolution10x10Backtracking.readProblemFromFile("src/main/resources/data/binary_10x10");
        startTime = System.currentTimeMillis();
        problemSolver.backtrackUntilFirstSolution(binarySolution10x10Backtracking, binarySolution10x10Backtracking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;

        System.out.println("10x10 binary forward search until first solution");
        solutions.BinarySolution binarySolution10x10ForwardChecking = new solutions.BinarySolution();
        nextVariableStrategy = new nextvariablestrategy.LeastDomainSizeStrategy(binarySolution10x10ForwardChecking);
        binarySolution10x10ForwardChecking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new valueorderingstrategy.DefaultOrderingStrategy();
        binarySolution10x10ForwardChecking.setDomainOrderingStrategy(domainOrderingStrategy);
        binarySolution10x10ForwardChecking.readProblemFromFile("src/main/resources/data/binary_10x10");
        startTime = System.currentTimeMillis();
        problemSolver.initialDomainRemoval(binarySolution10x10ForwardChecking);
        problemSolver.forwardCheckingUntilFirstSolution(binarySolution10x10ForwardChecking, binarySolution10x10ForwardChecking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;

        System.out.println("4x4 futoshiki backtracking until first solution");
        solutions.FutoshikiSolution futoshikiSolution4x4Backtracking = new solutions.FutoshikiSolution();
        nextVariableStrategy = new nextvariablestrategy.DefaultNextVariableStrategy(futoshikiSolution4x4Backtracking);
        futoshikiSolution4x4Backtracking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new valueorderingstrategy.DefaultOrderingStrategy();
        futoshikiSolution4x4Backtracking.setDomainOrderingStrategy(domainOrderingStrategy);
        futoshikiSolution4x4Backtracking.readProblemFromFile("src/main/resources/data/futoshiki_4x4");
        startTime = System.currentTimeMillis();
        problemSolver.backtrackUntilFirstSolution(futoshikiSolution4x4Backtracking, futoshikiSolution4x4Backtracking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;

        System.out.println("4x4 futoshiki forward checking until first solution");
        solutions.FutoshikiSolution futoshikiSolution4x4ForwardChecking = new solutions.FutoshikiSolution();
        nextVariableStrategy = new nextvariablestrategy.LeastDomainSizeStrategy(futoshikiSolution4x4ForwardChecking);
        futoshikiSolution4x4ForwardChecking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new valueorderingstrategy.DefaultOrderingStrategy();
        futoshikiSolution4x4ForwardChecking.setDomainOrderingStrategy(domainOrderingStrategy);
        futoshikiSolution4x4ForwardChecking.readProblemFromFile("src/main/resources/data/futoshiki_4x4");
        startTime = System.currentTimeMillis();
        problemSolver.forwardCheckingUntilFirstSolution(futoshikiSolution4x4ForwardChecking, futoshikiSolution4x4ForwardChecking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;

        System.out.println("5x5 futoshiki backtracking until first solution");
        solutions.FutoshikiSolution futoshikiSolution5x5Backtracking = new solutions.FutoshikiSolution();
        nextVariableStrategy = new nextvariablestrategy.DefaultNextVariableStrategy(futoshikiSolution5x5Backtracking);
        futoshikiSolution5x5Backtracking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new valueorderingstrategy.DefaultOrderingStrategy();
        futoshikiSolution5x5Backtracking.setDomainOrderingStrategy(domainOrderingStrategy);
        futoshikiSolution5x5Backtracking.readProblemFromFile("src/main/resources/data/futoshiki_5x5");
        startTime = System.currentTimeMillis();
        problemSolver.backtrackUntilFirstSolution(futoshikiSolution5x5Backtracking, futoshikiSolution5x5Backtracking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;

        System.out.println("5x5 futoshiki forward search until first solution");
        solutions.FutoshikiSolution futoshikiSolution5x5ForwardChecking = new solutions.FutoshikiSolution();
        nextVariableStrategy = new nextvariablestrategy.LeastDomainSizeStrategy(futoshikiSolution5x5ForwardChecking);
        futoshikiSolution5x5ForwardChecking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new valueorderingstrategy.DefaultOrderingStrategy();
        futoshikiSolution5x5ForwardChecking.setDomainOrderingStrategy(domainOrderingStrategy);
        futoshikiSolution5x5ForwardChecking.readProblemFromFile("src/main/resources/data/futoshiki_5x5");
        startTime = System.currentTimeMillis();
        problemSolver.forwardCheckingUntilFirstSolution(futoshikiSolution5x5ForwardChecking, futoshikiSolution5x5ForwardChecking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;

        System.out.println("6x6 futoshiki backtracking until first solution");
        FutoshikiSolution futoshikiSolution6x6Backtracking = new FutoshikiSolution();
        nextVariableStrategy = new DefaultNextVariableStrategy(futoshikiSolution6x6Backtracking);
        futoshikiSolution6x6Backtracking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new DefaultOrderingStrategy();
        futoshikiSolution6x6Backtracking.setDomainOrderingStrategy(domainOrderingStrategy);
        futoshikiSolution6x6Backtracking.readProblemFromFile("src/main/resources/data/futoshiki_6x6");
        startTime = System.currentTimeMillis();
        problemSolver.backtrackUntilFirstSolution(futoshikiSolution6x6Backtracking, futoshikiSolution6x6Backtracking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;

        System.out.println("6x6 futoshiki forward search until first solution");
        FutoshikiSolution futoshikiSolution6x6ForwardChecking = new FutoshikiSolution();
        nextVariableStrategy = new LeastDomainSizeStrategy(futoshikiSolution6x6ForwardChecking);
        futoshikiSolution6x6ForwardChecking.setNextVariableStrategy(nextVariableStrategy);
        domainOrderingStrategy = new DefaultOrderingStrategy();
        futoshikiSolution6x6ForwardChecking.setDomainOrderingStrategy(domainOrderingStrategy);
        futoshikiSolution6x6ForwardChecking.readProblemFromFile("src/main/resources/data/futoshiki_6x6");
        startTime = System.currentTimeMillis();
        problemSolver.initialDomainRemoval(futoshikiSolution6x6ForwardChecking);
        problemSolver.forwardCheckingUntilFirstSolution(futoshikiSolution6x6ForwardChecking, futoshikiSolution6x6ForwardChecking.getFirstIndex());
        System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
        System.out.println("Going backs: " + ProblemSolver.goingBacks);
        System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
        System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
        System.out.println("______________________________________________________");
        ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;
    }

    private static void researchBacktrackUntilFirstSolution(Solution solution, String path, int n, DomainOrderingStrategy domainOrderingStrategy, NextVariableStrategy nextVariableStrategy) {
        long startTime = 0;
        double avgTime = 0;
        long bestTime = Long.MAX_VALUE;
        long worstTime = Long.MIN_VALUE;
        long time = 0;
        List<Long> executionTimes = new ArrayList<>();
        double standardDeviation = 0;
        ProblemSolver problemSolver = new ProblemSolver();

        for (int i = 0; i < 10; i++) {
            System.out.println("6x6 binary backtracking until first solution");
            nextVariableStrategy.setSolution(solution);
            solution.setNextVariableStrategy(nextVariableStrategy);
            solution.setDomainOrderingStrategy(domainOrderingStrategy);
            solution.readProblemFromFile(path);
            startTime = System.currentTimeMillis();
            problemSolver.backtrackUntilFirstSolution(solution, solution.getFirstIndex());
            time = System.currentTimeMillis() - startTime;
            avgTime += time;
            if (time < bestTime) {
                bestTime = time;
            }
            if (time > worstTime) {
                worstTime = time;
            }
            executionTimes.add(time);
            System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
            System.out.println("Going backs: " + ProblemSolver.goingBacks);
            System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
            System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
            System.out.println("______________________________________________________");
            ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;
        }
        avgTime /= 10;
        System.out.println("Average time: " + avgTime);
        System.out.println("Best time: " + bestTime);
        System.out.println("Worst time: " + worstTime);
        double finalAvgTime = avgTime;
        standardDeviation = Math.sqrt(executionTimes.stream().map(e -> Math.pow(e - finalAvgTime, 2)).mapToDouble(e -> e).sum() / n);
        System.out.println("Standard deviation: " + standardDeviation);
    }

    private static void researchBacktrack(Solution solution, String path, int n, DomainOrderingStrategy domainOrderingStrategy, NextVariableStrategy nextVariableStrategy) {
        long startTime = 0;
        double avgTime = 0;
        long bestTime = Long.MAX_VALUE;
        long worstTime = Long.MIN_VALUE;
        long time = 0;
        List<Long> executionTimes = new ArrayList<>();
        double standardDeviation = 0;
        ProblemSolver problemSolver = new ProblemSolver();

        for (int i = 0; i < 10; i++) {
            System.out.println("6x6 binary backtracking until first solution");
            nextVariableStrategy.setSolution(solution);
            solution.setNextVariableStrategy(nextVariableStrategy);
            solution.setDomainOrderingStrategy(domainOrderingStrategy);
            solution.readProblemFromFile(path);
            startTime = System.currentTimeMillis();
            problemSolver.backtrack(solution, solution.getFirstIndex());
            time = System.currentTimeMillis() - startTime;
            avgTime += time;
            if (time < bestTime) {
                bestTime = time;
            }
            if (time > worstTime) {
                worstTime = time;
            }
            executionTimes.add(time);
            System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
            System.out.println("Going backs: " + ProblemSolver.goingBacks);
            System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
            System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
            System.out.println("______________________________________________________");
            ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;
        }
        avgTime /= 10;
        System.out.println("Average time: " + avgTime);
        System.out.println("Best time: " + bestTime);
        System.out.println("Worst time: " + worstTime);
        double finalAvgTime = avgTime;
        standardDeviation = Math.sqrt(executionTimes.stream().map(e -> Math.pow(e - finalAvgTime, 2)).mapToDouble(e -> e).sum() / n);
        System.out.println("Standard deviation: " + standardDeviation);
    }

    private static void researchForwardCheckingUntilFirstSolution(Solution solution, String path, int n, DomainOrderingStrategy domainOrderingStrategy, NextVariableStrategy nextVariableStrategy) {
        long startTime = 0;
        double avgTime = 0;
        long bestTime = Long.MAX_VALUE;
        long worstTime = Long.MIN_VALUE;
        long time = 0;
        List<Long> executionTimes = new ArrayList<>();
        double standardDeviation = 0;
        ProblemSolver problemSolver = new ProblemSolver();

        for (int i = 0; i < 10; i++) {
            nextVariableStrategy.setSolution(solution);
            solution.setNextVariableStrategy(nextVariableStrategy);
            solution.setDomainOrderingStrategy(domainOrderingStrategy);
            solution.readProblemFromFile(path);
            startTime = System.currentTimeMillis();
            problemSolver.forwardCheckingUntilFirstSolution(solution, solution.getFirstIndex());
            time = System.currentTimeMillis() - startTime;
            avgTime += time;
            if (time < bestTime) {
                bestTime = time;
            }
            if (time > worstTime) {
                worstTime = time;
            }
            executionTimes.add(time);
            System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
            System.out.println("Going backs: " + ProblemSolver.goingBacks);
            System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
            System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
            System.out.println("______________________________________________________");
            ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;
        }
        avgTime /= 10;
        System.out.println("Average time: " + avgTime);
        System.out.println("Best time: " + bestTime);
        System.out.println("Worst time: " + worstTime);
        double finalAvgTime = avgTime;
        standardDeviation = Math.sqrt(executionTimes.stream().map(e -> Math.pow(e - finalAvgTime, 2)).mapToDouble(e -> e).sum() / n);
        System.out.println("Standard deviation: " + standardDeviation);
    }

    private static void researchForwardChecking(Solution solution, String path, int n, DomainOrderingStrategy domainOrderingStrategy, NextVariableStrategy nextVariableStrategy) {
        long startTime = 0;
        double avgTime = 0;
        long bestTime = Long.MAX_VALUE;
        long worstTime = Long.MIN_VALUE;
        long time = 0;
        List<Long> executionTimes = new ArrayList<>();
        double standardDeviation = 0;
        ProblemSolver problemSolver = new ProblemSolver();

        for (int i = 0; i < 10; i++) {
            nextVariableStrategy.setSolution(solution);
            solution.setNextVariableStrategy(nextVariableStrategy);
            solution.setDomainOrderingStrategy(domainOrderingStrategy);
            solution.readProblemFromFile(path);
            startTime = System.currentTimeMillis();
            problemSolver.forwardChecking(solution, solution.getFirstIndex());
            time = System.currentTimeMillis() - startTime;
            avgTime += time;
            if (time < bestTime) {
                bestTime = time;
            }
            if (time > worstTime) {
                worstTime = time;
            }
            executionTimes.add(time);
            System.out.println("Nodes visited: " + ProblemSolver.nodesVisited);
            System.out.println("Going backs: " + ProblemSolver.goingBacks);
            System.out.println("Time spent: " + (System.currentTimeMillis() - startTime));
            System.out.println("Number of solutions: " + ProblemSolver.numberOfSolutions);
            System.out.println("______________________________________________________");
            ProblemSolver.nodesVisited = ProblemSolver.goingBacks = ProblemSolver.numberOfSolutions = 0;
        }
        avgTime /= 10;
        System.out.println("Average time: " + avgTime);
        System.out.println("Best time: " + bestTime);
        System.out.println("Worst time: " + worstTime);
        double finalAvgTime = avgTime;
        standardDeviation = Math.sqrt(executionTimes.stream().map(e -> Math.pow(e - finalAvgTime, 2)).mapToDouble(e -> e).sum() / n);
        System.out.println("Standard deviation: " + standardDeviation);
    }
}
