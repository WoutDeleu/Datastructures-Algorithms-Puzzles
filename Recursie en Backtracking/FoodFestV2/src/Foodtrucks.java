import java.util.ArrayList;

public class Foodtrucks {
    private ArrayList<Foodtruck> foodtrucks;

    public Foodtrucks(int f) {
        foodtrucks = new ArrayList<>(f);
    }
    public void add(Foodtruck f) {
        foodtrucks.add(f);
    }

    public Foodtruck get(int foodtruckIndex) {
        return foodtrucks.get(foodtruckIndex);
    }

    public int length() {
        return foodtrucks.size();
    }
}
