import java.util.Scanner;

class Edge {
    private int src, dest, weight;
    public Edge() {
        src = dest = weight = 0;
    }

    public int getSrc() {
        return src;
    }

    public int getDest() {
        return dest;
    }

    public int getWeight() {
        return weight;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

class Graph {
    private int aantalKnopen, aantalEdges;
    private Edge edge [];

    Graph(int v, int e) {
        aantalKnopen = v;
        aantalEdges = e;
        edge = new Edge[e];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    public Edge getEdge(int i) {
        return edge[i];
    }

    public int getAantalKnopen() {
        return aantalKnopen;
    }

    public int getAantalEdges() {
        return aantalEdges;
    }
}



public class Main {
    static int BellmanFord(Graph graph, int src)
    {
        int aantalKnopen = graph.getAantalKnopen();
        int aantalEdges = graph.getAantalEdges();
        int dist[] = new int[aantalKnopen];

        // Step 1: Initialize distances from src to all other
        // vertices as INFINITE
        for (int i = 0; i < aantalKnopen; ++i)
            dist[i] = Integer.MAX_VALUE;
        dist[src] = 0;

        // Step 2: Relax all edges |V| - 1 times. A simple
        // shortest path from src to any other vertex can
        // have at-most |V| - 1 edges
        for (int i = 1; i < aantalKnopen; ++i) {
            for (int j = 0; j < aantalEdges; ++j) {
                int startpunt = graph.getEdge(j).getSrc();
                int eindpunt = graph.getEdge(j).getDest();
                int weight = graph.getEdge(j).getWeight();
                if (dist[startpunt-1] != Integer.MAX_VALUE && dist[startpunt-1] + weight < dist[eindpunt-1])
                    dist[eindpunt-1] = dist[startpunt-1] + weight;
            }
        }

        // Step 3: check for negative-weight cycles. The above
        // step guarantees shortest distances if graph doesn't
        // contain negative weight cycle. If we get a shorter
        // path, then there is a cycle.
        for (int j = 0; j < aantalEdges; ++j) {
            int startpunt = graph.getEdge(j).getSrc();
            int eindpunt = graph.getEdge(j).getDest();
            int weight = graph.getEdge(j).getWeight();
            if (dist[startpunt-1] != Integer.MAX_VALUE && dist[startpunt-1] + weight < dist[eindpunt-1]) {
                return Integer.MIN_VALUE;
            }
        }
        return dist[dist.length-1];
    }

    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);

        int aantalTestgevallen = sc.nextInt();
        for(int a = 1; a<=aantalTestgevallen; a++) {
            int aantalKnooppunten = sc.nextInt();
            int aantalVerbindingen = sc.nextInt();

            Graph graph = new Graph(aantalKnooppunten, aantalVerbindingen);
            for(int i = 0; i<aantalVerbindingen; i++) {
                graph.getEdge(i).setSrc(sc.nextInt());
                graph.getEdge(i).setDest(sc.nextInt());
                graph.getEdge(i).setWeight(sc.nextInt());
            }
            int oplossing = BellmanFord(graph, 0);

            System.out.print(a);
            if(oplossing == Integer.MAX_VALUE) System.out.println(" plus oneindig");
            else if(oplossing == Integer.MIN_VALUE) System.out.println(" min oneindig");
            else System.out.println(" " + oplossing);
        }
}
}
