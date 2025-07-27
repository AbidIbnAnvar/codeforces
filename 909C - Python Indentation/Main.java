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
        char[] statements = new char[n + 1];
        for (int i = 1; i <= n; i++) {
            statements[i] = in.nextLine().charAt(0);
        }
        int result = pythonIndentation(n, statements);
        out.println(result);
    }

    static int pythonIndentation(int n, char[] statements) {
        int[][] dp = new int[n + 1][n + 2];
        for (int l = 0; l <= n + 1; l++) {
            dp[n][l] = 1;
        }
        for (int i = n - 1; i >= 1; i--) {
            int sum = 0;
            for (int l = 0; l <= n; l++) {
                sum += dp[i + 1][l];
                sum %= MOD;
                if (statements[i] == 'f') {
                    dp[i][l] = dp[i + 1][l + 1];
                } else {
                    dp[i][l] = sum;
                }
            }
        }
        return dp[1][0];
    }

    static final int MOD = 1_000_000_007;

    static int helper(int i, int l, int n, char[] statements, Integer[][] dp) {
        if (i == n) {
            return 1;
        }
        if (dp[i][l] != null) {
            return dp[i][l];
        }
        int result = 0;
        if (statements[i] == 'f') {
            result = helper(i + 1, l + 1, n, statements, dp);
        } else {
            for (int prevLevel = 0; prevLevel <= l; prevLevel++) {
                result += helper(i + 1, prevLevel, n, statements, dp);
            }
        }
        return dp[i][l] = result % MOD;
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