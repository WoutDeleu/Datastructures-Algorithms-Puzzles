import java.util.HashMap;
import java.util.Scanner;

public class Main{
    //static variabelen en methoden kunnen hier

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int aantalTestGevallen;
        String input;
        char [][] l = new char[3][2];
        char [][] alfabet = new char[3][52];

        //Alfabet INLEZEN
        for (int i = 0; i<3; i++) {
            input = sc.nextLine();
            for (int j = 0; j<52; j++) {
                alfabet[i][j] = input.charAt(j);
            }
        }

        //Hashmap maken --> vertaaltaalkaart
        HashMap <BrailleLetter, String> vertaalkaart = new HashMap<>();
        int n = 0;
        for(char ch = 'A'; ch<='Z';ch++) {
            //letter afzonderen uit matrix
            for (int k = 0; k<2; k++) {
                for (int m = 0; m<3; m++) {
                    l[m][k] = alfabet[m][n];
                }
                n++;
            }

            BrailleLetter brailleLetter = new BrailleLetter(l);
            vertaalkaart.put(brailleLetter, Character.toString(ch));
        }

        //Testgevallen overlopen
        aantalTestGevallen = Integer.parseInt(sc.nextLine());
        for (int a=1;a<=aantalTestGevallen;a++){

            //Inlezen content
            input = sc.nextLine();
            char [][] tekst = new char[3][input.length()];
            boolean b = true;
            for (int i = 0; i<3; i++) {
                if(b) b = false;
                else input = sc.nextLine();
                for (int j = 0; j<input.length(); j++) {
                    tekst[i][j] = input.charAt(j);
                }
            }

            //Tekst in stukken trekken en vertalen
            System.out.print(a + " ");
            for(int m = 0; m<tekst[0].length;) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 3; j++) {
                        l[j][i] = tekst[j][m];
                    }
                    m++;
                }
                BrailleLetter brailleLetter = new BrailleLetter(l);
                System.out.print(vertaalkaart.get(brailleLetter));
            }
            System.out.println();
        }

        sc.close();
    }
}