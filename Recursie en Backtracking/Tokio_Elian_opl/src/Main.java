import java.util.*;

class Persoon{
    private int beginStation;
    private int eindStation;
    private int kost;   //Hoeveel de rit normaal zou kosten
    Persoon(){
        this.beginStation = 0;
        this.eindStation = 0;
        this.kost = 0;
    }
    public void setEindStation(int eindStation){this.eindStation = eindStation;}
    public void setBeginStation(int beginStation){this.beginStation = beginStation;}
    public void setKost(int kost){this.kost = kost;}
    public int getBeginStation(){return beginStation;}
    public int getEindStation(){return eindStation;}
    public int getKost(){return kost;}
}
class Personen{
    private List<Persoon> personen;
    Personen(){personen = new ArrayList<>();}

    public void voegPersoonToe(Persoon persoon){personen.add(persoon);}
    public List<Persoon> getPersonen(){return personen;}
    public Persoon getPersoon(int tellerPersoon) {return personen.get(tellerPersoon);}
    public int getGrootte(){return personen.size();}
}

public class Main{
    //static variabelen en methoden kunnen hier
    static int aantalStations;
    static int aantalPersonen;
    static int normaleTotaleKost;
    static int globaleWinst;
    static int tijdelijkeBestemming;
    static List<Integer> bestemmingen;
    static Map<Integer, Integer> vrijeBestemmingen;
    static int[][] kostenMatrix;

    //Berekent de normale kost, de kost wat het normaal zou zijn zonder de tickets te wisselen
    private static void berekenKost(int [][]kostenMatrix, Persoon persoon){
        int begin = persoon.getBeginStation()-1;
        int einde = persoon.getEindStation()-1;
        int kost = kostenMatrix[begin][einde];
        persoon.setKost(kost);
        normaleTotaleKost +=  kost;
    }

    private static void berekenWinst(int[][] kostenMatrix, List<Integer> bestemmingen, Personen personen, int tellerPersoon, int totaleTestKost) {
        Persoon persoon = personen.getPersoon(tellerPersoon);
        for (int bestemming : bestemmingen) {
            tijdelijkeBestemming = bestemming;
            if (vrijeBestemmingen.get(bestemming) > 0) {
                vrijeBestemmingen.put(bestemming, vrijeBestemmingen.get(bestemming)-1);
                int kost = kostenMatrix[persoon.getBeginStation() - 1][bestemming - 1];
                totaleTestKost += kost;
                if(kost <= persoon.getKost() && tellerPersoon < personen.getGrootte()-1){
                    berekenWinst(kostenMatrix,bestemmingen,personen,tellerPersoon+1,totaleTestKost);
                    totaleTestKost -= kost;
                    vrijeBestemmingen.put(bestemming, vrijeBestemmingen.get(bestemming)+1);
                }else if (tellerPersoon == personen.getGrootte()-1){
                    if (totaleTestKost < normaleTotaleKost){
                        int tijdelijkeWinst = normaleTotaleKost - totaleTestKost;
                        if (globaleWinst < tijdelijkeWinst){
                            globaleWinst = tijdelijkeWinst;
                            //System.out.println("-- De tijdelijke winst: "+ tijdelijkeWinst);
                            //System.out.println("-- Globale winst: "+ globaleWinst);
                        }
                    }
                    totaleTestKost -= kost;
                    vrijeBestemmingen.put(bestemming, vrijeBestemmingen.get(bestemming)+1);
                }else{
                    totaleTestKost -= kost;
                    vrijeBestemmingen.put(bestemming, vrijeBestemmingen.get(bestemming)+1);
                }
            }
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        //inlezen aantal testgevallen
        int aantalTestGevallen = Integer.parseInt(sc.nextLine());
        for (int a=1;a<=aantalTestGevallen;a++){
            //inlezen gegevens voor 1 testgeval
            normaleTotaleKost = 0;
            bestemmingen = new ArrayList<>();
            Personen personen = new Personen();
            aantalStations = Integer.parseInt(sc.nextLine());
            kostenMatrix = new int[aantalStations][aantalStations];
            for (int i = 0; i < aantalStations; i++) {
                for (int j = 0; j < aantalStations; j++) {
                    kostenMatrix[i][j] = sc.nextInt();
                }
                sc.nextLine();
            }
            aantalPersonen = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < aantalPersonen; i++) {
                Persoon persoon = new Persoon();
                persoon.setBeginStation(sc.nextInt());
                personen.voegPersoonToe(persoon);
            }
            sc.nextLine();
            vrijeBestemmingen = new HashMap<>();
            for(Persoon persoon : personen.getPersonen()){
                int einde = sc.nextInt();
                persoon.setEindStation(einde);
                bestemmingen.add(einde);
                if (!vrijeBestemmingen.containsKey(einde)){
                    vrijeBestemmingen.put(einde,1);
                }else{
                    vrijeBestemmingen.put(einde,vrijeBestemmingen.get(einde)+1);
                }
                berekenKost(kostenMatrix, persoon);
            }
            sc.nextLine();
            int tellerPersoon = 0;
            globaleWinst = 0;
            int totaleTestKost = 0;
            berekenWinst(kostenMatrix,bestemmingen,personen,tellerPersoon, totaleTestKost);
            //oplossen van 1 testgeval

            //uitschrijven van 1 testgeval met nummer a
            System.out.println(a+" "+globaleWinst);
            vrijeBestemmingen.clear();
        }
        sc.close();
    }
}
