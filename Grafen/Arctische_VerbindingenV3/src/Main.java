// A Java program for Prim's Minimum Spanning Tree (MST) algorithm.
// The program is for adjacency matrix representation of the graph

import java.util.*;
import java.lang.*;

class Onderzoekcentra {
    private int x;
    private int y;

    public Onderzoekcentra(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public int berekenAfstand(Onderzoekcentra a) {
        double x2 = (double) a.getX();
        double y2 = (double) a.getY();
        double x1 = (double) x;
        double y1 = (double) y;
        return (int) Math.ceil( Math.sqrt( Math.pow((x1-x2),2) + Math.pow((y1-y2),2) ) );
    }

}


class MST {
    // Aantal grafen in de knoop
    private int aantalOnderzoekcentra;

    public MST(int aantalOnderzoekcentra) {
        this.aantalOnderzoekcentra = aantalOnderzoekcentra;
    }

    // A utility function to find the vertex with minimum key
    // value, from the set of vertices not yet included in MST
    int minAfstand(int afstand[], Boolean mstSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < aantalOnderzoekcentra; v++)
            if (mstSet[v] == false && afstand[v] < min) {
                min = afstand[v];
                min_index = v;
            }
        return min_index;
    }


    // Function to construct and print MST for a graph represented
    // using adjacency matrix representation
    int[] primMST(int graph[][]) {
        // Array to store constructed MST
        int knoop[] = new int[aantalOnderzoekcentra];

        // Key values used to pick minimum weight edge in cut
        int afstand[] = new int[aantalOnderzoekcentra];

        // To represent set of vertices included in MST
        Boolean mstSet[] = new Boolean[aantalOnderzoekcentra];

        // Initialize all keys as INFINITE
        for (int i = 0; i < aantalOnderzoekcentra; i++) {
            afstand[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Always include first 1st vertex in MST.
        afstand[0] = 0; // Make afstand 0 so that this vertex is
        // picked as first vertex
        knoop[0] = -1; // First node is always root of MST

        // The MST will have aantalOnderzoekcentra vertices
        for (int count = 0; count < aantalOnderzoekcentra - 1; count++) {
            // Pick thd minimum afstand vertex from the set of vertices
            // not yet included in MST
            int u = minAfstand(afstand, mstSet);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update afstand value and knoop index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < aantalOnderzoekcentra; v++)

                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the afstand only if graph[u][v] is smaller than afstand[v]
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < afstand[v]) {
                    knoop[v] = u;
                    afstand[v] = graph[u][v];
                }
        }
        return afstand;
    }
}
public class Main {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int aantalTestgevallen = sc.nextInt();
        for(int a = 1; a<=aantalTestgevallen; a++) {
            int aantalSattelietverbindingen = sc.nextInt();
            int aantalOnderzoekcentra = sc.nextInt();
            int minimale_kracht = 0;

            //inlezen
            List<Onderzoekcentra> onderzoekcentras = new ArrayList<>();
            for(int i = 0; i<aantalOnderzoekcentra; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                onderzoekcentras.add(new Onderzoekcentra(x,y));
            }

            int [][] adj = new int[aantalOnderzoekcentra][aantalOnderzoekcentra];
            for(int i = 0; i<aantalOnderzoekcentra; i++) {
                for(int j = 0; j<aantalOnderzoekcentra; j++) {
                    adj[i][j] = onderzoekcentras.get(i).berekenAfstand(onderzoekcentras.get(j));
                }
            }
            MST t = new MST(aantalOnderzoekcentra);

            // Print the solution
            boolean al1Uitgedeeld = false;
            int [] afstand = t.primMST(adj);
            if(aantalSattelietverbindingen > 1) {
                for(int i = 0; i<aantalSattelietverbindingen; i++) {
                    int huidigeLangste = 0;
                    int huidigeLangsteIndex = 0;
                    for(int j = 0; j<afstand.length; j++) {
                        if(afstand[j]>huidigeLangste) {
                            huidigeLangste = afstand[j];
                            huidigeLangsteIndex = j;
                        }
                    }
                    afstand[huidigeLangsteIndex] = 0;
                    if(!al1Uitgedeeld) {
                        al1Uitgedeeld = true;
                        i++;
                    }
                }
            }
            for(int j = 0; j<afstand.length; j++) {
                if(afstand[j]>minimale_kracht) {
                    minimale_kracht = afstand[j];
                }
            }
            System.out.println(minimale_kracht);
        }
    }
}