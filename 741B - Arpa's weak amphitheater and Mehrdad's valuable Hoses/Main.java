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
        int m = in.nextInt();
        int w = in.nextInt();
        // int[] weights = new int[n + 1];
        List<Integer> weights = new ArrayList<>();
        weights.add(0);
        for (int i = 1; i <= n; i++) {
            // weights[i] = in.nextInt();
            weights.add(in.nextInt());
        }
        // int[] beauties = new int[n + 1];
        List<Integer> beauties = new ArrayList<>();
        beauties.add(0);
        for (int i = 1; i <= n; i++) {
            // beauties[i] = in.nextInt();
            beauties.add(in.nextInt());
        }
        int[][] friends = new int[m][2];
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            friends[i] = new int[] { x, y };
        }
        out.println(solution(n, m, w, weights, beauties, friends));
    }

    static void convertToAdjacencyList(Map<Integer, List<Integer>> adj, int[][] arr) {
        for (int[] item : arr) {
            adj.computeIfAbsent(item[0], a -> new ArrayList<Integer>()).add(item[1]);
            adj.computeIfAbsent(item[1], a -> new ArrayList<Integer>()).add(item[0]);
        }
    }

    static void dfs(int i, int n, boolean[] visited, List<Integer> group, Map<Integer, List<Integer>> adj) {
        visited[i] = true;
        group.add(i);
        List<Integer> friends = adj.getOrDefault(i, new ArrayList<>());
        for (int friend : friends) {
            if (visited[friend])
                continue;
            dfs(friend, n, visited, group, adj);
        }
    }

    static int solution(int n, int m, int w, List<Integer> weights, List<Integer> beauties, int[][] friends) {

        Map<Integer, List<Integer>> adj = new HashMap<>();
        convertToAdjacencyList(adj, friends);

        List<List<Integer>> groups = new ArrayList<>();
        boolean[] visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (visited[i])
                continue;
            List<Integer> group = new ArrayList<>();
            dfs(i, n, visited, group, adj);
            groups.add(group);
        }

        int ng = groups.size();
        List<Integer> gWeights = new ArrayList<>();
        List<Integer> gBeauties = new ArrayList<>();
        gWeights.add(0);
        gBeauties.add(0);

        for (List<Integer> group : groups) {
            int gWeight = 0;
            int gBeauty = 0;
            for (Integer friend : group) {
                gWeight += weights.get(friend);
                gBeauty += beauties.get(friend);
            }
            gWeights.add(gWeight);
            gBeauties.add(gBeauty);
        }

        int[][] dp = new int[ng + 1][w + 1];
        for (int i = 0; i <= ng; i++) {
            for (int j = 0; j <= w; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = dp[i - 1][j];
                if (j >= gWeights.get(i)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - gWeights.get(i)] + gBeauties.get(i));
                }
                List<Integer> group = groups.get(i - 1);
                for (int k = 0; k < group.size(); k++) {
                    if (j < weights.get((int) group.get(k))) {
                        continue;
                    }
                    dp[i][j] = Math.max(dp[i][j],
                            dp[i - 1][j - weights.get((int) group.get(k))] + beauties.get((int) group.get(k)));
                }

            }
        }
        return dp[ng][w];
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