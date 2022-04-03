import javax.naming.OperationNotSupportedException;
import java.util.List;

public interface Solution {
    void readProblemFromFile(String path);
    boolean isSolutionValid(int index, int value);
    boolean isSolutionFinal();
    List<Integer> getDomainForVariable(int index);
    void set(int index, int value);
    void print();
    Solution getCopy();
    int getNextIndex(int index);
    int getFirstIndex();
    int size();
    List<Integer> chooseValuesForIndex(int index);
    void removeImpossibleDomains(int index, int value);
    boolean hasEmptyDomain();
    default void printDomain() {}
}
