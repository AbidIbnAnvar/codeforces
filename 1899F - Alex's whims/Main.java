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
        // Your problem-solving code goes here
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int q = in.nextInt();
            int[] days = new int[q];
            for (int i = 0; i < q; i++) {
                days[i] = in.nextInt();
            }
            System.out.print(alexsWhims(n, q, days).toString());
        }
    }

    static class Edge {
        int u;
        int v;

        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }

        @Override
        public String toString() {
            return u + " " + v + "\n";
        }
    }

    static class Response {
        List<Edge> edges;
        int[][] operations;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("");
            for (Edge edge : edges) {
                sb.append(edge.toString());
            }
            for (int[] op : operations) {
                String str = op[0] + " " + op[1] + " " + op[2] + "\n";
                sb.append(str);
            }
            return sb.toString();
        }
    }

    static Response alexsWhims(int n, int q, int[] days) {
        Response response = new Response();
        int[][] operations = new int[q][3];
        List<Edge> edges = new ArrayList<>();
        for (int i = 1; i <= n - 1; i++) {
            edges.add(new Edge(i, i + 1));
        }
        int currentDistance = n - 1;
        for (int i = 0; i < q; i++) {
            if (days[i] == currentDistance) {
                operations[i] = new int[] { -1, -1, -1 };
            } else {
                operations[i] = new int[] { n, currentDistance, days[i] };
                currentDistance = days[i];
            }
        }

        response.operations = operations;
        response.edges = edges;
        return response;
    }

}