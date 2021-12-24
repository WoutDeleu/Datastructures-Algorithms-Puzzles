import java.util.ArrayList;

class Foodtrucks {
    private ArrayList<Foodtruck> foodtrucks;
    private int foodtruckCounter;

    public Foodtrucks(int aantalFoodtrucks) {
        foodtrucks = new ArrayList<>(aantalFoodtrucks);
        foodtruckCounter = 0;
    }
    public void voegFoodtruckToe(Foodtruck foodtruck) {
        foodtrucks.add(foodtruck);
    }
    private boolean isTenEinde() {
        return foodtruckCounter == foodtrucks.size()-1;
    }
    public int stepBack(int budget) {
        foodtrucks.get(foodtruckCounter).setGerechtenTeller(0);
        foodtruckCounter--;
        budget = budget + foodtrucks.get(foodtruckCounter).getHuidigGerecht();
        //Hier problemen?
        if(!foodtrucks.get(foodtruckCounter).isTenEinde()) {
            foodtrucks.get(foodtruckCounter).verhoogGerechtenTeller();
        }
        return budget;
    }
    public boolean controleer(int budget) {
        boolean oplossing = false;
        for(int gerecht : foodtrucks.get(foodtruckCounter).getGerechten()) {
            if(this.isTenEinde()) {
                if (budget == gerecht) {
                    oplossing = true;
                    break;
                }
                else {
                    if(foodtrucks.get(foodtruckCounter).isTenEinde()) {
                        oplossing =  false;
                        break;
                    }
                    else {
                        foodtrucks.get(foodtruckCounter).verhoogGerechtenTeller();
                        oplossing = this.controleer(budget);
                    }
                }
            }
            else {
                if (budget>gerecht) {
                    budget = budget - gerecht;
                    foodtruckCounter++;
                    oplossing = this.controleer(budget);
                }
                else { //(budget <= gerecht)
                    if(!foodtrucks.get(foodtruckCounter).isTenEinde()) {
                        foodtrucks.get(foodtruckCounter).verhoogGerechtenTeller();
                        oplossing = this.controleer(budget);
                    }
                    else {
                        foodtrucks.get(foodtruckCounter).setGerechtenTeller(0);
                        foodtruckCounter--;
                        budget = budget + foodtrucks.get(foodtruckCounter).getHuidigGerecht();
                        foodtrucks.get(foodtruckCounter).verhoogGerechtenTeller();
                        oplossing = this.controleer(budget);
                    }
                }
            }
        }
        return oplossing;
    }
}