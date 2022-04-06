package nextvariablestrategy;

import lombok.Setter;
import nextvariablestrategy.NextVariableStrategy;
import solutions.Solution;

import java.util.List;
import java.util.Map;

@Setter
public class LeastDomainSizeStrategy implements NextVariableStrategy {
    private Solution solution;

    public LeastDomainSizeStrategy(Solution solution) {
        this.solution = solution;
    }

    @Override
    public int getNextIndex(int index) {
        int min = Integer.MAX_VALUE;
        int indexToReturn = -1;
        Map<Integer, List<Integer>> domain = solution.getDomain();
        for (Integer key : domain.keySet()) {
            if (domain.get(key).size() < min && solution.getVariables().get(key) == null && key != index) {
                min = domain.get(key).size();
                indexToReturn = key;
            }
        }
        return indexToReturn;
    }
}
