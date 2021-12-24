import java.util.Scanner;
class Speler{
    //Heeft de kaart nog niet gechecked:0
    //Heeft de kaart niet:1
    //Heeft de kaart wel:2
    int[] personen;
    int[] locaties;
    int[] wapens;
    Speler(int aantalPersonen, int aantalLocaties, int aantalWapens){
        //Elke speler begint het spel met mogelijks elke kaart in hand.
        personen = new int[aantalPersonen];
        locaties = new int[aantalLocaties];
        wapens = new int[aantalWapens];
    }
    //Print functie om de kaarten van een speler te printen
    public void print(){
        for(int i =0; i<personen.length; i++){
            if(personen[i] == 2) System.out.print(i+1);
        }
        for(int i =0; i<locaties.length; i++){
            if(locaties[i] == 2)System.out.print((char)(i + 65));
        }
        for(int i =0; i<wapens.length; i++){
            if(wapens[i] == 2)System.out.print((char)(i + 97));
        }
    }
    //Een speler heeft zeker een kaart als het aantal niet hebbende kaarten al voldoende is.
    public void uitsluitCheck(int maxNietHebbendeKaarten){
        int nietHebbendeKaarten = 0;
        for(int i =0; i<personen.length; i++){
            if(personen[i] == 1)nietHebbendeKaarten++;
        }
        for(int i =0; i<locaties.length; i++){
            if(locaties[i] == 1)nietHebbendeKaarten++;
        }
        for(int i =0; i<wapens.length; i++){
            if(wapens[i] == 1)nietHebbendeKaarten++;
        }
        if(nietHebbendeKaarten == maxNietHebbendeKaarten){
            for(int i =0; i<personen.length; i++){
                if(personen[i] == 0)personen[i] = 2;
            }
            for(int i =0; i<locaties.length; i++){
                if(locaties[i] == 0)locaties[i] = 2;
            }
            for(int i =0; i<wapens.length; i++){
                if(wapens[i] == 0)wapens[i] = 2;
            }
        }
    }
    //Kijkt of de speler dit wapen heeft
    public boolean hebbenWapenCheck(int positie){
        if(wapens[positie] == 2)return true;
        else return false;
    }
    //Kijkt of de speler deze locatie heeft
    public boolean hebbenLocatieCheck(int positie){
        if(locaties[positie] == 2)return true;
        else return false;
    }
    //Kijkt of de speler deze persoon heeft
    public boolean hebbenPersoonCheck(int positie){
        if(personen[positie] == 2)return true;
        else return false;
    }
    //Kijkt hoeveel kaarten de speler zeker heeft
    public int kennis() {
        int aantalTrue =0;
        for(int i =0; i<personen.length; i++){
            if(personen[i] == 2)aantalTrue++;
        }
        for(int i =0; i<locaties.length; i++){
            if(locaties[i] == 2)aantalTrue++;
        }
        for(int i =0; i<wapens.length; i++){
            if(wapens[i] == 2)aantalTrue++;
        }
        return aantalTrue;
    }
}