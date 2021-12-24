import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int aantalTestGevallen, aantalPersonen, aantalWapens, aantalLocaties, aantalAntwoorden, aantalKaartjesPP;
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
                Speler p = new Speler(i, aantalKaartjesPP, aantalPersonen, aantalLocaties, aantalWapens);
                spelers.put(p.getId(), p);
            }


            //loop over vragen
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

                //Bepalen welke personen geen antwoord hebben
                int verschil;
                if(!geenAntwoord) {
                    verschil = antwoorder - vrager;
                    if (verschil < 0) verschil = verschil+3;
                    else verschil = verschil -1;
                }
                else verschil = 3;

                //loop over condities bij antwoorders
                int huidig = vrager;
                for (int i = 0; i<verschil; i++) {
                    if(huidig < 4) huidig++;
                    else huidig = 1;

                    for (int j = 0; j<3; j++) {
                        spelers.get(huidig).voegOnmogelijkheidToe(inputArray[j]);

                        //b=0;
                    }
                }
                int conditieCounter = 0;
                int zekerheidsnr = 0;
                if(!geenAntwoord) {
                    for (int j = 0; j<3; j++) {
                        if(!spelers.get(antwoorder).bevatOnmoglijkheid(inputArray[j]))
                            conditieCounter++;
                            zekerheidsnr = j;
                    }
                    if (conditieCounter==1) {
                        spelers.get(huidig).voegZekerheidToe(inputArray[zekerheidsnr]);
                        //b=0;
                    }
                }
                /*

                spelers.get(1).printKaarten();
                spelers.get(1).printmog();
                spelers.get(1).printonm();
                spelers.get(2).printKaarten();
                spelers.get(2).printmog();
                spelers.get(2).printonm();
                spelers.get(3).printKaarten();
                spelers.get(3).printmog();
                spelers.get(3).printonm();

                 */
            }

            System.out.print(a + " ");
            for (int i = 1; i<=4; i++){
                spelers.get(i).vergelijk();
                spelers.get(i).printKaarten();
                System.out.print(" ");
            }
            System.out.println();
        }
        sc.close();
    }


}
