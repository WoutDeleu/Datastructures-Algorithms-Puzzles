import java.util.ArrayList;

public class Budgetten {
    private ArrayList<Budget> budgetten;

    public Budgetten(int aantalBudgetten) {
        budgetten = new ArrayList<>(aantalBudgetten);
    }
    public void add(Budget b) {
        budgetten.add(b);
    }
    public int getSize() {
        return budgetten.size();
    }
    public Budget getBudget(int i){
        return budgetten.get(i);
    }
    public void setBudget(int index, Budget huidigBudget) {
        budgetten.set(index, huidigBudget);
    }
}
