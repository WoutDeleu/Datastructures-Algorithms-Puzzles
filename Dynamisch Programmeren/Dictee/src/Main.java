import java.util.Scanner;

//https://www.youtube.com/watch?v=AY2DZ4a9gyk

public class Main {
    private static int levenshtein(char[] zin, char[] correcteZin) {
        int [][] minimaleAfstand = new int[zin.length+1][correcteZin.length+1];
        for(int i = 0; i<= zin.length; i++) {
            for (int j = 0; j <= correcteZin.length; j++) {
                //+1 voor de #
                if (i == 0) minimaleAfstand[i][j] = 2 * j;
                else if (j == 0) minimaleAfstand[i][j] = 2 * i;
                else {
                    int verschil = berekenKost(zin[i - 1], correcteZin[j - 1]);
                    int tijdelijkeWaarde = Math.min(minimaleAfstand[i - 1][j] + 2, minimaleAfstand[i][j - 1] + 2);
                    minimaleAfstand[i][j] = Math.min(tijdelijkeWaarde, minimaleAfstand[i - 1][j - 1] + verschil);
                }
            }
        }
        return minimaleAfstand[zin.length][correcteZin.length];
    }

    public static int berekenKost(char a, char b) {
        if(a==b) return 0;
        else if(a ==b+32 || a ==b-32) {
            if(b >= 64 && b <= 90 ||  b >= 97 && b <= 122) return 1;
        }
        return 2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int aantalTestgevallen = Integer.parseInt(sc.nextLine());

        for (int a = 1; a<=aantalTestgevallen; a++) {
            char[] zin = sc.nextLine().toCharArray();
            char[] correcteZin = sc.nextLine().toCharArray();

            System.out.println(a +  " " + levenshtein(zin, correcteZin));
        }
    }
}
