public class Budget {
    private int budget;
    private boolean geldig;

    public boolean isGeldig() {
        return geldig;
    }

    public void setGeldig(boolean geldig) {
        this.geldig = geldig;
    }

    public Budget(int budget) {
        this.budget = budget;
        this.geldig = true;
    }

    public int getBudget() {
        return budget;
    }

    public void schrijf() {
        System.out.print(" " + budget);
    }
}
