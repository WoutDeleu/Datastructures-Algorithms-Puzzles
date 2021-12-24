import java.util.*;


class Speler {
    int id;

    public Set<Character> getOnmogelijkheid() {
        return onmogelijkheid;
    }

    Set<Character> mogelijkheid;
    Set<Character> onmogelijkheid;


    public Speler(int id, int aantalPersonen, int aantalWapens, int aantalLocaties) {
        this.id = id;
        mogelijkheid = new HashSet<>();
        onmogelijkheid = new HashSet<>();

        char[] persoonen = "123456789".toCharArray();
        char[] locaties = "ABCDEFGHI".toCharArray();
        char[] wapens = "abcdefghi".toCharArray();

        for (int i = 0; i<aantalPersonen; i++) mogelijkheid.add(persoonen[i]);
        for (int i = 0; i<aantalLocaties; i++) mogelijkheid.add(locaties[i]);
        for (int i = 0; i<aantalWapens; i++) mogelijkheid.add(wapens[i]);
    }

    public int getId() {
        return id;
    }

    public void voegOnmogelijkheidToe(char ch) {
        onmogelijkheid.add(ch);
        mogelijkheid.remove(ch);
    }

    public void printKaarten() {
        ArrayList<Character> k = new ArrayList<>(mogelijkheid);
        Collections.sort(k);
        for(Character c: k) {
            System.out.print(c);
        }
    }
}


public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int aantalTestGevallen, aantalPersonen, aantalWapens, aantalLocaties, aantalAntwoorden, aantalKaartjesPP;
        Set<Character> oplossing = new HashSet<>();

        aantalTestGevallen = sc.nextInt();

        for (int a = 1; a<= aantalTestGevallen; a++) {
            //Inlezen gegevens
            aantalPersonen = sc.nextInt();
            aantalLocaties = sc.nextInt();
            aantalWapens = sc.nextInt();
            aantalAntwoorden = sc.nextInt();
            aantalKaartjesPP = (aantalPersonen + aantalLocaties + aantalWapens - 3)/4;


            //Spelers activeren
            HashMap<Integer, Speler> spelers = new HashMap<>();
            for (int i = 1; i<=4; i++){
                Speler p = new Speler(i, aantalPersonen, aantalWapens, aantalLocaties);
                spelers.put(p.getId(), p);
            }


            //loop over vragen
            int vrager, antwoorder;
            char locatie, wapen, verdachte;
            char[] inputArray;
            String input;
            boolean geenAntwoord;
            for (int b = 0; b<aantalAntwoorden; b++) {

                //input rekening houdend met X als antwoord
                geenAntwoord = false;
                vrager = sc.nextInt();
                input = sc.next();
                if(sc.hasNextInt()) antwoorder = sc.nextInt();
                else {
                    geenAntwoord = true;
                    antwoorder = 0;
                    sc.next();
                }
                inputArray = input.toCharArray();

                //Bepalen welke personen geen antwoord hebben
                int verschil;
                if(!geenAntwoord) {
                    verschil = antwoorder - vrager;
                    if (verschil < 0) verschil = verschil+3;
                    else verschil = verschil -1;
                }
                else verschil = 3;
                int huidig = vrager;
                for (int i = 0; i<verschil; i++) {
                    if(huidig < 4) huidig++;
                    else huidig = 1;
                    for (int j = 0; j<3; j++) {
                        spelers.get(huidig).voegOnmogelijkheidToe(inputArray[j]);
                        huidig++;
                    }
                }
            }
            //Oplossing geven 2 sets
            System.out.print(a + " ");
            for (int i = 1; i<=4; i++){
                spelers.get(i).printKaarten();
                System.out.print(" ");
            }
            System.out.println();
        }
        sc.close();
    }


}
