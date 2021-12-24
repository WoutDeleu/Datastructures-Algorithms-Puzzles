import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int aantalTestgevallen = sc.nextInt();
        for(int a = 0; a<aantalTestgevallen; a++) {
            int aantalSattelietverbindingen = sc.nextInt();
            int aantalOnderzoekcentra = sc.nextInt();

            ArrayList<Vetrice> graafLijst = new ArrayList<>(aantalOnderzoekcentra);
            for(int i = 0; i<aantalOnderzoekcentra; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                graafLijst.add(new Vetrice(i,x,y,graafLijst));
            }
            Graaf graaf = new Graaf(graafLijst);

            //minimum spanning tree maken
            Graaf mst = graaf.berekenMST();
            sc.nextInt();
        }
    }
}
