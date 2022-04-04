import lombok.Setter;

import java.util.List;

@Setter
public class DefaultNextVariableStrategy implements NextVariableStrategy {
    private Solution solution;

    public DefaultNextVariableStrategy(Solution solution) {
        this.solution = solution;
    }

    @Override
    public int getNextIndex(int index) {
        List<Integer> variables = solution.getVariables();
        for (int i = index + 1; i < variables.size(); i++) {
            if (variables.get(i) == null) {
                return i;
            }
        }
        return -1;
    }
}
