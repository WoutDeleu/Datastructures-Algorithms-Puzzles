import java.util.HashSet;
import java.util.Set;

public class Ronde {
    private char [] verdachten;
    private int vrager;
    private int antwoorder;
    private boolean geenAntwoord;
    private int verschil;


    public int getVerschil() {
        return verschil;
    }

    public char[] getVerdachten() {
        return verdachten;
    }

    public int getVrager() {
        return vrager;
    }

    public int getAntwoorder() {
        return antwoorder;
    }

    public boolean getGeenAntwoord() {
        return geenAntwoord;
    }

    public Ronde(char[] verdachten, int vrager, int antwoorder, boolean geenAntwoorrd) {
        this.verdachten = verdachten;
        this.vrager = vrager;
        this.antwoorder = antwoorder;
        this.geenAntwoord = geenAntwoorrd;
        this.verschil = this.berekenverschil(vrager, antwoorder);
    }

    private int berekenverschil(int vrager, int antwoorder) {
        int verschil;
        if(!geenAntwoord) {
            verschil = antwoorder - vrager;
            if (verschil < 0) verschil = verschil+3;
            else verschil = verschil -1;
        }
        else verschil = 3;
        return verschil;
    }
}
