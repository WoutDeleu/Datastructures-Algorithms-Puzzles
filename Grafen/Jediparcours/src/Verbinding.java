public class Verbinding {
    private int eindknoop;
    private int strafpunten;

    public Verbinding(int eindknoop, int strafpunten) {
        this.eindknoop = eindknoop;
        this.strafpunten = strafpunten;
    }

    public int getEindknoop() {
        return eindknoop;
    }

    public int getStrafpunten() {
        return strafpunten;
    }
}
