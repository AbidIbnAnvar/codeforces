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
        int[][] problems = new int[n][3];
        for (int i = 0; i < n; i++) {
            problems[i][0] = in.nextInt();
            problems[i][1] = in.nextInt();
            problems[i][2] = in.nextInt();
        }
        int result = solve(n, problems);
        out.println(result);
    }

    static int solve(int n, int[][] problems) {
        int canSolve = 0;
        for (int[] problem : problems) {
            if (problem[0] + problem[1] + problem[2] > 1) {
                canSolve++;
            }
        }
        return canSolve;
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