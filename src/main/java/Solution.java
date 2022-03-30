import javax.naming.OperationNotSupportedException;
import java.util.List;

public interface Solution {
    void readProblemFromFile(String path);
    default boolean isSolutionValid(int index) throws OperationNotSupportedException{
        throw new OperationNotSupportedException();
    }
    default boolean isSolutionValid(int index, int value) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }
    boolean isSolutionFinal();
    List<Integer> getDomainForVariable(int index);
    void set(int index, int value);
    void print();
    Solution getCopy();
    int getNextIndex(int index);
    int getFirstIndex();
    int size();
}
