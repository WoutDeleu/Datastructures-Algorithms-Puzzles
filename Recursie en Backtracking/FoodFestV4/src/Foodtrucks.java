import java.util.ArrayList;

class Foodtrucks {
    private ArrayList<Foodtruck> foodtrucks;
    private int foodtruckteller;

    public Foodtrucks(int aantalFoodtrucks) {
        foodtrucks = new ArrayList<>(aantalFoodtrucks);
        foodtruckteller = 0;
    }
    public void voegFoodtruckToe(Foodtruck foodtruck) {
        foodtrucks.add(foodtruck);
    }
    private boolean isTenEinde() {
        return foodtruckteller == foodtrucks.size()-1;
    }
    public int stepBack(int budget) {
        foodtrucks.get(foodtruckteller).setGerechtenTeller(0);
        foodtruckteller--;
        budget = budget + foodtrucks.get(foodtruckteller).getHuidigGerecht();
        //Hier problemen?
        if(!foodtrucks.get(foodtruckteller).isTenEinde()) {
            foodtrucks.get(foodtruckteller).verhoogGerechtenTeller();
        }
        return budget;
    }
    public boolean controleer(int budget) {
        while(true) {
            if (foodtrucks.get(foodtruckteller).getHuidigGerecht() > budget) {
                if (foodtrucks.get(foodtruckteller).isTenEinde()) {
                    if (foodtruckteller == 0) return false;
                    else {
                        budget = this.stepBack(budget);
                    }
                } else {
                    foodtrucks.get(foodtruckteller).verhoogGerechtenTeller();
                }
            } else if (foodtrucks.get(foodtruckteller).getHuidigGerecht() == budget) {
                if (this.isTenEinde()) return true;
                else {
                    if (foodtrucks.get(foodtruckteller).isTenEinde()) {
                        if (foodtruckteller == 0) return false;
                        else {
                            budget = this.stepBack(budget);
                        }
                    } else {
                        foodtrucks.get(foodtruckteller).verhoogGerechtenTeller();
                    }
                }
            } else {
                if (this.isTenEinde()) {
                    if (foodtrucks.get(foodtruckteller).isTenEinde()) {
                        if (foodtruckteller == 0) return false;
                        else budget = this.stepBack(budget);
                    }
                    else {
                        foodtrucks.get(foodtruckteller).verhoogGerechtenTeller();
                    }
                }
                else {
                    budget = budget - foodtrucks.get(foodtruckteller).getHuidigGerecht();
                    foodtruckteller++;
                }
            }
            this.controleer(budget);
        }
    }
}