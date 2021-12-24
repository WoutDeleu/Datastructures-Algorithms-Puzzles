import java.util.*;
import java.lang.Math;
//andere klassen kunnen hier als geen public
class Knoop{
    private int xcoordinaat;
    private int ycoordinaat;
    Knoop(String[] s){
        xcoordinaat=Integer.parseInt(s[0]);
        ycoordinaat=Integer.parseInt(s[1]);
    }
    public int getX(){return xcoordinaat;}
    public int getY(){return ycoordinaat;}
}
class MST {
    //aantal knopen in graaf
    private int V;

    public void setV(int v) {V = v;}
    // functie om het kleinste pad te vinden
    // value, from the set of vertices not yet included in MST
    int minKey(int key[], Boolean mstSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        return min_index;
    }

    // De lengte van het pad printen
    // Eerst het grootste pad zoeken
    void printMST(int parent[], int graph[][]) {
        int max=0;
        for (int i = 1; i < V; i++) {
            if(max<graph[i][parent[i]]) max=graph[i][parent[i]];
        }
        System.out.println(max);
    }

    // Function to construct and print MST for a graph represented
    // adjacency matrix representatie
    void primMST(int graph[][]) {
        // Array to store constructed MST
        int parent[] = new int[V];
        // Key values used to pick minimum weight edge in cut
        int key[] = new int[V];
        // To represent set of vertices included in MST
        Boolean mstSet[] = new Boolean[V];

        // Initialize all keys as INFINITE
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Always include first 1st vertex in MST.
        key[0] = 0; // Make key 0 so that this vertex is
        // picked as first vertex
        parent[0] = -1; // First node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < V; v++)

                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }
        // print the constructed MST
        printMST(parent, graph);
    }
}

public class Main{
    //static variabelen en methoden kunnen hier

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        //inlezen aantal testgevallen
        //kies 1 van 2 onderstaande
        //als er ook lijnen gelezen moeten worden
        int aantalTestGevallen = Integer.parseInt(sc.nextLine());
        //anders
        //int aantalTestGevallen = sc.nextInt();

        for (int a=1;a<=aantalTestGevallen;a++){
            //inlezen gegevens voor 1 testgeval
            int satelietverbindingen,onderzoekscentra;
            String rij=sc.nextLine();
            String[] splitrij=rij.split(" ");
            satelietverbindingen=Integer.parseInt(splitrij[0]);
            onderzoekscentra=Integer.parseInt(splitrij[1]);
            MST t = new MST();
            t.setV(onderzoekscentra);
            Knoop[] knopen= new Knoop[onderzoekscentra];
            int[][] matrix= new int[onderzoekscentra][onderzoekscentra];
            //knopen aanmaken en coordinaten toe kennen
            for(int g=0;g<onderzoekscentra;g++){
                String rij1=sc.nextLine();
                String[] rij1split= rij1.split(" ");
                knopen[g]=new Knoop(rij1split);
            }
            //oplossen van 1 testgeval
            //matrix aanmaken met de afstand tot een ander knooppunt
            for(int i=0;i<onderzoekscentra;i++){
                for(int j=0;j<onderzoekscentra;j++){
                    if(i!=j){ //afronden naar boven
                        int xAfstandkwadraat=(int)Math.ceil((Math.pow(knopen[i].getX()-knopen[j].getX(),2)));//x²
                        int yAfstandkwadraat=(int)Math.ceil((Math.pow(knopen[i].getY()-knopen[j].getY(),2)));//y²
                        int afstand=(int)Math.ceil((Math.sqrt(xAfstandkwadraat+yAfstandkwadraat)));//sqrt(x²+y²)
                        matrix[i][j]=afstand;// afstand in matrix zetten
                    }
                }
            }

            //rekening houden met satelliet verbinding, grootste pad op 0 zetten
            while(satelietverbindingen!=1){
                int max=0;
                int x=0;
                int y=0;
                for(int i=0;i<onderzoekscentra;i++){
                    for(int j=0;j<onderzoekscentra;j++){
                        if(max<matrix[i][j]) {//als max kleiner is als de waarde in de matrix dan deze waarde nemen en de coordinaten opslaan
                            max = matrix[i][j];
                            x=i;
                            y=j;
                        }
                    }
                }
                matrix[x][y]=1;//de afstanden op 1 zetten zodat het algoritme dit pad ook gebruikt in de graaf
                matrix[y][x]=1;// moest het 0 zijn dat zou hij dit pad niet meerekenen
                ;               satelietverbindingen--;
            }
            //uitschrijven van 1 testgeval met nummer a
            //vaak
            System.out.print(a+" ");
            t.primMST(matrix);
        }
        sc.close();
    }
}