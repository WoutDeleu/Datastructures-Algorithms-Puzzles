import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/*
import java.util.*;

public class Dijkstra {

    private final ArrayList<Integer> nodes;
    private final ArrayList<Edge> edges;
    private HashSet<Integer> settledNodes;
    private HashSet<Integer> unsettledNodes;
    private HashMap<Integer, Integer> predecessors;
    private HashMap<Integer,Integer> distance;

    public Dijkstra(Graaf graaf){
        this.nodes = new ArrayList<Integer>(graaf.getVerdiepen());
        this.edges = new ArrayList<Edge>(graaf.getVerbindingen());
    }

    public void execute(int bronVerdiep){
        settledNodes = new HashSet<Integer>();
        unsettledNodes = new HashSet<Integer>();
        distance = new HashMap<Integer, Integer>();
        predecessors = new HashMap<Integer, Integer>();

        distance.put(bronVerdiep,0);
        unsettledNodes.add(bronVerdiep);

        while(unsettledNodes.size() >0){
            int node = getMinimum(unsettledNodes);
            settledNodes.add(node);
            unsettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(int node) {
        ArrayList<Integer> adjacentNodes = getNeighbors(node);
        for (int target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unsettledNodes.add(target);
            }
        }

    }

    private int getDistance(int node, int target) {
        for (Edge edge : edges) {
            if (edge.getStartVerdieping()== (node)
                    && edge.getEindVerdieping() == target) {
                return 1;
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private ArrayList<Integer> getNeighbors(int node) {
        ArrayList<Integer> neighbors = new ArrayList<Integer>();
        for (Edge edge : edges) {
            if (edge.getStartVerdieping() == node
                    && !isSettled(edge.getEindVerdieping())) {
                neighbors.add(edge.getEindVerdieping());
            }
        }
        return neighbors;
    }

    private boolean isSettled(int vertex) {
        return settledNodes.contains(vertex);
    }

    private int getMinimum(HashSet<Integer> vertexes) {
        int minimum = 0;
        for (int vertex : vertexes) {
            if (minimum == 0) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private int getShortestDistance(int destination) {
        int d = distance.get(destination);
        if (d == 0) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }


    */
/*
 * This method returns the path from the source to the selected target and
 * NULL if no path exists
 *//*

    public LinkedList<Integer> getPath(int target) {
        LinkedList<Integer> path = new LinkedList<Integer>();
        int step = target;
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

    public ArrayList<Edge> getPijlenDiePassen(int van, int naar){

        ArrayList<Edge> returner = new ArrayList<Edge>();

        for(Edge p: edges){
            if(p.getStartVerdieping() == van && p.getEindVerdieping() == naar){
                returner.add(p);
            }
        }

        return returner;
    }


    public ArrayList<Edge> getPijlen(LinkedList<Integer> pad) {
        //controleert voor elke stap welke radliften hieraan kunnen voldoen
        int huidigVerdiep=0;
        int volgendVerdiep=0;

        //dit is de lijst die we zullen returnen
        //hier in mag der dus maar 1 keer iets toegevoegd worden per set
        ArrayList<Edge> gevolgdePijlen = new ArrayList<Edge>();

        for(int i=0; i<pad.size()-1; i++){
            huidigVerdiep = pad.get(i);
            volgendVerdiep = pad.get(i+1);


            ArrayList<Edge> pijlenVoor1stap = this.getPijlenDiePassen(huidigVerdiep, volgendVerdiep);

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
                    vorigLiftNummer = gevolgdePijlen.get(vorigLiftNummer-1).getId();

                    //overloop de gebruikte pijlen, check als deze aanwezig is
                    int zelfdeLiftId = bevatZelfdeLiftNummer(vorigLiftNummer, pijlenVoor1stap);

                    //als het dus niet hetzelfde liftnummer bevat
                    if(zelfdeLiftId == -1){
                        //dan moeten we gewoon het kleinste liftnummer returnen
                        Edge pijlptr = getPijlMetLaagsteLiftNummer(pijlenVoor1stap);
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

                    Edge pijlptr = getPijlMetLaagsteLiftNummer(pijlenVoor1stap);
                    gevolgdePijlen.add(pijlptr);

                }


            }



        }

        return gevolgdePijlen;


    }

    private Edge getPijlMetLaagsteLiftNummer(ArrayList<Edge> pijlenVoor1stap) {
        Edge pijlptr = null;

        int laagstePijlNummer = Integer.MAX_VALUE;
        for(Edge p : pijlenVoor1stap){
            if(p.getId() < laagstePijlNummer){
                laagstePijlNummer = p.getId();
                pijlptr = p;
            }
        }
        return pijlptr;

    }

    private int bevatZelfdeLiftNummer(int vorigLiftNummer, ArrayList<Edge> gevolgdePijlen) {
        //returnt -1 als hij der niet in zit

        for(int i=0; i<gevolgdePijlen.size(); i++){
            if(gevolgdePijlen.get(i).getId()==vorigLiftNummer){return i;}
        }
        return (-1);
    }
}
*/

class Graaf {
    private int aantalVerdiepen;
    private ArrayList<Integer> verdiepen;
    private ArrayList<Edge> verbindingen;

    public Graaf(int v) {
        aantalVerdiepen = v;
        verdiepen = new ArrayList<>(v);
        for(int i=0;i<aantalVerdiepen;i++) {
            verdiepen.add(i);
        }
        verbindingen = new ArrayList<>();
    }

    public void addRadlift(int id,int startVerdieping, int eindVerdieping, int stapgrootte) {
        int i = startVerdieping;
        int j = eindVerdieping;
        while (i<eindVerdieping) {
            verbindingen.add(new Edge(id,i, i+stapgrootte,true));
            verbindingen.add(new Edge(id,j, j-stapgrootte,false));

            i+=stapgrootte;
            j-=stapgrootte;
        }
    }

    public ArrayList<Integer> getVerdiepen() {
        return verdiepen;
    }
    public ArrayList<Edge> getVerbindingen() {
        return verbindingen;
    }
}

class Edge {
    private int startVerdieping, eindVerdieping, kokerNr;
    private int weight = 1;
    public Edge() {
        startVerdieping = eindVerdieping = 0;
    }


    public Edge(int id, int startVerdieping, int eindVerdieping, boolean opwaarts) {
        this.startVerdieping = startVerdieping;
        this.eindVerdieping = eindVerdieping;
        if(opwaarts) kokerNr = 2*id -1;
        else kokerNr = 2*id;
    }

    public int getId() {
        return kokerNr;
    }

    public int getWeight() {
        return weight;
    }

    public int getStartVerdieping() {
        return startVerdieping;
    }

    public int getEindVerdieping() {
        return eindVerdieping;
    }
}









//http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html

public class Main  {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int aantalGevallen = sc.nextInt();
        for(int a = 1; a<=aantalGevallen; a++) {
            int  aantalVerdiepingen = sc.nextInt();
            int aantalRadliften = sc.nextInt();

            Graaf graaf = new Graaf(aantalVerdiepingen);
            for(int i=1; i<=aantalRadliften; i++) {
                graaf.addRadlift(i,sc.nextInt(), sc.nextInt(), sc.nextInt());
            }
            int startplek = sc.nextInt();
            int bestemming = sc.nextInt();

        }
    }

/*    private static String genereerAntwoord(ArrayList<Edge> gebruiktePijlen) {
        StringBuilder antwoord = new StringBuilder();

        int huidigLiftNummer=0;
        int huidigeEindBestemming=0;
        int volgendLiftNummer=0;
        int arrayListSize = gebruiktePijlen.size();
        //beginsituatie
        for(int i=0; i<arrayListSize-1; i++) {
            huidigLiftNummer = gebruiktePijlen.get(i).getId();
            volgendLiftNummer = gebruiktePijlen.get(i + 1).getId();

            if (huidigLiftNummer != volgendLiftNummer) {
                huidigeEindBestemming = gebruiktePijlen.get(i).getEindVerdieping();
                antwoord.append("(" + huidigLiftNummer + "," + huidigeEindBestemming + ")");
            }


        }

        huidigLiftNummer = gebruiktePijlen.get(arrayListSize-1).getId();
        huidigeEindBestemming = gebruiktePijlen.get(arrayListSize-1).getEindVerdieping();
        antwoord.append("(" + huidigLiftNummer + "," + huidigeEindBestemming + ")");

        return antwoord.toString();
    }*/
}
