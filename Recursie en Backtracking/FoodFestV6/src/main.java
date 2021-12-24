import java.util.ArrayList;

import java.util.Scanner;

class Foodtrucks {
    private ArrayList<Foodtruck> foodtrucks;

    public Foodtrucks(int aantalFoodtrucks) {
        foodtrucks = new ArrayList<>(aantalFoodtrucks);
    }
    public void voegFoodtruckToe(Foodtruck foodtruck) {
        foodtrucks.add(foodtruck);
    }

    public void controleer(int budget, ArrayList<Integer> oplossing, int temp, int counter) {
        for(int gerecht : foodtrucks.get(counter).getGerechten()) {
            if (!oplossing.contains(budget)) {
                temp = temp - gerecht;

                //doorgaan naar de volgende
                if (temp > 0 && counter != foodtrucks.size() - 1) {
                    controleer(budget, oplossing, temp, counter+1);
                    //terug naar laatste goeie staat
                    temp = temp + gerecht;

                //voeg juiste antwoord toe
                } else if (temp == 0 && counter == foodtrucks.size() - 1) {
                    oplossing.add(budget);

                //terugkeren naar persoon
                } else if (temp < 0) {
                    break;

                //volgende gerecht
                } else temp = temp + gerecht;
            }
        }
    }
}
class Foodtruck {
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
}
public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Inlezen testgevallen
        int aantalTestgevallen = sc.nextInt();
        for(int a = 1; a<=aantalTestgevallen; a++) {

            //Inlezen budgetten
            int aantalBudgetten = sc.nextInt();
            int [] budgetten = new int[aantalBudgetten];
            for(int i = 0; i<aantalBudgetten; i++) {
                budgetten[i] = sc.nextInt();
            }

            //Inlezen Foodtrucks
            int aantalFoodtrucks = sc.nextInt();
            Foodtrucks foodtrucks = new Foodtrucks(aantalFoodtrucks);
            for(int i = 0; i<aantalFoodtrucks; i++) {
                int aantalGerechten = sc.nextInt();
                Foodtruck foodtruck = new Foodtruck(aantalGerechten);
                for(int j = 0; j <aantalGerechten; j++) {
                    foodtruck.voegGerechtToe(j, sc.nextInt());
                }
                foodtrucks.voegFoodtruckToe(foodtruck);
            }

            //Overlopen
            ArrayList<Integer> oplossing = new ArrayList<>();
            int temp;
            for(int i = 0; i<aantalBudgetten; i++) {
                temp = budgetten[i];
                foodtrucks.controleer(budgetten[i], oplossing, temp, 0);
            }

            //Uitprinten
            System.out.print(a);
            if(oplossing.isEmpty()) System.out.println(" GEEN");
            else {
                for(int i = 0; i<oplossing.size(); i++) {
                    System.out.print(" " + oplossing.get(i));
                }
                System.out.println();
            }


        }
        sc.close();
    }

}