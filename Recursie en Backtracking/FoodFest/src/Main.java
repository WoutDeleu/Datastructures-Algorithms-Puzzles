import java.util.Scanner;

public class Main {
    private static boolean controleerFoodtrucksBudget(int budget, Foodtruck[] foodtrucks, int[] gerechtenIndex, int foodtruckIndex) {
        boolean geldig = false;
        if(budget>0) {
            if(budget>=foodtrucks[foodtruckIndex].getPrijzen()[gerechtenIndex[foodtruckIndex]]) {
                budget = budget - foodtrucks[foodtruckIndex].getPrijzen()[gerechtenIndex[foodtruckIndex]];
                if(foodtruckIndex==foodtrucks.length-1) {
                    gerechtenIndex[foodtruckIndex] = 0;
                    if(foodtruckIndex == 0) geldig = false;
                    else foodtruckIndex--;
                    if(gerechtenIndex[foodtruckIndex] == foodtrucks[foodtruckIndex].getPrijzen().length-1) {
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
            else if(budget < foodtrucks[foodtruckIndex].getPrijzen()[gerechtenIndex[foodtruckIndex]]) {
                if(foodtruckIndex == 0) geldig = false;
                else {
                    gerechtenIndex[foodtruckIndex] = 0;
                    if(foodtruckIndex == 0) geldig = false;
                    else foodtruckIndex--;
                    if(gerechtenIndex[foodtruckIndex] == foodtrucks[foodtruckIndex].getPrijzen().length-1) {
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
            if(foodtruckIndex == foodtrucks.length) geldig = true;
            else if(foodtruckIndex < foodtrucks.length) {
                if(foodtruckIndex == 0) geldig = false;
                gerechtenIndex[foodtruckIndex] = 0;
                if(foodtruckIndex == 0) geldig = false;
                else foodtruckIndex--;
                if(gerechtenIndex[foodtruckIndex] == foodtrucks[foodtruckIndex].getPrijzen().length-1) {
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

    public static void  main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aantalTestGevallen = sc.nextInt();

        int b, f;
        for (int a=1;a<=aantalTestGevallen;a++){

            //inlezen budgetten
            b = sc.nextInt();
            Budget [] budgetten = new Budget[b];
            for(int i = 0; i<b; i++) {
                budgetten[i] = new Budget(sc.nextInt());
            }

            //Inlezen foodtrucks
            f = sc.nextInt();
            Foodtruck [] foodtrucks = new Foodtruck[f];
            for(int i = 0; i<f; i++) {
                int grootte = sc.nextInt();
                int [] temp = new int[grootte];
                for(int j = 0; j<grootte; j++) {
                    temp[j] = sc.nextInt();
                }
                foodtrucks[i] = new Foodtruck(temp);
            }


            //Oplossen
            boolean geldig;
            for(int i = 0; i <b; i++) {
                int [] gerechtenIndex = new int[foodtrucks.length];
                int foodtruckIndex = 0;
                geldig =  controleerFoodtrucksBudget(budgetten[i].getBudget(), foodtrucks, gerechtenIndex, foodtruckIndex);
                budgetten[i].setGeldig(geldig);
            }

            //uitprinten oplossing
            int teller = 0;
            for(int i = 0; i<b; i++) {
                if(budgetten[i].isGeldig()) teller++;
            }
            if(teller == 0) System.out.print(a + " " + "GEEN");
            else {
                for(int i = 0; i<b; i++) {
                    System.out.print(a);
                    if(budgetten[i].isGeldig()) budgetten[i].schrijf();
                }
            }


        }
        sc.close();
    }
}
