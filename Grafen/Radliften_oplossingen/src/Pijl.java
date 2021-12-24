public class Pijl {

    //attributen
    private int radliftId;   //dit moet in de output!
    private Verdiep van;
    private Verdiep naar;   //dit moet in de output!
    private int gewicht;

    //constructor
    public Pijl(int radliftId, Verdiep van, Verdiep naar){
        this.radliftId = radliftId;
        this.van = van;
        this.naar= naar;
        gewicht = 1;
    }

    public Pijl(Pijl p){
        radliftId= p.radliftId;
        van = p.van;
        naar = p.naar;
        gewicht =1;
    }

    //getters, setters

    public int getRadliftId() {
        return radliftId;
    }

    public Verdiep getVan() {
        return van;
    }

    public Verdiep getNaar() {
        return naar;
    }


    public void setRadLiftId(int getal) {
        radliftId = getal;
    }
}
