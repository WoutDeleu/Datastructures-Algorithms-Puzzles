import java.util.*;

public class DijkstraAlgoritme {

    private final ArrayList<Verdiep> nodes;
    private final ArrayList<Pijl> edges;
    private HashSet<Verdiep> settledNodes;
    private HashSet<Verdiep> unsettledNodes;
    private HashMap<Verdiep, Verdiep> predecessors;
    private HashMap<Verdiep,Integer> distance;

    public DijkstraAlgoritme(Graaf graaf){
        this.nodes = new ArrayList<Verdiep>(graaf.getVerdiepen());
        this.edges = new ArrayList<Pijl>(graaf.getPijlen());
    }

    public void execute(Verdiep bronVerdiep){
        settledNodes = new HashSet<Verdiep>();
        unsettledNodes = new HashSet<Verdiep>();
        distance = new HashMap<Verdiep, Integer>();
        predecessors = new HashMap<Verdiep, Verdiep>();

        distance.put(bronVerdiep,0);
        unsettledNodes.add(bronVerdiep);

        while(unsettledNodes.size() >0){
            Verdiep node = getMinimum(unsettledNodes);
            settledNodes.add(node);
            unsettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Verdiep node) {
        ArrayList<Verdiep> adjacentNodes = getNeighbors(node);
        for (Verdiep target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unsettledNodes.add(target);
            }
        }

    }

    private int getDistance(Verdiep node, Verdiep target) {
        for (Pijl edge : edges) {
            if (edge.getVan().equals(node)
                    && edge.getNaar().equals(target)) {
                return 1; //gewicht is altijd 1
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private ArrayList<Verdiep> getNeighbors(Verdiep node) {
        ArrayList<Verdiep> neighbors = new ArrayList<Verdiep>();
        for (Pijl edge : edges) {
            if (edge.getVan().equals(node)
                    && !isSettled(edge.getNaar())) {
                neighbors.add(edge.getNaar());
            }
        }
        return neighbors;
    }

    private boolean isSettled(Verdiep vertex) {
        return settledNodes.contains(vertex);
    }

    private Verdiep getMinimum(HashSet<Verdiep> vertexes) {
        Verdiep minimum = null;
        for (Verdiep vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private int getShortestDistance(Verdiep destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }


    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<Verdiep> getPath(Verdiep target) {
        LinkedList<Verdiep> path = new LinkedList<Verdiep>();
        Verdiep step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order

        Collections.reverse(path);
        return path;
    }

    public ArrayList<Pijl> getPijlenDiePassen(Verdiep van, Verdiep naar){

        ArrayList<Pijl> returner = new ArrayList<Pijl>();

        for(Pijl p: edges){
            if(p.getVan() == van && p.getNaar() == naar){
                returner.add(p);
            }
        }

        return returner;
    }


    public ArrayList<Pijl> getPijlen(LinkedList<Verdiep> pad) {
        //controleert voor elke stap welke radliften hieraan kunnen voldoen
        Verdiep huidigVerdiep=null;
        Verdiep volgendVerdiep=null;

        //dit is de lijst die we zullen returnen
        //hier in mag der dus maar 1 keer iets toegevoegd worden per set
        ArrayList<Pijl> gevolgdePijlen = new ArrayList<Pijl>();

        for(int i=0; i<pad.size()-1; i++){
            huidigVerdiep = pad.get(i);
            volgendVerdiep = pad.get(i+1);


            ArrayList<Pijl> pijlenVoor1stap = this.getPijlenDiePassen(huidigVerdiep, volgendVerdiep);

            //als der maar 1 gevonden is voor die van en naar
            if(pijlenVoor1stap.size()==1){
                //dan hebben we hem

                //voeg die toe aan de gebruikte pijlen
                gevolgdePijlen.add(pijlenVoor1stap.get(0));
                
            }

            else{
                //dus als der meer of 2 zijn

                //kijk naar de vorig gebruikte lift
                //dit kan enkel als de gevolgdePijlen arrayList niet leeg is
                if(gevolgdePijlen.size() != 0) {

                    //neem hetzelfde liftnummer als de lift die hiervoor werd gebruikt
                        //hiervoor moeten we checken als dit liftnummer er in zit
                        int vorigLiftNummer = gevolgdePijlen.size();
                        vorigLiftNummer = gevolgdePijlen.get(vorigLiftNummer-1).getRadliftId(); //mad hackz
                    
                        //overloop de gebruikte pijlen, check als deze aanwezig is
                        int zelfdeLiftId = bevatZelfdeLiftNummer(vorigLiftNummer, pijlenVoor1stap);

                        //als het dus niet hetzelfde liftnummer bevat
                        if(zelfdeLiftId == -1){
                            //dan moeten we gewoon het kleinste liftnummer returnen
                            Pijl pijlptr = getPijlMetLaagsteLiftNummer(pijlenVoor1stap);
                            gevolgdePijlen.add(pijlptr);
                        }
                        else{
                            //als het dus wel hetzelfde lift nummer bevat

                            //in zelfdeliftId zit de plek van dit liftnummer
                            //das door die methode daar 'bevatzelfdeliftnummer'
                            gevolgdePijlen.add(pijlenVoor1stap.get(zelfdeLiftId));
                        }

                    
                    
                    
                    
                    //als dit niet mogelijk is, neem dan de lift met het laagste liftnummer


                }

                else{
                    //dus als de arraylist met gebruikte pijlen tot nu toe leeg is (1e stap)
                    //neem dan gewoon het liftnummer met het laagste id

                    Pijl pijlptr = getPijlMetLaagsteLiftNummer(pijlenVoor1stap);
                    gevolgdePijlen.add(pijlptr);

                }


            }



        }


        //nu nog de radlift id's van de pijlen aanpassen
        // 1 = 1 of 2
        //3 = 5 of 6
        //als omhoog: huidignummer *2 -1
        //als omlaag: huidigNummer*2
        for(Pijl p: gevolgdePijlen){
            if(p.getVan().getVerdiepNummer() < p.getNaar().getVerdiepNummer()){
                //dan gaan we omhoog
                p.setRadLiftId(     (p.getRadliftId()*2) -1);
            }
            else{
                //dan gaan we omlaag
                p.setRadLiftId( p.getRadliftId()*2);
            }
        }


        return gevolgdePijlen;


    }

    private Pijl getPijlMetLaagsteLiftNummer(ArrayList<Pijl> pijlenVoor1stap) {
        Pijl pijlptr = null;

        int laagstePijlNummer = Integer.MAX_VALUE;
        for(Pijl p : pijlenVoor1stap){
            if(p.getRadliftId() < laagstePijlNummer){
                laagstePijlNummer = p.getRadliftId();
                pijlptr = p;
            }
        }
        return pijlptr;

    }

    private int bevatZelfdeLiftNummer(int vorigLiftNummer, ArrayList<Pijl> gevolgdePijlen) {
        //returnt -1 als hij der niet in zit

        for(int i=0; i<gevolgdePijlen.size(); i++){
            if(gevolgdePijlen.get(i).getRadliftId()==vorigLiftNummer){return i;}
        }
        return (-1);
    }
}
