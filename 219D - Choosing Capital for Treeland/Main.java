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

    static void solve(FastReader in, PrintWriter out) throws IOException {
        int n = in.nextInt();
        int[][] edges = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            edges[i][0] = in.nextInt();
            edges[i][1] = in.nextInt();
        }
        choosingCapitalForTreeLand(n, edges);
    }

    static List<int[]>[] graph;
    static Integer[] dp;

    static int dfs(int curr, int p, int n, Integer[] dp) {
        int val = 0;
        for (int[] e : graph[curr]) {
            int v = e[0];
            int dir = e[1];
            if (v == p) {
                continue;
            }
            if (dir == -1) {
                val++;
            }
            val += dfs(v, curr, n, dp);
        }
        dp[curr] = val;
        return dp[curr];
    }

    static void reroot(int u, int p, int n, Integer[] dp) {
        for (int[] e : graph[u]) {
            int v = e[0];
            int dir = e[1];
            if (v == p) {
                continue;
            }
            dp[v] = dp[u] + (dir == 1 ? 1 : -1);
            reroot(v, u, n, dp);
        }
    }

    static void choosingCapitalForTreeLand(int n, int[][] edges) {
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        dp = new Integer[n + 1];
        for (int i = 0; i < n - 1; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            graph[u].add(new int[] { v, 1 });
            graph[v].add(new int[] { u, -1 });
        }

        dfs(1, -1, n, dp);
        reroot(1, -1, n, dp);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (min > dp[i]) {
                min = dp[i];
            }
        }
        System.out.println(min);
        for (int i = 1; i <= n; i++) {
            if (dp[i] == min) {
                System.out.print(i + " ");
            }
        }

    }

}
