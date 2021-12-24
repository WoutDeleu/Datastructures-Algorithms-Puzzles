import java.util.Scanner;

public class Main {
    public static boolean isBestaandWoord(String lettergreep) {
        boolean isWoord = false;
        if(lettergreep.equals("ba")||lettergreep.equals("du")||lettergreep.equals("di")) isWoord =true;
        return isWoord;
    }
    public static boolean isNaomees(String woord) {
        if ( woord.length() == 2 && isBestaandWoord(woord) ) return true;

        //Wanneer het woord maar bestaat uit 2 'lettergreep' is het een woord als deze 2 gelijk zijn en 1 van de basiswoorden zijn
        else if( woord.length() == 4 && ( isBestaandWoord(woord.substring(0,2)) ) && woord.substring(0,2).equals(woord.substring(2,4)) ) {
            return true;
        }
        else {
            //Anders testen we opnieuw of het naomees is (via recursie) met een array waarbij de buitenste elementen verwijdert zijn
            //De 2 buitenste woorden moeten wel aan elkaar gelijk zijn en een bestaand woord zijn
            if( woord.substring(0,2).equals(woord.substring(woord.length()-2)) && isBestaandWoord(woord.substring(0,2)) ) {
                return isNaomees(woord.substring(2,woord.length()-2));
            }
            else return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aantalGevallen;
        String woord;

        aantalGevallen = Integer.parseInt(sc.nextLine());
        for(int i = 1; i<=aantalGevallen; i++) {
            System.out.print(i);
            //loop voor de 5 lijnen
            for (int j = 0; j<5; j++) {
                woord = sc.nextLine();
                int n =0;
                //checken of het een naomees woord is
                if (isNaomees(woord)) System.out.print(" naomees");
                else System.out.print(" onzin");
            }
            System.out.println();
        }

    }
}
