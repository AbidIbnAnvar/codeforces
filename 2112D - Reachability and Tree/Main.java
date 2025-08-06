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
        int t = in.nextInt();
        while (t-- > 0) {
            final int n = in.nextInt();
            final int[][] edges = new int[n - 1][2];
            for (int i = 0; i < n - 1; i++) {
                edges[i][0] = in.nextInt();
                edges[i][1] = in.nextInt();
            }
            List<int[]> result = reachabilityAndTree(n, edges);
            if (result.isEmpty()) {
                out.println("NO");
            } else {
                out.println("YES");
                for (int[] res : result) {
                    out.println(res[0] + " " + res[1]);
                }
            }
        }
    }

    static Map<Integer, List<Integer>> convertToAdjList(int[][] arr) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int[] item : arr) {
            adj.computeIfAbsent(item[0], a -> new ArrayList<>()).add(item[1]);
            adj.computeIfAbsent(item[1], a -> new ArrayList<>()).add(item[0]);
        }
        return adj;
    }

    static enum Degree {
        IN_DEGREE,
        OUT_DEGREE;

        public Degree opposite() {
            return this == IN_DEGREE ? OUT_DEGREE : IN_DEGREE;
        }
    };

    static void dfs(int vertex, Degree degree, boolean[] visited, Map<Integer, List<Integer>> adj,
            List<int[]> result) {
        visited[vertex] = true;

        for (Integer neighbour : adj.getOrDefault(vertex, new ArrayList<>())) {
            if (!visited[neighbour]) {
                if (degree == Degree.IN_DEGREE) {
                    result.add(new int[] { neighbour, vertex });
                } else {
                    result.add(new int[] { vertex, neighbour });
                }
                dfs(neighbour, degree.opposite(), visited, adj, result);
            }
        }
    }

    static List<int[]> reachabilityAndTree(int n, int[][] edges) {
        int[] degrees = new int[n + 1];
        for (int[] edge : edges) {
            degrees[edge[0]]++;
            degrees[edge[1]]++;
        }
        int found = -1;
        for (int i = 1; i <= n; i++) {
            if (degrees[i] == 2) {
                found = i;
                break;
            }
        }
        if (found == -1) {
            return Collections.emptyList();
        }
        Map<Integer, List<Integer>> adj = convertToAdjList(edges);
        boolean[] visited = new boolean[n + 1];
        List<int[]> result = new ArrayList<>();
        visited[found] = true;
        int u = adj.get(found).get(0);
        int w = adj.get(found).get(1);
        result.add(new int[] { u, found });
        result.add(new int[] { found, w });
        dfs(u, Degree.OUT_DEGREE, visited, adj, result);
        dfs(w, Degree.IN_DEGREE, visited, adj, result);
        return result;
    }

}