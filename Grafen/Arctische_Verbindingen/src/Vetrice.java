import java.util.ArrayList;
import java.util.HashMap;

public class Vetrice {
    //int id;
    int x;
    int y;
    HashMap<Vetrice, Integer> edges;

    public Vetrice(int id, int x, int y, ArrayList<Vetrice> graafLijst) {
        //this.id = id;
        this.x = x;
        this.y = y;
        edges = new HashMap<>();
        for(Vetrice v : graafLijst) {
            v.voegEdgeToe(this);
            this.voegEdgeToe(v);
        }
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    private void voegEdgeToe(Vetrice v) {
        int afstand = this.berekenAfstand(v);
        edges.put(v, afstand);
    }

    public int berekenAfstand(Vetrice v) {
        double x2 = (double)v.getX();
        double y2 = (double)v.getY();
        double x1 = (double) x;
        double y1 = (double) y;
        return (int) Math.ceil( Math.sqrt( Math.pow((x1-x2),2) + Math.pow((y1-y2),2) ) );
    }
    public void removeEdges() {
        edges.clear();
    }

    public Vetrice vindKortste(ArrayList<Vetrice> vetrices) {
        Vetrice tmp;
        tmp = vetrices.get(0);
        for(Vetrice v : vetrices) {
            if(v.berekenAfstand(this) < tmp.berekenAfstand(this)) tmp = v;
        }
        return tmp;
    }

    public void verbindMet(Vetrice kortste) {
        edges.put(kortste, kortste.berekenAfstand(this));
        kortste.addEdge(this);
    }
    private void addEdge(Vetrice vetrice) {
        edges.put(vetrice, vetrice.berekenAfstand(this));
    }
}
