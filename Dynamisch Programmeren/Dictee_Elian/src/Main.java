import java.util.Scanner;

public class Main{
    //static variabelen en methoden kunnen hier
    private static char[] inputString;
    private static char[] voorbeeldString;

    private static int minimaleFoutZoeken(char[]  inputString, char[]  voorbeeldString) {
        int[][] matrix = new int[inputString.length + 1][voorbeeldString.length + 1];
        for (int i = 0; i <= inputString.length; i++) {
            for (int j = 0; j <= voorbeeldString.length; j++) {
                /*
                Op de 1ste rij zijn alle waarden steeds een veelvoud van 2, de reden hiervoor is dat een letter steeds
                een substitutie is t.o.v. geen letter. Dit is ook zo in de 1ste kolom
                 */
                if (i == 0) {
                    matrix[i][j] = j*2;
                } else if (j == 0) {
                    matrix[i][j] = i*2;
                } else {
                    int tijdelijkeWaarde = Math.min(matrix[i - 1][j] + 2, matrix[i][j - 1] + 2);
                    matrix[i][j] = Math.min(tijdelijkeWaarde, matrix[i - 1][j - 1] + kostVanSubstitutie(inputString[i-1], voorbeeldString[j-1]));
                }
            }
        }
        return matrix[inputString.length][voorbeeldString.length];
    }
    private static int kostVanSubstitutie(char a, char b){
        //Als de characters gelijk zijn aan elkaar geef je 0 terug
        if (a == b){
            return 0;
        }
        /*
        Wanneer er een verwisseling is gebeurt en het is niet de hoofdletter ervan is de fout 2.
        Is de letter een hoofdletter in plaats van een kleine letter is de fout 1.
         */
        else if (a + 32 == b || a == b +32){
            if (b >= 64 && b <= 90 ||  b >= 97 && b <= 122){
                return 1;
            }
        }
        return 2;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        //inlezen aantal testgevallen
        int aantalTestGevallen = Integer.parseInt(sc.nextLine());

        for (int a=1;a<=aantalTestGevallen;a++){
            //inlezen gegevens voor 1 testgeval
            inputString = sc.nextLine().toCharArray();
            voorbeeldString = sc.nextLine().toCharArray();
            int kost = minimaleFoutZoeken(inputString,voorbeeldString);
            //oplossen van 1 testgeval

            //uitschrijven van 1 testgeval met nummer a
            System.out.println(a+" "+kost);

        }
        sc.close();
    }


}
