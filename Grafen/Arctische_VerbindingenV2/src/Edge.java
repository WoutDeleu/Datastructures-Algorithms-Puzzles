public class Edge {
    Vetrice v;
    int afstand;

    public Vetrice getV() {
        return v;
    }

    public int getAfstand() {
        return afstand;
    }

    public Edge(Vetrice v, int afstand) {
        this.v = v;
        this.afstand = afstand;
    }
}
