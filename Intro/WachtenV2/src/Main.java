import java.util.Scanner;

public class Main {
    public static void uitprintenArray(int [] a) {
        for(int i = 0; i<a.length; i++) {
            System.out.println((i+1) + " " + a[i]);
        }
    }

    public static void doorschuiven(int [] a) {
        int koper = a[0] - 1;
        for(int i = 0; i<a.length-1; i++) {
            a[i] = a[i+1];
        }
        a[a.length - 1] = koper;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int aantalTestGevallen = sc.nextInt();
        int [] uitkomst = new int[aantalTestGevallen];

        //for-lus voor het aantal testgevallen
        for (int index = 0; index < aantalTestGevallen; index++) {
            //info inlezen over huidig testgeval
            int a = sc.nextInt();
            int p = sc.nextInt() - 1;
            int teller = 0;

            //Inlezen rij
            int[] rij = new int[a];

            for (int i = 0; i < a; i++) {
                rij[i] = sc.nextInt();
            }
            while (true) {
                if(a>1) {
                    if(rij[0] ==0) a--;
                    if(p>0) p--;
                    else if (p==0) {
                        if (rij[p] == 0) {
                            teller++;
                            break;
                        }
                        else if (a!=1) p = a-1;
                    }
                    doorschuiven(rij);
                }
                teller++;
            }
            uitkomst[index] = teller;
        }
        uitprintenArray(uitkomst);
        sc.close();
    }
}