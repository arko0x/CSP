import javax.naming.OperationNotSupportedException;

public class Main {
    public static void main(String[] args) throws OperationNotSupportedException {
        ProblemSolver problemSolver = new ProblemSolver();
        BinarySolution binarySolution = new BinarySolution();
        binarySolution.readProblemFromFile("src/main/resources/data/binary_6x6");
        long startTime = System.currentTimeMillis();
        problemSolver.forwardChecking(binarySolution, binarySolution.getFirstIndex());
        System.out.println(ProblemSolver.forwardCheckingNodesVisits);
        System.out.println(System.currentTimeMillis() - startTime);

        ProblemSolver problemSolver1 = new ProblemSolver();
        BinarySolution binarySolution1 = new BinarySolution();
        binarySolution1.readProblemFromFile("src/main/resources/data/binary_6x6");
        startTime = System.currentTimeMillis();
        problemSolver1.backtrack(binarySolution1, binarySolution1.getFirstIndex());
        System.out.println(ProblemSolver.backtrackNodesVisits);
        System.out.println(System.currentTimeMillis() - startTime);

//        long startTime = System.currentTimeMillis();
//        FutoshikiSolution futoshikiSolution = new FutoshikiSolution();
//        futoshikiSolution.readProblemFromFile("src/main/resources/data/futoshiki_6x6");
//        problemSolver.backtrack(futoshikiSolution, futoshikiSolution.getFirstIndex());
//        long endTime = System.currentTimeMillis() - startTime;
//        System.out.println(endTime / 1000);
    }
}
