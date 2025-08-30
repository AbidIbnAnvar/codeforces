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
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            int d = in.nextInt();
            System.out.println(inTheDream(a, b, c, d) ? "YES" : "NO");
        }
    }

    static boolean inTheDream(int a, int b, int c, int d) {
        int t1 = Math.min(a, b);
        int t2 = Math.max(a, b);
        if (t2 > 2 * (t1 + 1)) {
            return false;
        }
        int rem1 = c - a;
        int rem2 = d - b;
        if (rem1 < 0 || rem2 < 0) {
            return false;
        }
        t1 = Math.min(rem1, rem2);
        t2 = Math.max(rem1, rem2);
        if (t2 > 2 * (t1 + 1)) {
            return false;
        } else {

        }
        return true;
    }

}
