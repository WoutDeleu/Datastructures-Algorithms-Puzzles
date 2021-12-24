import java.util.Scanner;

public class Main {
    private static boolean controleerBudget(int budget, Foodtrucks foodtrucks) {
        boolean erIsOplossing = false;
        while(!erIsOplossing) {
            Foodtruck huidigeFoodtruck = foodtrucks.getHuidigeFoodtruck();
            Gerechten huidigeGerechten = huidigeFoodtruck.getPrijslijst();
            int huidigGerechtIndex = huidigeFoodtruck.getIndex();
            int huidigePrijs = huidigeFoodtruck.getHuidigePrijs();
            Gerecht huidigGerecht = huidigeFoodtruck.getHuidigGerecht();

            if(huidigGerecht.isGeldig()) {
                if(huidigePrijs == huidigGerecht.getPrijs()) {
                    if(foodtrucks.indexIsTenEinde()) return true;
                    else {
                        recursie(budget, foodtrucks);
                        cont
                    }
                }
                else {
                    huidigePrijs = huidigePrijs-huidigGerecht.getPrijs();

                }
            }
            else {
                erIsOplossing = recursie(budget, foodtrucks);
                if(erIsOplossing) return false;
                else controleerBudget(budget, foodtrucks);
            }
        }
    }

    private static boolean recursie(int budget, Foodtrucks foodtrucks) {
        boolean oplossing;
        if(!foodtrucks.indexIsTenEinde()) {
            foodtrucks.getHuidigeFoodtruck().verhoogIndex();
            return false;
        }
        else {
            foodtrucks.getHuidigeFoodtruck().setIndex(0);
            if(foodtrucks.getHuidigeFoodtruckIndex() == 0) return true;
            else {
                foodtrucks.verlaagHuidigeFoodtruck();
            }
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Inlezen testgevallen
        int aantalTestgevallen = sc.nextInt();
        for(int a = 0; a<aantalTestgevallen; a++) {

            //Inlezen budgetten
            int aantalBudgetten = sc.nextInt();
            Budgetten budgetten = new Budgetten(aantalBudgetten);
            for(int i = 0; i<aantalBudgetten; i++) {
                budgetten.add(new Budget(sc.nextInt()));
            }

            //Inlezen Foodtrucks
            int aantalFoodtrucks = sc.nextInt();
            Foodtrucks foodtrucks = new Foodtrucks(aantalFoodtrucks);
            for(int i = 0; i<aantalFoodtrucks; i++) {
                int aantalGerechten = sc.nextInt();
                Gerechten gerechtenFoodtruck = new Gerechten(aantalGerechten);
                for(int j = 0; j <aantalGerechten; j++) {
                    gerechtenFoodtruck.append(new Gerecht(sc.nextInt()));
                }
                foodtrucks.append(new Foodtruck(gerechtenFoodtruck));
            }
            for(int i = 0; i<budgetten.getSize(); i++) {
                Budget huidigBudget = budgetten.getBudget(i);
                int budgetWaarde = huidigBudget.getWaarde();
                foodtrucks.setStart();
                foodtrucks.checkGeldigheidBudget(budgetWaarde);
                huidigBudget.setGeldigheid(controleerBudget(budgetWaarde, foodtrucks));
                budgetten.setBudget(i, huidigBudget);
            }


            //Geldig/Ongeldig!!!!!
                //Wanneer een prijs groter /gelijk is aan budget, ongeldig voor dat
            //Kijken tutorial sudoku solver
        }

        sc.close();
    }
}
