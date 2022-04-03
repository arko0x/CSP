import javax.naming.OperationNotSupportedException;

public class Main {
    public static void main(String[] args) throws OperationNotSupportedException {
        ProblemSolver problemSolver = new ProblemSolver();
        BinarySolution binarySolution = new BinarySolution();
        binarySolution.readProblemFromFile("src/main/resources/data/binary_10x10");
        problemSolver.forwardChecking(binarySolution, binarySolution.getFirstIndex());
        System.out.println(ProblemSolver.forwardCheckingNodesVisits);

        ProblemSolver problemSolver1 = new ProblemSolver();
        BinarySolution binarySolution1 = new BinarySolution();
        binarySolution1.readProblemFromFile("src/main/resources/data/binary_10x10");
        problemSolver1.backtrack(binarySolution1, binarySolution1.getFirstIndex());
        System.out.println(ProblemSolver.backtrackNodesVisits);

//        long startTime = System.currentTimeMillis();
//        FutoshikiSolution futoshikiSolution = new FutoshikiSolution();
//        futoshikiSolution.readProblemFromFile("src/main/resources/data/futoshiki_6x6");
//        problemSolver.backtrack(futoshikiSolution, futoshikiSolution.getFirstIndex());
//        long endTime = System.currentTimeMillis() - startTime;
//        System.out.println(endTime / 1000);
    }
}
