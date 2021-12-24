import java.util.Scanner;

public class Main {
    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);

        int aantalTestgevallen = sc.nextInt();
        for(int a = 1; a<=aantalTestgevallen; a++) {
            int aantalKnooppunten = sc.nextInt();
            int aantalVerbindingen = sc.nextInt();
            Graaf graaf = new Graaf();

            for(int i = 0; i<aantalVerbindingen; i++) {
                int beginKnoopNummer = sc.nextInt();
                int eindKnoopNummer = sc.nextInt();
                int strafpunten = sc.nextInt();

                graaf.addKnoop(beginKnoopNummer);
                graaf.addVerbinding(beginKnoopNummer, new Verbinding(eindKnoopNummer, strafpunten));
                graaf.verwijderOnbereikbaar();
            }
        }
    }
}
