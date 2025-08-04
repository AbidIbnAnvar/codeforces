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
        int[] cats = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            cats[i] = in.nextInt();
        }
        int[][] edges = new int[n][2];
        for (int i = 0; i < n - 1; i++) {
            edges[i][0] = in.nextInt();
            edges[i][1] = in.nextInt();
        }
        System.out.println(kefaAndPark(n, m, cats, edges));
    }

    static Map<Integer, List<Integer>> convertToAdjList(int[][] edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[0], (a) -> new ArrayList<Integer>()).add(edge[1]);
            adj.computeIfAbsent(edge[1], (a) -> new ArrayList<Integer>()).add(edge[0]);
        }
        return adj;
    }

    static int dfs(int index, int catCount, int m, int[] cats, Map<Integer, List<Integer>> adj, boolean[] visited) {
        visited[index] = true;
        catCount = (cats[index] == 1) ? catCount + 1 : 0;
        if (catCount > m) {
            return 0;
        }
        int ans = 0;
        boolean isLeaf = true;
        if (adj.containsKey(index)) {
            for (Integer adjacent : adj.get(index)) {
                if (visited[adjacent]) {
                    continue;
                }
                isLeaf = false;
                ans += dfs(adjacent, catCount, m, cats, adj, visited);
            }
        }
        return isLeaf ? 1 : ans;
    }

    static int kefaAndPark(int n, int m, int[] cats, int[][] edges) {
        Map<Integer, List<Integer>> adj = convertToAdjList(edges);
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(visited, false);
        return dfs(1, 0, m, cats, adj, visited);
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