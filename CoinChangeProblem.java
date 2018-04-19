import java.util.Arrays;

/**
 * David Ackerman
 * AP CS A
 * 4/18/2018
 * CoinChange
 * Description:
 */
public class CoinChangeProblem {
    private int[] d;
    private int a;
    private int[] res;
    private int m;
    private boolean solved;

    public CoinChangeProblem(int[] denoms, int amt) {
        this.d = denoms;
        this.a = amt;
        res = new int[denoms.length];
        m = 0;
        solve();
    }

    private void solve() {
        //array to hold results
        int[] r = new int[a + 1];
        //array to determine c
        int[] s = new int[a + 1];
        //initialize array
        for (int i = 0; i <= a; i++) {
            r[i] = Integer.MAX_VALUE - 1;
        }
        r[0] = 0;
        for (int i = 0; i < d.length; i++) {
            for (int j = 1; j <= a; j++) {
                //see if we can add coin of denom v[i]
                if (j - d[i] >= 0) {
                    //check to see if better
                    if (r[j] > 1 + r[j - d[i]]) {
                        r[j] = 1 + r[j - d[i]];
                        //update array for computing c
                        s[j] = i;
                    }
                }
            }
//            System.out.println(Arrays.toString(r));
//            System.out.println(Arrays.toString(s));
        }
        //array of coins
        m = r[a];
        int temp = a;
        while (temp > 0) {
            int denom = s[temp];
            res[denom]++;
            temp -= d[denom];
        }
        solved = true;
    }

    public int[] minCoinArray() {
        return res;
    }

    public int minCoins() {
        return m;
    }

    public void printProblem() {
        System.out.println("Denominations: " + Arrays.toString(d));
        System.out.println("Amount: " + a);
    }

    public void printAnswer() {
        String str = "";
        boolean first = true;
        for (int i = 0; i < res.length; i++) {
            if (res[i] != 0) {
                int count = res[i];
                if (first) {
                    first = false;
                    str += d[i];
                    count--;
                }

                for (int j = 0; j < count; j++) {
                    str += " + " + d[i];
                }
            }
        }
        String ans = "Min coins needed: " + m + "\n"
                + "Denominations: " + Arrays.toString(res) + "\n"
                + a + " = " + str;
        System.out.println(ans);
    }

    public String fileOutput() {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < d.length; i++) {
            ans.append(d[i]);
            if (i != d.length - 1) {
                ans.append(" ");
            }
        }
        ans.append("\n" + a + "\n");
        for (int i = 0; i < res.length; i ++) {
            ans.append(res[i]);
            if (i != res.length -1) {
                ans.append(" ");
            }
        }
        ans.append("\n" + m);
        return ans.toString();
    }
}
