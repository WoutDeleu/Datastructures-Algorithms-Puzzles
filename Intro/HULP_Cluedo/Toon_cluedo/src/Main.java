import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //inlezen aantal testgevallen
        int aantalTestGevallen = sc.nextInt();
        for (int a=1;a<=aantalTestGevallen;a++){
            //inlezen gegevens voor 1 testgeval
            int aantalPersonen = sc.nextInt();
            int aantalLocaties = sc.nextInt();
            int aantalWapens = sc.nextInt();
            int aantalKaartenPerSpeler = (aantalLocaties + aantalPersonen + aantalWapens - 3)/4;
            //De 4 spelers aanmaken
            Speler[] spelers = new Speler[4];
            for(int i = 0; i<4; i++){
                spelers[i] = new Speler(aantalPersonen, aantalLocaties, aantalWapens);
            }
            // Elke ronde inlezen en opslaan
            int aantalRondes = sc.nextInt();
            String[] ronde = new String[aantalRondes];
            String buffer = sc.nextLine(); // Eerste nextLine() zal nog \n nemen van de aantalRondes lijn.
            for(int i = 0; i<aantalRondes; i++) {
                ronde[i] = sc.nextLine();
            }
            boolean uitkomst = false; //Zal true worden wanneer we de uitkomst voor het spel weten
            boolean alGeweten = false; //Wanneer er spelers niet kunnen antwoorden moet dit maar 1 keer opgeslaan worden, dit maakt het programma sneller
            while(!uitkomst){
                for(int i = 0; i<aantalRondes; i++) {
                    //De gegevens van elke ronde
                    int vragendeSpeler = ronde[i].charAt(0)-48;
                    int persoon = (int)ronde[i].charAt(2) -49; //Positie 0 in de array komt overeen met persoon 1 enzovoort
                    int locatie = (int)ronde[i].charAt(3) -65;
                    int wapen = (int)ronde[i].charAt(4) - 97;
                    int antworendeSpeler = (int)ronde[i].charAt(6) - 48;
                    //Kijken welke kaarten de spelers niet hebben.
//Wanneer er geen enkele speler kan antwoorden
                    if(antworendeSpeler == 40 && !alGeweten){
                        for(int j = 1; j<=4;j++){
                            if(j != vragendeSpeler) {
                                //Speler kan kaarten opvragen die hij zelf heeft
                                spelers[j-1].personen[persoon] = 1;
                                spelers[j-1].locaties[locatie] = 1;
                                spelers[j-1].wapens[wapen] = 1;
                            }
                        }
                    }
                    //Wanneer er enkele spelers niet kunnen antwoorden
                    else if((antworendeSpeler - vragendeSpeler > 1 || antworendeSpeler - vragendeSpeler <0) && !alGeweten){
                        int teller = 1;
                        while((vragendeSpeler + teller-1)%4 != antworendeSpeler-1){
                            spelers[(vragendeSpeler + teller -1)%4].personen[persoon] = 1;
                            spelers[(vragendeSpeler + teller -1)%4].locaties[locatie] = 1;
                            spelers[(vragendeSpeler + teller -1)%4].wapens[wapen] = 1;
                            teller++;
                        }
                    }
                    //Kaarten die spelers hebben door te weten welke ze niet hebben.(Als ze 1 kaart tonen en we weten dat ze de andere 2 niethebben)
                    if(antworendeSpeler != 40) {
                        if (spelers[antworendeSpeler - 1].personen[persoon] == 1 && spelers[antworendeSpeler - 1].locaties[locatie] == 1 ) {
                            for (int j = 1; j <= 4; j++) {
                                if (j != antworendeSpeler) {
                                    spelers[j - 1].wapens[wapen] = 1;
                                }
                            }
                            spelers[antworendeSpeler - 1].wapens[wapen] = 2;
                        } else if (spelers[antworendeSpeler - 1].personen[persoon] == 1 && spelers[antworendeSpeler - 1].wapens[wapen] == 1 ) {
                            for (int j = 1; j <= 4; j++) {
                                if (j != antworendeSpeler) {
                                    spelers[j - 1].locaties[locatie] = 1;
                                }
                            }
                            spelers[antworendeSpeler - 1].locaties[locatie] = 2;
                        } else if (spelers[antworendeSpeler - 1].wapens[wapen] == 1 && spelers[antworendeSpeler - 1].locaties[locatie] == 1 ) {
                            for (int j = 1; j <= 4; j++) {
                                if (j != antworendeSpeler) {
                                    spelers[j - 1].personen[persoon] = 1;
                                }}
                            spelers[antworendeSpeler - 1].personen[persoon] = 2;
                        }
                    }
                    for(int j = 0; j<4; j++){
                        spelers[j].uitsluitCheck((aantalLocaties + aantalPersonen + aantalWapens - aantalKaartenPerSpeler)); //checht of er genoeg kaarten zijn uitgesloten om de juiste kaarten te vinden
                        //Zal voor elke geweten oplossing de kaartjes voor de andere spelers op niet hebben zetten
                        for(int k =0; k<aantalPersonen; k++) {
                            if (spelers[j].hebbenPersoonCheck(k)) {
                                for (int l = 0; l < 4; l++) {
                                    if (l != j)
                                        spelers[l].personen[k] = 1;
                                }
                            }
                        }
                        for(int k =0; k<aantalWapens; k++) {
                            if (spelers[j].hebbenWapenCheck(k)) {
                                for (int l = 0; l < 4; l++) {
                                    if (l != j)
                                        spelers[l].wapens[k] = 1;
                                }
                            }
                        }
                        for(int k =0; k<aantalLocaties; k++) {
                            if (spelers[j].hebbenLocatieCheck(k)) {
                                for (int l = 0; l < 4; l++) {
                                    if (l != j)
                                        spelers[l].locaties[k] = 1;
                                }
                            }
                        }
                    }
                }
                //Steeds checken of alle spelers hun kaarten weten om het spelletje te beëindigen
                int gevondenSpelers = 0;
                for(int j = 0; j<4; j++){
                    if(spelers[j].kennis() == aantalKaartenPerSpeler) gevondenSpelers++;
                }
                if(gevondenSpelers == 4)uitkomst = true;
                alGeweten = true;
            }
            //Uitprinten output
            System.out.print(a + " ");
            for(int j = 0; j<3; j++){
                spelers[j].print();
                System.out.print(" ");
            }
            spelers[3].print();
            System.out.println("");
        }
        sc.close();
    }
}