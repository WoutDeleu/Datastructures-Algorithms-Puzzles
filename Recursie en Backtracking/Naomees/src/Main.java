import java.util.Arrays;
import java.util.Scanner;

//een lettergreep is een simpel obj met 1 attribuut char array van 2 groot
class LetterGreep {
    char [] letterGeep;

    public LetterGreep(char[] letterGeep) { this.letterGeep = letterGeep; }

    //Vergelijkt de 2 lettergrepen, of deze delelfde inhoud heeft
    public static boolean isgelijk(LetterGreep l1, LetterGreep l2) {
        boolean isgelijk = true;
        for (int i = 0; i < 2; i++) {
            if (l1.letterGeep[i] != l2.letterGeep[i]) isgelijk = false;
        }
        return isgelijk;
    }
}

public class Main {
    //Static array die de 3 basiswoorden opslaat
    public static LetterGreep [] woordenboek = new LetterGreep[3];

    //Controleert of de lettergreep uit het woordenboek komt
    public static boolean woordenboekBevat(LetterGreep l) {
        boolean bevat = false;
        for(int i = 0; i<3; i++) {
            //Vergelijkt of het woord 1 van de 3 basiswoorden is
            if(LetterGreep.isgelijk(woordenboek[i], l)) bevat = true;
        }
        return bevat;
    }

    public static boolean isNaomees(LetterGreep[] woord) {
        //Wanneer het woord maar bestaat uit 1 'lettergreep' is het een woord als het gelijk is aan 1 van de basiswoorden
        if ( woord.length == 1 && woordenboekBevat(woord[0]) ) {
            return true;
        }

        //Wanneer het woord maar bestaat uit 2 'lettergreep' is het een woord als deze 2 gelijk zijn en 1 van de basiswoorden zijn
        else if( woord.length == 2 && ( woordenboekBevat(woord[0]) ) && LetterGreep.isgelijk(woord[0],woord[1]) ) {
            return true;
        }
        else {
            //Anders testen we opnieuw of het naomees is (via recursie) met een array waarbij de buitenste elementen verwijdert zijn
            //De 2 buitenste woorden moeten wel aan elkaar gelijk zijn en een woord uit het woordenboek zijn
            if(LetterGreep.isgelijk(woord[0], woord[woord.length-1]) && woordenboekBevat(woord[0] ) ){
                return isNaomees(Arrays.copyOfRange(woord, 1, (woord.length-1)));
            }
            else return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aantalGevallen;
        String input;
        char [] woord;
        LetterGreep [] woordLettergrepen;

        //Woordenboek vullen met de basiswoorden
        woordenboek[0] = new LetterGreep("ba".toCharArray());
        woordenboek[1] = new LetterGreep("du".toCharArray());
        woordenboek[2] = new LetterGreep("di".toCharArray());

        //loop voor aantal testgevallen
        aantalGevallen = Integer.parseInt(sc.nextLine());
        for(int i = 1; i<=aantalGevallen; i++) {
            System.out.print(i);

            //loop voor de 5 lijnen
            for (int j = 0; j<5; j++) {
                input = sc.nextLine();
                woord = input.toCharArray();

                //Maak array aan met het woord opgesplitst in 'lettergrepen'
                woordLettergrepen = new LetterGreep[woord.length/2];
                int n =0;
                for(int k = 0; k<woord.length; k++) {
                    char [] temp = new char[2];
                    temp[0] = woord[k];
                    k++;
                    temp[1] = woord[k];
                    LetterGreep letterGreep = new LetterGreep(temp);
                    woordLettergrepen[n] = letterGreep;
                    n++;
                }
                //checken of het een naomees woord is
                if (isNaomees(woordLettergrepen)) System.out.print(" naomees");
                else System.out.print(" onzin");
            }
            System.out.println();
        }
    }


}
