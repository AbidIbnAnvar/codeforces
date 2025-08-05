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
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] difficulty = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                difficulty[i] = in.nextInt();
            }
            System.out.println(mortalKombatTower(n, difficulty));
        }
    }

    private enum TURN {
        FRIEND, MINE
    };

    static long dfs(int index, TURN turn, int n, int[] difficulty, long[][] dp) {
        int t = turn == TURN.FRIEND ? 0 : 1;
        if (index > n) {
            return 0;
        }
        if (dp[t][index] != -1) {
            return dp[t][index];
        }

        long ans = 0;

        switch (turn) {
            case FRIEND -> {
                if (index + 1 <= n) {
                    ans = Math.min(
                            difficulty[index] + dfs(index + 1, TURN.MINE, n, difficulty, dp),
                            difficulty[index] + difficulty[index + 1] + dfs(index + 2, TURN.MINE, n, difficulty, dp));
                } else {
                    ans = difficulty[index];
                }

            }
            case MINE -> {
                if (index + 1 <= n) {
                    ans += Math.min(dfs(index + 1, TURN.FRIEND, n, difficulty, dp),
                            dfs(index + 2, TURN.FRIEND, n, difficulty, dp));
                } else {
                    ans += dfs(index + 1, TURN.FRIEND, n, difficulty, dp);
                }
            }
        }
        return dp[t][index] = ans;
    }

    static long mortalKombatTower(int n, int[] difficulty) {
        long[][] dp = new long[2][n + 1];
        for (long[] item : dp) {
            Arrays.fill(item, -1);
        }
        return dfs(1, TURN.FRIEND, n, difficulty, dp);
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