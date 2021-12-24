public class Gerecht {
    private int prijs;
    private boolean geldig;

    public Gerecht(int prijs) {
        this.prijs = prijs;
        geldig = true;
    }
    public boolean isGeldig() {
        return geldig;
    }
    public void setGeldig(boolean geldig) {
        this.geldig = geldig;
    }
    public int getPrijs() {
        return prijs;
    }
    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }
    public void setStart() {
        geldig = true;
    }
}
