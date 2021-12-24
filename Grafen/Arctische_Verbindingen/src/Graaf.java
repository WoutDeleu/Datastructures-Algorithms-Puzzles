import java.util.ArrayList;

public class Graaf {
    ArrayList<Vetrice> vetrices;

    public Graaf(ArrayList<Vetrice> vetrices) {
        this.vetrices = vetrices;
    }

    public Graaf berekenMST() {
        ArrayList<Vetrice> tmp = vetrices;
        Vetrice v1 = tmp.get(0);
        ArrayList<Vetrice> mst = new ArrayList<>();
        mst.add(v1);
        for(Vetrice v : tmp) {
            v.removeEdges();
            Vetrice kortste = v.vindKortste(mst);
            v.verbindMet(kortste);
        }
        return new Graaf(mst);
    }
}
