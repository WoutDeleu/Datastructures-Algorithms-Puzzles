
import java.util.HashMap;

//een verdiep is een vertex
public class Verdiep{

    //attributen
    int verdiepNummer;

    //constructor
    public Verdiep(int v){
        verdiepNummer = v;
    }

    //getters, setters
    public int getVerdiepNummer() {
        return verdiepNummer;
    }

    public void setVerdiepNummer(int verdiepNummer) {
        this.verdiepNummer = verdiepNummer;
    }


    //andere methodes

    //misschien een equals methode
    public boolean equals(Verdiep other) {
        if(verdiepNummer == other.verdiepNummer){return true;}
        else{return false;}
    }







}
