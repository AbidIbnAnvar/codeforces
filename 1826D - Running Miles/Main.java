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
            int[] beauty = new int[n + 2];
            for (int i = 1; i <= n; i++) {
                beauty[i] = in.nextInt();
            }
            System.out.println(runningMiles(n, beauty));
        }
    }

    static int runningMiles(int n, int[] beauty) {
        int[] prefix = new int[n + 2];
        int[] suffix = new int[n + 2];
        int ans = Integer.MIN_VALUE;
        Arrays.fill(prefix, Integer.MIN_VALUE);
        Arrays.fill(suffix, Integer.MIN_VALUE);
        for (int i = 1; i <= n; i++) {
            prefix[i] = Math.max(prefix[i - 1], beauty[i] + i);
        }
        for (int i = n; i >= 1; i--) {
            suffix[i] = Math.max(suffix[i + 1], beauty[i] - i);
        }
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, beauty[i] + prefix[i - 1] + suffix[i + 1]);
        }
        return ans;
    }

}