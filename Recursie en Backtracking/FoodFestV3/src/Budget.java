public class Budget {
    private int budget;
    private boolean geldig;

    public Budget(int budget) {
        this.budget = budget;
    }
    public int getWaarde() {
        return budget;
    }
    public void setGeldigheid(boolean b) {
        geldig = b;
    }
}
