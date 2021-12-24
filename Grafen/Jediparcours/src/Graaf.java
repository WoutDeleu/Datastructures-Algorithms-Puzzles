import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Graaf {
    private HashMap<Integer, HashSet<Verbinding>> graaf;

    public Graaf() {
        graaf = new HashMap<>();
    }

    public void addKnoop (int beginKnoopNummer) {
        graaf.put(beginKnoopNummer, new HashSet<>());
    }
    public void addVerbinding(int beginKnoopNummer, Verbinding v) {
        graaf.get(beginKnoopNummer).add(v);
    }

    public void verwijderOnbereikbaar() {
        ArrayList<Integer> onbereikbaar = new ArrayList<>();
        for(int i = 1; i<graaf.size(); i++) {
            HashSet<Verbinding> verbindingen = graaf.get(i);
            boolean volgendeIsGevonden = false;
            for(Verbinding v : verbindingen) {
                if(v.getEindknoop() == i+1) volgendeIsGevonden = true;
            }
            if(!volgendeIsGevonden) {
                onbereikbaar.add(i+1);
                i=-2;
            }
        }

    }
}
