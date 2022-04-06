package valueorderingstrategy;

import solutions.Solution;
import valueorderingstrategy.DomainOrderingStrategy;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MinimalConflictsOrderingStrategy implements DomainOrderingStrategy {
    @Override
    public void sortDomain(Solution solution) {
        Map<Integer, List<Integer>> domain = solution.getDomain();
        for (Integer key : domain.keySet()) {
            domain.get(key).sort(Comparator.comparingInt(e -> solution.countDomainRemovals(key, e)));
        }
    }

    @Override
    public void sortDomainForVariable(int index, Solution solution) {
        solution.getDomain().get(index).sort(Comparator.comparingInt(value -> solution.countDomainRemovals(index, value)));
    }
}
