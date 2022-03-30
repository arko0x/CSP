import javax.naming.OperationNotSupportedException;

public class Main {
    public static void main(String[] args) throws OperationNotSupportedException {
        ProblemSolver problemSolver = new ProblemSolver();
        BinarySolution binarySolution = new BinarySolution();
        binarySolution.readProblemFromFile("src/main/resources/data/binary_10x10");
        problemSolver.backtrackBinary(binarySolution, binarySolution.getFirstIndex());

        FutoshikiSolution futoshikiSolution = new FutoshikiSolution();
        futoshikiSolution.readProblemFromFile("src/main/resources/data/futoshiki_5x5");
        problemSolver.backtrack(futoshikiSolution, futoshikiSolution.getFirstIndex());
    }
}
