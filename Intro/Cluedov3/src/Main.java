import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aantalTestGevallen, aantalPersonen, aantalWapens, aantalLocaties, aantalAntwoorden, aantalKaartjesPP;
        aantalTestGevallen = sc.nextInt();

        for (int a = 1; a <= aantalTestGevallen; a++) {
            //Inlezen gegevens
            aantalPersonen = sc.nextInt();
            aantalLocaties = sc.nextInt();
            aantalWapens = sc.nextInt();
            aantalAntwoorden = sc.nextInt();
            aantalKaartjesPP = (aantalPersonen + aantalLocaties + aantalWapens - 3) / 4;
            Ronde[] rondes = new Ronde[aantalAntwoorden];
            Set<Character> oplossing = new HashSet<>(3);

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

                rondes[b] = new Ronde(inputArray, vrager, antwoorder, geenAntwoord);
            }

            //ronden overlopen
            int c = 0;
            boolean verandering;
            while (c<rondes.length) {
            //while(true) {
                //loop over condities bij antwoorders
                verandering = false;
                int verschil = rondes[c].getVerschil();
                int huidig = rondes[c].getVrager();

                for (int i = 0; i<verschil; i++) {
                    if(huidig < 4) huidig++;
                    else huidig = 1;

                    for (int j = 0; j<3; j++) {
                        if(spelers.get(huidig).bevatMoglijkheid(rondes[c].getVerdachten()[j])) {
                            verandering = true;
                            spelers.get(huidig).voegOnmogelijkheidToe(rondes[c].getVerdachten()[j]);
                        }
                    }
                }
                int conditieCounter = 0;
                int zekerheidsnr = 0;
                if(!rondes[c].getGeenAntwoord()) {
                    for (int j = 0; j<3; j++) {
                        if(!spelers.get(rondes[c].getAntwoorder()).bevatOnmoglijkheid(rondes[c].getVerdachten()[j])) conditieCounter++;
                        zekerheidsnr = j;
                    }
                    if (conditieCounter==1) {
                        if(!spelers.get(huidig).bevatZekerheid(rondes[c].getVerdachten()[zekerheidsnr])) verandering = true;
                        spelers.get(huidig).voegZekerheidToe(rondes[c].getVerdachten()[zekerheidsnr]);
                        for(int i = 1; i<=4; i++) {
                            if (i != huidig) {
                                spelers.get(i).voegOnmogelijkheidToe(rondes[c].getVerdachten()[zekerheidsnr]);
                            }
                        }
                    }
                }

                //Bepalen oplossing
                else {
                    boolean isOplossing = true;
                    for(int j = 0; j<3; j++) {
                        char verdachte = rondes[c].getVerdachten()[j];
                        for (Speler s : spelers.values()) {
                            if(!s.bevatOnmoglijkheid(verdachte)) {
                                isOplossing = false;
                            }
                        }
                        if(isOplossing) {
                            if(!oplossing.contains(verdachte)) {
                                oplossing.add(verdachte);
                                verandering =true;
                            }
                            for(Speler s: spelers.values()) {
                                s.voegOnmogelijkheidToe(verdachte);
                            }
                        }
                    }
                }
                spelers.get(1).vergelijk(spelers.get(2), spelers.get(3), spelers.get(4));
                spelers.get(2).vergelijk(spelers.get(1), spelers.get(3), spelers.get(4));
                spelers.get(3).vergelijk(spelers.get(2), spelers.get(1), spelers.get(4));
                spelers.get(4).vergelijk(spelers.get(2), spelers.get(3), spelers.get(1));
                if(verandering) c = 0;
                else c++;
           }
            System.out.print(a + " ");
            for (int i = 1; i<=4; i++) {
                spelers.get(i).eindVergelijking();
                spelers.get(i).printKaarten();
                System.out.print(" ");
            }
            System.out.println();
        }
        sc.close();
    }
}