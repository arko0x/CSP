package valueorderingstrategy;

import solutions.Solution;

public interface DomainOrderingStrategy {
    void sortDomain(Solution solution);
    void sortDomainForVariable(int index, Solution solution);
}