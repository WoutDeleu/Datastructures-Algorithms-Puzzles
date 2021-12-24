import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Math.max;

class Kroegen {
    ArrayList<Kroeg> kroegen;

    public Kroegen(int grootte) {
        kroegen = new ArrayList<>(grootte);
    }
    public void addKroeg(Kroeg k) {
        kroegen.add(k);
    }
    public int getKost(int i) {
        return kroegen.get(i).getPrijs();
    }
    public int getGezelligheidScore(int i) {
        return kroegen.get(i).getGezelligheidScore();
    }
}

class Kroeg {
    private int gezelligheidScore;
    private int prijs;

    public Kroeg(int prijs, int gezelligheidScore) {
        this.prijs = prijs;
        this.gezelligheidScore = gezelligheidScore;
    }
    public int getGezelligheidScore() {
        return gezelligheidScore;
    }
    public int getPrijs() {
        return prijs;
    }
}

public class Main {
    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);

        int aantalTestgevallen = sc.nextInt();
        for(int a = 1; a <=aantalTestgevallen; a++) {
            int budget = sc.nextInt();
            int aantalKroegen = sc.nextInt();
            Kroegen kroegen = new Kroegen(aantalKroegen);
            for(int i = 0; i<=aantalKroegen; i++) {
                if(i==0) kroegen.addKroeg(null);
                else kroegen.addKroeg(new Kroeg(sc.nextInt(), sc.nextInt()));
            }

            int [][] cache = new int[budget+1][aantalKroegen+1];
            for(int i=0; i<=budget; i++) {
                for(int j=0; j<=aantalKroegen; j++) {
                    if(j==0 || i==0 || (i==0 && j==0)) cache[i][j] =0;
                    else {
                        int huidigeKost = kroegen.getKost(j);
                        int huidigeGezelligheid = kroegen.getGezelligheidScore(j);

                        int geenUitgave, uitgave = 0;
                        geenUitgave = cache[i][j-1];
                        if(i>=huidigeKost) uitgave = cache[i-huidigeKost][j-1]+huidigeGezelligheid;
                        cache[i][j] = max(geenUitgave, uitgave);
                    }
                }
            }
            System.out.println(a + " " + cache[budget][aantalKroegen]);

        }
    }
}
