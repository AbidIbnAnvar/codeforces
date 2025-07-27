import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        solve(in, out);

        out.flush();
    }

    static void solve(FastReader in, PrintWriter out) throws IOException {
        int n = in.nextInt();
        int h = in.nextInt();
        int l = in.nextInt();
        int r = in.nextInt();
        int[] sleep = new int[n];
        for (int i = 0; i < n; i++) {
            sleep[i] = in.nextInt();
        }
        int result = sleepingSchedule(n, h, l, r, sleep);
        out.println(result);
    }

    static int sleepingSchedule(int n, int h, int l, int r, int[] sleep) {
        Integer[][] dp = new Integer[n][h];
        return helper(0, 0, n, h, l, r, sleep, dp);
    }

    static int helper(int i, int t, int n, int h, int l, int r, int[] sleep, Integer[][] dp) {
        if (i == n)
            return 0;
        if (dp[i][t] != null)
            return dp[i][t];

        int sleep1 = (t + sleep[i]) % h;
        int sleep2 = (t + sleep[i] - 1 + h) % h;

        int res1 = helper(i + 1, sleep1, n, h, l, r, sleep, dp) + (sleep1 >= l && sleep1 <= r ? 1 : 0);
        int res2 = helper(i + 1, sleep2, n, h, l, r, sleep, dp) + (sleep2 >= l && sleep2 <= r ? 1 : 0);

        return dp[i][t] = Math.max(res1, res2);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}