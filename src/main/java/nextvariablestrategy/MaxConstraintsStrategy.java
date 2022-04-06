package nextvariablestrategy;

import lombok.Setter;
import nextvariablestrategy.NextVariableStrategy;
import solutions.Solution;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Setter
public class MaxConstraintsStrategy implements NextVariableStrategy {
    private Solution solution;

    public MaxConstraintsStrategy(Solution solution) {
        this.solution = solution;
    }

    @Override
    public int getNextIndex(int index) {
        int max = Integer.MIN_VALUE;
        int indexToReturn = -1;
        Map<Integer, List<Integer>> domain = solution.getDomain();
        for (Integer ind : domain.keySet()) {
            if (ind != index && solution.getVariables().get(ind) == null) {
                int val = domain.get(ind).stream().max(Comparator.comparingInt(e -> solution.countConstraintsBrokenAfterSettingDomainValues(ind))).get();
                if (val > max) {
                    max = val;
                    indexToReturn = ind;
                }
            }
        }
        return indexToReturn;
    }
}
