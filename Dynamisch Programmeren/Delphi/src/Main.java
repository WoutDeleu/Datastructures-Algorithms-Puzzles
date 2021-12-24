import java.util.Scanner;
import static java.lang.Integer.max;

public class Main {
    private static int delphi(int aantalNeeVragen, int periode) {
        int res = periode;
        /*
      We do +1 to index off of 1. So that the final answer that
      we want will be at cache[aantalNeeVragen][periode]...remember
      we index off of 0 so this is for convenience
      cache[aantalNeeVragen][periode] is literally the answer to the
      subproblem given those literal amounts...'aantalNeeVragen' and
      'periode'
    */
        int cache[][] = new int[aantalNeeVragen + 1][periode + 1];

    /*
      If we have 0 floors we need 0 trials, no matter the egg amount given
      If we have 1 floor we need 1 trial, no matter the egg amount given
    */
        for (int i = 1; i <= aantalNeeVragen; i++) {
            cache[i][0] = 0;
            cache[i][1] = 1;
        }

    /*
      If we have 1 egg...no matter what floors we get, our approach will
      be to do 'floorAmount' drops...this is because we want to start from
      floor 1, drop...then go to floor 2, drop...and so on until we get to
      in the worst case 'floorAmount'
      Remember, we want to know the minimum amount of drops that will always
      work. So we want to MINIMIZE...to the absolute LEAST...worst...amount
      of drops so that our drop count ALWAYS works
      Any worse then the MINIMIZED WORST will be suboptimal
    */
        for (int i = 1; i <= periode; i++) {
            cache[1][i] = i;
        }

    /*
      Solve the rest of the subproblems now that we have base cases defined
      for us
    */
        for (int i = 2; i <= aantalNeeVragen; i++) {
            for (int j = 2; j <= periode; j++) {
        /*
          Initialize the answer to this subproblem to a very large
          value that will be easily overtaken and provide a hard upper
          comparison wall
        */
                cache[i][j] = Integer.MAX_VALUE;

        /*
          We do a theoretical test on every floor from 1 to the 'floor'
          amount for this subproblem.
          At each 'attemptFloor' we express both possibilities described below
        */
                for (int k = 1; k <= j; k++) {
                    res = max(cache[i-1][k-1], 1+cache[i][j-k]);
                    if (res < cache[i][j]) cache[i][j] = res;

                }
            }
        }

    /*
      Remember we added +1 so we are indexed off of 1 now. We can reap our answer from
      cache[aantalNeeVragen][periode] instead of cache[aantalNeeVragen - 1][periode - 1]
    */
        int max = 0;
        for (int rij = 0; rij <= periode; rij++)
        {
            if (cache[aantalNeeVragen][rij] > max)
            {
                max = cache[aantalNeeVragen][rij];
            }
        }

        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int aantalTestgevallen = sc.nextInt();
        for(int a = 1; a<=aantalTestgevallen; a++) {
            int aantalNeeVragen = sc.nextInt();
            int periode = sc.nextInt();
            System.out.println(a + " " + delphi(aantalNeeVragen, periode));
        }
    }

    
}


