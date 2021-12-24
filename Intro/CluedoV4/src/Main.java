import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class Speler {
    private int id;
    private int aantalKaarten;
    private boolean isVolledig;
    private int aantalKaartenPP;

    private Set<Character> mogelijkheid;
    private Set<Character> onmogelijkheid;
    private Set<Character> zekerheid;

    public int getId() { return id; }
    public void voegOnmogelijkheidToe(char c) { onmogelijkheid.add(c); }
    public void verwijderMogelijkheid(char ch) { mogelijkheid.remove(ch); }
    public boolean bevatZekerheid(char ch) { return zekerheid.contains(ch); }
    public boolean bevatOnmogelijkheid(char ch) { return onmogelijkheid.contains(ch); }

    public Speler(int id, int aantalKaartenPP, int personen, int locaties, int wapens) {
        this.id = id;
        mogelijkheid = new HashSet<>();
        onmogelijkheid = new HashSet<>();
        zekerheid = new HashSet<>();
        this.aantalKaartenPP = aantalKaartenPP;
        this.aantalKaarten = 0;

        char[] per = "123456789".toCharArray();
        char[] pl = "ABCDEFGHI".toCharArray();
        char[] wa = "abcdefghi".toCharArray();
        for (int i = 0; i < personen; i++) mogelijkheid.add(per[i]);
        for (char i = 0; i < locaties; i++) mogelijkheid.add(pl[i]);
        for (char i = 0; i < wapens; i++) mogelijkheid.add(wa[i]);
    }
    public void voegZekerheidToe(char ch) {
        zekerheid.add(ch);
        verwijderMogelijkheid(ch);
        aantalKaarten++;
    }

    public void printKaarten() {
        ArrayList<Character> k = new ArrayList<>(zekerheid);
        Collections.sort(k);
        for(Character c: k) {
            System.out.print(c);
        }
    }
}
class Ronde {
    private char[] verdachten;
    private int vrager;
    private int antwoorder;
    private boolean geenAntwoord;
    private int verschil;

    public Ronde(char[] verdachten, int vrager, int antwoorder, boolean geenAntwoorrd) {
        this.verdachten = verdachten;
        this.vrager = vrager;
        this.antwoorder = antwoorder;
        this.geenAntwoord = geenAntwoorrd;
        this.verschil = this.berekenverschil(vrager, antwoorder);
    }

    public int getVerschil() { return verschil; }
    public int getVrager() { return vrager; }
    public boolean getGeenAntwoord() { return geenAntwoord; }
    public int getAntwoorder() { return antwoorder; }

    public int berekenverschil(int vrager, int antwoorder) {
        int verschil;
        if(!geenAntwoord) {
            verschil = antwoorder - vrager;
            if (verschil < 0) verschil = verschil+3;
            else verschil = verschil -1;
        }
        else verschil = 3;
        return verschil;
    }

    public char[] getVerdachten() {
        return verdachten;
    }
}

public class Main {
    private static void printKaarten(HashMap<Integer,Speler> spelers) {
        for (int i = 1; i<=4; i++) {
            System.out.print(" ");
            spelers.get(i).printKaarten();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aantalTestGevallen, aantalPersonen, aantalWapens, aantalLocaties, aantalRondes, aantalKaartjesPP;
        aantalTestGevallen = sc.nextInt();

        for (int a = 1; a <= aantalTestGevallen; a++) {
            //Inlezen gegevens
            aantalPersonen = sc.nextInt();
            aantalLocaties = sc.nextInt();
            aantalWapens = sc.nextInt();
            aantalRondes = sc.nextInt();

            aantalKaartjesPP = (aantalPersonen + aantalLocaties + aantalWapens - 3) / 4;

            Ronde[] rondes = new Ronde[aantalRondes];

            //Spelers activeren
            HashMap<Integer, Speler> spelers = new HashMap<>();
            for (int i = 1; i<=4; i++){
                Speler p = new Speler(i, aantalKaartjesPP, aantalPersonen, aantalLocaties, aantalWapens);
                spelers.put(p.getId(), p);
            }

            //Alle vragen'rondes' in een array steken
            int vrager, antwoorder;
            char[] inputArray;
            String input;
            boolean geenAntwoord;
            for (int b = 0; b<aantalRondes; b++) {
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

                rondes[b] = new Ronde(inputArray, vrager, antwoorder, geenAntwoord);
            }

            //ronden overlopen
            int c = 0;
            boolean verandering;
            while(c<=rondes.length) {
                //We overlopen alle rondes
                verandering = false;

                //We overlopen voor de rondes zelf
                if(c<rondes.length) {
                    int verschil = rondes[c].getVerschil();
                    int huidig = rondes[c].getVrager();
                    char [] verdachten = rondes[c].getVerdachten();
                    for (int i = 0; i<verschil; i++) {
                        if (huidig < 4) huidig++;
                        else huidig = 1;
                        for(int j = 0; j<3; j++) {
                            if(!spelers.get(huidig).bevatOnmogelijkheid(verdachten[j])) {
                                spelers.get(huidig).verwijderMogelijkheid(verdachten[j]);
                                spelers.get(huidig).voegOnmogelijkheidToe(verdachten[j]);
                                verandering = true;
                            }
                        }
                    }
                    if(!rondes[c].getGeenAntwoord()) {
                        int controleInt, zekerheid;
                        controleInt = 0;
                        zekerheid = 0;
                        for(int j = 0; j<3; j++) {
                            if(spelers.get(rondes[c].getAntwoorder()).bevatOnmogelijkheid(verdachten[j])) {
                                controleInt++;
                            }
                            else zekerheid = j;
                        }
                        if (controleInt == 2 && !spelers.get(rondes[c].getAntwoorder()).bevatZekerheid(verdachten[zekerheid])) {
                            spelers.get(rondes[c].getAntwoorder()).voegZekerheidToe(verdachten[zekerheid]);
                            spelers.get(rondes[c].getAntwoorder()).verwijderMogelijkheid(verdachten[zekerheid]);
                            for(int i = 1; i<=4; i++) {
                                if(i != rondes[c].getAntwoorder()) {
                                    spelers.get(i).voegOnmogelijkheidToe(verdachten[zekerheid]);
                                    spelers.get(i).verwijderMogelijkheid(verdachten[zekerheid]);
                                }
                            }
                            verandering = true;
                        }
                    }
                }
                if(verandering) c= 0;
                else c++;
            }
            System.out.print(a);
            printKaarten(spelers);
        }
        sc.close();
    }
}
