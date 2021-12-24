import java.util.ArrayList;

public class Foodtrucks {
    private ArrayList<Foodtruck> foodtrucks;
    int huidigeFoodtruck;

    public Foodtrucks(int aantalFoodtrucks) {
        foodtrucks = new ArrayList<>(aantalFoodtrucks);
        huidigeFoodtruck = 0;
    }
    public void append(Foodtruck foodtruck) {
        foodtrucks.add(foodtruck);
    }
    public Foodtruck getHuidigeFoodtruck() {
        return foodtrucks.get(huidigeFoodtruck);
    }
    public void setStart() {
        for (Foodtruck f: foodtrucks) {
            f.setStart();
        }
        huidigeFoodtruck = 0;
    }
    public void verhoogHuidigeFoodtruck() {
        huidigeFoodtruck++;
    }
    public void checkGeldigheidBudget(int budgetWaarde) {
        for(Foodtruck f : foodtrucks) {
            f.checkGeldigheidBudget(budgetWaarde);
        }
    }
    public boolean indexIsTenEinde() {
        if(huidigeFoodtruck == foodtrucks.size()-1) return true;
        else return false;
    }
    public int getHuidigeFoodtruckIndex() {
        return huidigeFoodtruck;
    }
    public void verlaagHuidigeFoodtruck() {
        huidigeFoodtruck--;
    }
}
