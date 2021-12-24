public class Foodtruck {
    private Gerechten prijslijst;
    private int index;

    public Foodtruck(Gerechten gerechtenFoodtruck) {
        prijslijst = gerechtenFoodtruck;
        index = 0;
    }
    public void verhoogIndex() {
        index++;
    }
    public void verlaagIndex() {
        index--;
    }
    public int getIndex(){
        return index;
    }
    public Gerechten getPrijslijst() {
        return prijslijst;
    }
    public int getSize() {
        return prijslijst.getSize();
    }
    public void setStart() {
        index = 0;
        prijslijst.setStart();
    }
    public void checkGeldigheidBudget(int budgetWaarde) {
        prijslijst.checkGeldigheidBudget(budgetWaarde);
    }
    public int getHuidigePrijs() {
        return prijslijst.getGerecht(index).getPrijs();
    }
    public Gerecht getHuidigGerecht() {
        return prijslijst.getGerecht(index);
    }
    public void setIndex(int i) {
        index = i;
    }
}
