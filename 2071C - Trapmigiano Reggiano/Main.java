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
            int n = in.nextInt();
            int st = in.nextInt();
            int en = in.nextInt();
            int[][] edges = new int[n - 1][2];
            for (int i = 0; i < n - 1; i++) {
                edges[i] = new int[] { in.nextInt(), in.nextInt() };
            }
            int[] result = trapmigianoReggiano(n, st, en, edges);
            for (int res : result) {
                System.out.print(res + " ");
            }
            System.out.println();
        }
    }

    static Map<Integer, List<Integer>> getAdjGraph(int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            map.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            map.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }
        return map;
    }

    static void dfs(int curr, Map<Integer, List<Integer>> adj, Set<Integer> visited, List<Integer> preOrder) {
        if (visited.contains(curr)) {
            return;
        }
        visited.add(curr);
        List<Integer> neigbors = adj.getOrDefault(curr, new ArrayList<>());
        for (int nei : neigbors) {
            if (!visited.contains(nei)) {
                dfs(nei, adj, visited, preOrder);
            }
        }
        preOrder.add(curr);
    }

    static List<Integer> preOrderTraversal(int st, int en, Map<Integer, List<Integer>> adj) {
        Set<Integer> visited = new HashSet<>();
        List<Integer> preOrder = new ArrayList<>();
        dfs(en, adj, visited, preOrder);
        return preOrder;
    }

    static int[] trapmigianoReggiano(int n, int st, int en, int[][] edges) {
        Map<Integer, List<Integer>> adj = getAdjGraph(edges);
        List<Integer> list = preOrderTraversal(st, en, adj);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

}