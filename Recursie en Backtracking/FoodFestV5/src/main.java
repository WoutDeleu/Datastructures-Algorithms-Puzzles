import java.util.Scanner;

public class main {
    private static boolean bevatOplossing(boolean[] oplossing) {
        boolean ret  = false;
        for(boolean b : oplossing) {
            if(b) ret =  true;
        }
        return ret;
    }

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
            boolean [] oplossing = new boolean[aantalBudgetten];
            for(int i = 0; i<aantalBudgetten; i++) {
                        oplossing[i] = foodtrucks.controleer(budgetten[i]);
            }

            //Uitprinten
            System.out.print(a);
            if(!bevatOplossing(oplossing)) System.out.println(" GEEN");
            else {
                for(int i = 0; i<aantalBudgetten; i++) {
                    if(oplossing[i]) System.out.print(" " + budgetten[i]);
                }
                System.out.println();
            }


        }
        sc.close();
    }

}