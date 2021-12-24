import java.util.ArrayList;

public class FakePijl {
    private int van;
    private int naar;

    public FakePijl(int v, int n){
        van = v;
        naar= n;
    }

    public static ArrayList<FakePijl> genereerPijlen(int onderVerdiep, int bovenVerdiep, int stap) {

        ArrayList<FakePijl> fp = new ArrayList<FakePijl>();

        boolean bovenBereikt = false;

        int eersteTussenVerdiep= onderVerdiep;
        int tweedeTussenVerdiep=0;
        //zolang boven nog niet bereikt is
        while(!bovenBereikt) {

            tweedeTussenVerdiep = eersteTussenVerdiep + stap;

            fp.add(new FakePijl(eersteTussenVerdiep,tweedeTussenVerdiep));

            if (tweedeTussenVerdiep == bovenVerdiep) {
                bovenBereikt = true;
            }
            eersteTussenVerdiep= tweedeTussenVerdiep;

        }

        boolean onderBereikt = false;
        tweedeTussenVerdiep = bovenVerdiep;

        while(!onderBereikt){
            eersteTussenVerdiep = tweedeTussenVerdiep-stap;

            fp.add(new FakePijl(tweedeTussenVerdiep,eersteTussenVerdiep));

            if(eersteTussenVerdiep == onderVerdiep){
                onderBereikt = true;
            }

            tweedeTussenVerdiep = eersteTussenVerdiep;

        }

        return fp;
    }




    public int getVan() {
        return van;
    }

    public int getNaar() {
        return naar;
    }
}
