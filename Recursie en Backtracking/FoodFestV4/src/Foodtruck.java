public class Foodtruck {
    private int[] gerechten;
    private int gerechtenteller;


    public Foodtruck(int aantal) {
        gerechten = new int[aantal];
    }

    public int[] getGerechten() {
        return gerechten;
    }
    public void voegGerechtToe(int index, int gerecht) {
        gerechten[index] = gerecht;
    }
    public int lengte() {
        return gerechten.length;
    }

    public void setGerechtenTeller(int i) {
        gerechtenteller = i;
    }
    public int getGerechtenteller() {
        return gerechtenteller;
    }
    public void verhoogGerechtenTeller() {
        gerechtenteller++;
    }

    public int getHuidigGerecht() {
        return gerechten[gerechtenteller];
    }

    public boolean isTenEinde() {
        return gerechtenteller == gerechten.length-1;
    }
}