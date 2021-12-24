import java.util.Scanner;
import java.util.ArrayList;

class Persoon {
    private int start;
    private int bestemming;
    private int origineleKost;
    private ArrayList<Integer> alternatieveBestemmingen;

    public Persoon() {
        alternatieveBestemmingen = new ArrayList<>();
    }

    public void setStart(int startlocatie) {
        this.start = startlocatie;
    }
    public void setBestemming(int bestemming) {
        this.bestemming = bestemming;
    }

    public int getStart() {
        return start;
    }
    public int getOrigineleKost() {
        return origineleKost;
    }

    public void berekenKost(int [][] kostenmatrix) {
        origineleKost = kostenmatrix[start-1][bestemming-1];
    }

    public void vindAlternatieveBestemmingen(Bestemmingen bestemmingen, int [][] kostenmatrix) {
        for(int i = 0; i<kostenmatrix.length; i++) {
            int station = i+1;
            int teller = i;
            if(kostenmatrix[start-1][teller] <= origineleKost && bestemmingen.bevat(station)) alternatieveBestemmingen.add(station);
        }
    }

    public ArrayList<Integer> getAlternatieveBestemmingen() {
        return alternatieveBestemmingen;
    }
}

class Personen {
    private ArrayList<Persoon> personen;
    private int [][] kostenmatrix;

    public Personen(int aantalPersonen) {
        this.personen = new ArrayList<>(aantalPersonen);
    }

    public void setPersoon(Persoon p) {
        personen.add(p);
    }
    public Persoon getPersoon(int i) {
        return personen.get(i);
    }
    public void setKostenmatrix(int[][] kostenmatrix) {
        this.kostenmatrix = kostenmatrix;
    }

    public void berekenKost() {
        for(int i =0; i<personen.size(); i++) {
            personen.get(i).berekenKost(kostenmatrix);
        }
    }
    public int berekenTotaleKost() {
        int kost = 0;
        for(Persoon p : personen) {
            kost = kost + p.getOrigineleKost();
        }
        return kost;
    }
    public void berekenAlternatieveBestemmingen(Bestemmingen bestemmingen) {
        for(Persoon p : personen) {
            p.vindAlternatieveBestemmingen(bestemmingen, kostenmatrix);
        }
    }
    static void printHuidigePersoonInfo(int personenCounter, int start, int bestemming, int huidigeBesteKost, int temp) {
        System.out.println('\n' + personenCounter + " van " + start + " " + bestemming + '\n' + " huidigeBesteKost "+ huidigeBesteKost + " temp " + temp + '\n');
    }

    public int bepaalWinst(Bestemmingen bestemmingen, int temp, int huidigeBesteKost, int personenCounter) {
        Persoon huidigePersoon = personen.get(personenCounter);
        ArrayList<Integer> alternatieveBestemmingen = huidigePersoon.getAlternatieveBestemmingen();
        int start = huidigePersoon.getStart();

        //bestemming aanpassen!!
        for(int bestemming : alternatieveBestemmingen) {
            if(bestemmingen.bevat(bestemming)) {
                //printHuidigePersoonInfo(personenCounter,start,bestemming,huidigeBesteKost,temp);
                temp = temp + kostenmatrix[bestemming - 1][start - 1];
                bestemmingen.verwijder(bestemming);
                //doorgaan naar de volgende
                if (temp < huidigeBesteKost && personenCounter < personen.size() - 1) {
                    huidigeBesteKost = bepaalWinst(bestemmingen, temp, huidigeBesteKost, personenCounter + 1);
                    temp = temp - kostenmatrix[bestemming - 1][start - 1];
                    bestemmingen.addBestemming(bestemming);
                }
                //nieuwe beste kost
                else if (temp < huidigeBesteKost && personenCounter == personen.size() - 1) {
                    huidigeBesteKost = temp;
                    bestemmingen.addBestemming(bestemming);
                }
                //Terug naar vorige persoon
                else if (temp >= huidigeBesteKost) {
                    bestemmingen.addBestemming(bestemming);
                    return huidigeBesteKost;
                }
                //Doorgaan naar volgende start
                else {
                    huidigeBesteKost = bepaalWinst(bestemmingen, temp, huidigeBesteKost, personenCounter + 1);
                    temp = temp - kostenmatrix[bestemming - 1][start - 1];
                    bestemmingen.addBestemming(bestemming);
                }
            }
        }
        return huidigeBesteKost;
    }
}

//"Pot" waar alle kaartjes in worden verzameld
class Bestemmingen {
    private ArrayList<Integer> bestemmingen;

    public Bestemmingen(int aantalPersonen) {
        bestemmingen = new ArrayList<>(aantalPersonen);
    }
    public void addBestemming(int bestemming) {
        bestemmingen.add(bestemming);
    }
    public boolean bevat(int station) {
        return bestemmingen.contains(station);
    }
    public void verwijder(int bestemming) {
        bestemmingen.remove(Integer.valueOf(bestemming));
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int aantalTestgevallen = sc.nextInt();
        for(int a = 1;  a<=aantalTestgevallen; a++) {

            //Inlezen kostenmatrix
            int aantalStations = sc.nextInt();
            int [][] kostenmatrix = new int [aantalStations][aantalStations];
            for(int i = 0; i<aantalStations; i++) {
                for(int j = 0; j<aantalStations; j++) {
                    kostenmatrix[i][j] = sc.nextInt();
                }
            }

            //Inlezen
            int aantalPersonen = sc.nextInt();
            Personen personen = new Personen(aantalPersonen);
            Bestemmingen bestemmingen = new Bestemmingen(aantalPersonen);


            for(int i = 0; i<aantalPersonen; i++) {
                personen.setPersoon(new Persoon());
            }
            for(int i = 0; i<aantalPersonen; i++) {
                int start = sc.nextInt();
                personen.getPersoon(i).setStart(start);
            }
            for(int i = 0; i<aantalPersonen; i++) {
                int bestemming = sc.nextInt();
                personen.getPersoon(i).setBestemming(bestemming);
                bestemmingen.addBestemming(bestemming);
            }
            personen.setKostenmatrix(kostenmatrix);
            personen.berekenKost();
            personen.berekenAlternatieveBestemmingen(bestemmingen);
            int origineleTotaalkost = personen.berekenTotaleKost();

            //Initialiseren variabelen nodig in recursie
            int temp = 0;
            int huidigeBesteKost = origineleTotaalkost;
            int personenCounter = 0;

            //Recursieve methode
            int goedkoopste = personen.bepaalWinst(bestemmingen, temp, huidigeBesteKost, personenCounter);

            //Oplossing uitschrijven
            int winst = origineleTotaalkost - goedkoopste;
            System.out.println(a +" "+ winst);
        }
    }
}
