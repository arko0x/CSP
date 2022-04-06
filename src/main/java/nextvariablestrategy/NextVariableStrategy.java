package nextvariablestrategy;

import solutions.Solution;

public interface NextVariableStrategy {
    int getNextIndex(int index);
    void setSolution(Solution solution);
}
