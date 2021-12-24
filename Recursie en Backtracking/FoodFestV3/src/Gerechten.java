import java.util.ArrayList;
import java.util.List;

public class Gerechten {
    private List<Gerecht> gerechten;

    public Gerechten(int aantalGerechten) {
        gerechten = new ArrayList(aantalGerechten);
    }
    public void append(Gerecht gerecht) {
        gerechten.add(gerecht);
    }
    public int getSize() {
        return gerechten.size();
    }
    public Gerecht getGerecht(int j) {
        return gerechten.get(j);
    }
    public int getIndex(int prijs) {
        return gerechten.indexOf(prijs);
    }
    public void setStart() {
        for (Gerecht g : gerechten) {
            g.setStart();
        }
    }
    public void checkGeldigheidBudget(int budgetWaarde) {
        for(Gerecht g : gerechten) {
            if(g.getPrijs() > budgetWaarde) {
                g.setGeldig(false);
            }
            else g.setGeldig(false);
        }
    }
}
