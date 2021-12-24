import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Graaf {
    private HashMap<Vetrice, HashSet<Edge>> graaf;

    public Graaf() {
        this.graaf = new HashMap<Vetrice, HashSet<Edge>>();
    }

    public void add(Vetrice v) {
        HashSet<Edge> edges = new HashSet<>();
        for(Vetrice vetriceInList : graaf.keySet()) {
            int afstand = v.berekenAfstand(vetriceInList);
            edges.add(new Edge(vetriceInList, afstand));
            graaf.get(vetriceInList).add(new Edge(vetriceInList, afstand));
        }
        graaf.put(v,edges);
    }

    public Graaf berekenMST() {
        Graaf mst = new Graaf();

        for(Vetrice vetriceInList : graaf.keySet()) {
            if(mst.graaf.isEmpty()) mst.addFirstMST(vetriceInList);
            else {
                mst.addMMST(vetriceInList);
            }
        }

        return mst;
    }

    private void addMMST(Vetrice vetriceInList) {
        Vetrice teLinkenVetrice = this.vindKortse(vetriceInList);
        HashSet<Edge> edge = new HashSet<>(1);
        edge.add(new Edge(teLinkenVetrice, vetriceInList.berekenAfstand(teLinkenVetrice)));
        graaf.put(vetriceInList, edge);
        graaf.get(teLinkenVetrice).add(new Edge(vetriceInList, vetriceInList.berekenAfstand(teLinkenVetrice)));
    }

    private Vetrice vindKortse(Vetrice vetriceInList) {
        Vetrice kortsteVetrice = null;
        int huidigeBesteAfstand = 0;

        for(Vetrice v : graaf.keySet()) {
            int afstand = vetriceInList.berekenAfstand(v);
            if(afstand<huidigeBesteAfstand || kortsteVetrice == null) {
                huidigeBesteAfstand = afstand;
                kortsteVetrice = v;
            }
        }
        return kortsteVetrice;
    }

    private void addFirstMST(Vetrice vetriceInList) {
        graaf.put(vetriceInList, new HashSet<Edge>());
    }

    public int[][] toMatrix(int grootte) {
        int [][] sol = new int [grootte][grootte];

        for(Vetrice v1 : graaf.keySet()) {
            HashSet<Edge> e_list = graaf.get(v1);
            for (Edge e : e_list) {
                Vetrice v2 = e.getV();
                sol[v1.getId()][v2.getId()] = e.getAfstand();
            }
        }
        return sol;
    }
}
