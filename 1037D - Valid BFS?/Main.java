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
        int[][] edges = new int[n][2];
        for (int i = 0; i < n - 1; i++) {
            edges[i][0] = in.nextInt();
            edges[i][1] = in.nextInt();
        }
        int[] seq = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            seq[i] = in.nextInt();
        }
        System.out.println((validBfs(n, edges, seq)) ? "YES" : "NO");

    }

    static Map<Integer, List<Integer>> convertToAdjMap(int[][] edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            adj.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }
        return adj;
    }

    static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    static boolean validBfs(int n, int[][] edges, int[] seq) {
        Map<Integer, List<Integer>> adj = convertToAdjMap(edges);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        queue.add(1);
        visited[1] = true;
        return bfs(2, n, adj, queue, visited, seq);
    }

    static boolean bfs(int index, int n, Map<Integer, List<Integer>> adj, Queue<Integer> queue,
            boolean[] visited, int[] seq) {
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            List<Integer> children = new ArrayList<>();
            visited[currentNode] = true;
            for (Integer neigbours : adj.getOrDefault(currentNode, new ArrayList<>())) {
                if (visited[neigbours]) {
                    continue;
                }
                children.add(neigbours);
            }
            Set<Integer> expected = new HashSet<>(children);
            int k = children.size();
            for (int i = 0; i < k; i++) {
                if (index + i > n || !expected.contains(seq[index + i])) {
                    return false;
                }
            }
            for (int i = 0; i < k; i++) {
                int child = seq[index + i];
                queue.add(child);
                visited[child] = true;
            }
            index += k;
        }
        return true;
    }

}