import java.util.Scanner;

public class Main {
    private static boolean controleerFoodtrucksBudget(int budget,  Foodtrucks foodtrucks, int[] gerechtenIndex, int foodtruckIndex) {
        boolean geldig = false;
        Foodtruck huidigeFoodtruck = foodtrucks.get(foodtruckIndex);
        int huidigGerecht =  huidigeFoodtruck.getPrijzen()[gerechtenIndex[foodtruckIndex]];
        int lengte = foodtrucks.length();
        if(budget>0) {
            if(budget>=huidigGerecht) {
                budget = budget - huidigGerecht;
                if(foodtruckIndex==lengte-1) {
                    gerechtenIndex[foodtruckIndex] = 0;
                    if(foodtruckIndex == 0) geldig = false;
                    else foodtruckIndex--;
                    if(gerechtenIndex[foodtruckIndex] == foodtrucks.get(foodtruckIndex).getPrijzen().length-1) {
                        controleerFoodtrucksBudget(budget, foodtrucks, gerechtenIndex, foodtruckIndex);
                    }
                    else {
                        gerechtenIndex[foodtruckIndex]++;
                        controleerFoodtrucksBudget(budget, foodtrucks, gerechtenIndex, foodtruckIndex);
                    }
                }
                else {
                    foodtruckIndex++;
                }
                controleerFoodtrucksBudget(budget, foodtrucks, gerechtenIndex, foodtruckIndex);
            }
            else if(budget < huidigGerecht) {
                if(foodtruckIndex == 0) geldig = false;
                else {
                    gerechtenIndex[foodtruckIndex] = 0;
                    if(foodtruckIndex == 0) geldig = false;
                    else foodtruckIndex--;
                    if(gerechtenIndex[foodtruckIndex] == foodtrucks.get(foodtruckIndex).getPrijzen().length-1) {
                        controleerFoodtrucksBudget(budget, foodtrucks, gerechtenIndex, foodtruckIndex);
                    }
                    else {
                        gerechtenIndex[foodtruckIndex]++;
                        controleerFoodtrucksBudget(budget, foodtrucks, gerechtenIndex, foodtruckIndex);
                    }
                }
            }
        }
        if(budget == 0) {
            if(foodtruckIndex == lengte) geldig = true;
            else if(foodtruckIndex < lengte) {
                if(foodtruckIndex == 0) geldig = false;
                gerechtenIndex[foodtruckIndex] = 0;
                if(foodtruckIndex == 0) geldig = false;
                else foodtruckIndex--;
                if(gerechtenIndex[foodtruckIndex] == foodtrucks.get(foodtruckIndex).getPrijzen().length-1) {
                    controleerFoodtrucksBudget(budget, foodtrucks, gerechtenIndex, foodtruckIndex);
                }
                else {
                    gerechtenIndex[foodtruckIndex]++;
                    controleerFoodtrucksBudget(budget, foodtrucks, gerechtenIndex, foodtruckIndex);
                }
            }
        }
        return geldig;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aantalTestGevallen = sc.nextInt();

        int b, f;
        for (int a=1;a<=aantalTestGevallen;a++) {

            //inlezen budgetten
            b = sc.nextInt();
            Budgetten budgetten = new Budgetten(b);
            for(int i = 0; i<b; i++) {
                budgetten.add(new Budget(sc.nextInt()));
            }

            //Inlezen foodtrucks
            f = sc.nextInt();
            Foodtrucks foodtrucks = new Foodtrucks(f);
            for(int i = 0; i<f; i++) {
                int grootte = sc.nextInt();
                int [] temp = new int[grootte];
                for(int j = 0; j<grootte; j++) {
                    temp[j] = sc.nextInt();
                }
                foodtrucks.add(new Foodtruck(temp));
            }

            //Oplossen
            boolean geldig;
            for(int i = 0; i <b; i++) {
                int [] gerechtenIndex = new int[f];
                int foodtruckIndex = 0;
                geldig =  controleerFoodtrucksBudget(budgetten.get(i), foodtrucks, gerechtenIndex, foodtruckIndex);
                budgetten.setGeldig(i, geldig);
            }



            //uitprinten oplossing
            budgetten.schrijfOplossing(a);
        }
        sc.close();
    }
}
