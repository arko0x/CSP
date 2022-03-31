import javax.naming.OperationNotSupportedException;

public class Main {
    public static void main(String[] args) throws OperationNotSupportedException {
        ProblemSolver problemSolver = new ProblemSolver();
        BinarySolution binarySolution = new BinarySolution();
        binarySolution.readProblemFromFile("src/main/resources/data/binary_10x10");
        problemSolver.backtrackBinary(binarySolution, binarySolution.getFirstIndex());

        long startTime = System.currentTimeMillis();
        FutoshikiSolution futoshikiSolution = new FutoshikiSolution();
        futoshikiSolution.readProblemFromFile("src/main/resources/data/futoshiki_6x6");
        problemSolver.backtrack(futoshikiSolution, futoshikiSolution.getFirstIndex());
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println(endTime);
        System.out.println(ProblemSolver.numberOfSolutionsFutoshiki);
    }
}
