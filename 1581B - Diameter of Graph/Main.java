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
        boolean[] result = new boolean[t];
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            result[i] = diameterOfGraph(n, m, k);
        }
        for (boolean res : result) {
            if (res) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
    }

    static boolean diameterOfGraph(int n, int m, int k) {
        long maxEdges = (long) n * (n - 1) / 2;
        if (m < n - 1) {
            return false;
        }
        if (m > maxEdges) {
            return false;
        }
        if (n == 1) {
            return k > 1;
        }
        if (m == maxEdges) {
            return k > 2;
        }
        if (m == n - 1) {
            return k > 3;
        }
        if (m > n - 1 && m < maxEdges) {
            return k > 3;
        }
        return false;
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