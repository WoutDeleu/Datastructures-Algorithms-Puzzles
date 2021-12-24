import java.util.Scanner;

public class Main {
    private static int[] bereken_langsteVetrices(int[][] mstMatrix) {
        Vetrice [] v = new Vetrice[2];
        int huidigeLangste = 0;
        int [] indexen_voor_langste = new int[2];
        for(int i = 0; i<mstMatrix[0].length; i++) {
            for(int j = 0; j<mstMatrix.length; j++) {
                if(mstMatrix[i][j]>huidigeLangste) {
                    indexen_voor_langste[0] = i;
                    indexen_voor_langste[1] = j;
                    huidigeLangste = mstMatrix[i][j];
                }
            }
        }

        return indexen_voor_langste;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int aantalTestgevallen = sc.nextInt();
        for(int a = 1; a<=aantalTestgevallen; a++) {
            int aantalSattelietverbindingen = sc.nextInt();
            int aantalOnderzoekcentra = sc.nextInt();
            int minimale_kracht;

            //Inlezen graaf
            Graaf graaf = new Graaf();
            for(int i = 0; i<aantalOnderzoekcentra; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                graaf.add(new Vetrice(i, x, y));
            }

            //Bereken MST
            Graaf mst = graaf.berekenMST();

            int [][] mstMatrix = mst.toMatrix(aantalOnderzoekcentra);

            if(aantalSattelietverbindingen != 1) {
                int [] v_array_langste = bereken_langsteVetrices(mstMatrix);
                mstMatrix[v_array_langste[0]][v_array_langste[1]] = 0;
                mstMatrix[v_array_langste[1]][v_array_langste[0]] = 0;
                aantalSattelietverbindingen -= 2;
                while(aantalSattelietverbindingen != 0) {
                    int [] v_array_langste2 = bereken_langsteVetrices(mstMatrix);
                    mstMatrix[v_array_langste2[0]][v_array_langste[1]] = 0;
                    mstMatrix[v_array_langste2[1]][v_array_langste[0]] = 0;
                    aantalSattelietverbindingen--;
                }
            }
            int [] langsteVetrice_indexen = bereken_langsteVetrices(mstMatrix);
            System.out.println(mstMatrix[langsteVetrice_indexen[0]][langsteVetrice_indexen[1]]);
        }
    }



}
