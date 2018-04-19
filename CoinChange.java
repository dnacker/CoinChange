import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * David Ackerman
 * AP CS A
 * 4/18/2018
 * CoinChange
 * Description:
 */
public class CoinChange {
    public static void main(String[] args) {
        List<CoinChangeProblem> problems = readFile("amount.txt");
        int num = 1;
        for (CoinChangeProblem p : problems) {
            int[] c = p.minCoinArray();
            int m = p.minCoins();
            System.out.println("Problem #" + num + ":");
            p.printProblem();
            p.printAnswer();
            System.out.println("--------------------------------------");
            num++;
        }
        writeFile("change.txt", problems);
    }

    public static List<CoinChangeProblem> readFile(String filename) {
        BufferedReader br = null;
        List<CoinChangeProblem> problems = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            while (line != null) {
                String[] sVals = line.split(" ");
                int[] vals = new int[sVals.length];
                for (int i = 0; i < sVals.length; i++) {
                    vals[i] = Integer.parseInt(sVals[i]);
                }

                int amt = Integer.parseInt(br.readLine());
                problems.add(new CoinChangeProblem(vals, amt));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        return problems;
    }

    public static void writeFile(String filename, List<CoinChangeProblem> solvedProblems) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filename));
            for (int i = 0; i < solvedProblems.size(); i++) {
                CoinChangeProblem problem = solvedProblems.get(i);
                bw.write(problem.fileOutput());
                if (i != solvedProblems.size() - 1) {
                    bw.write("\n");
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    /**
     * Determines the set of coins with denominations v_1, ..., v_n
     * such that the number of coins in the set adds to A and is
     * minimal.
     * @param v, an array of denominations
     * @param A, an amount the coins add to
     * @return an array C such that c_i is the number of coins
     * with value v_i, and the sum of c_i is minimal
     */
    public static int[] minCoinChange(int[] v, int A) {
        //array to hold results
        int[] r = new int[A + 1];
        //array to determine c
        int[] s = new int[A + 1];
        //initialize array
        for (int i = 0; i <= A; i++) {
            r[i] = Integer.MAX_VALUE - 1;
        }
        r[0] = 0;
        for (int i = 0; i < v.length; i++) {
            for (int j = 1; j <= A; j++) {
                //see if we can add coin of denom v[i]
                if (j - v[i] >= 0) {
                    //check to see if better
                    if (r[j] > 1 + r[j - v[i]]) {
                        r[j] = 1 + r[j - v[i]];
                        //update array for computing c
                        s[j] = i;
                    }
                }
            }
//            System.out.println(Arrays.toString(r));
//            System.out.println(Arrays.toString(s));
        }
        //array of coins
        int[] c = new int[v.length];
        while (A > 0) {
            int denom = s[A];
            c[denom]++;
            A -= v[denom];
        }
        return c;
    }

    public static int minCoins(int[] c) {
        int count = 0;
        for (int n : c) {
            count += n;
        }
        return count;
    }

}
