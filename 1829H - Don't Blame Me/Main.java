import java.io.*;
import java.util.*;

public class Main {
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

        short nextShort() {
            return Short.parseShort(next());
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

    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        solve(in, out);

        out.flush();
    }

    static final int MOD = 1_000_000_007;

    static void solve(FastReader in, PrintWriter out) throws IOException {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            short k = in.nextShort();
            short[] arr = new short[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextShort();
            }
            System.out.println(dontBlameMe(n, k, arr));
        }
    }

    static int dontBlameMe(int n, short k, short[] arr) {
        int ans = 0;
        int[][] dp = new int[n + 1][64];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 64; j++) {
                dp[i + 1][j] = (dp[i + 1][j] + dp[i][j]) % MOD;
                dp[i + 1][j & arr[i]] = (dp[i + 1][j & arr[i]] + dp[i][j]) % MOD;
            }
            dp[i + 1][arr[i]] = (dp[i + 1][arr[i]] + 1) % MOD;
        }
        for (int i = 0; i < 64; i++) {
            if (Integer.bitCount(i) == k) {
                ans = (ans + dp[n][i]) % MOD;
            }
        }
        return ans % MOD;
    }

}