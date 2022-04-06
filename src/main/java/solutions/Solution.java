package solutions;

import java.util.List;
import java.util.Map;

public interface Solution {
    Map<Integer, List<Integer>> getDomain();
    List<Integer> getVariables();
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
    int countDomainRemovals(int index, int value);
    int countConstraintsBrokenAfterSettingDomainValues(int index);
    boolean hasEmptyDomain();
    default void printDomain() {}
}
