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
            int[] arr = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(stayOrMirror(n, arr));
        }
    }

    static void printArray(int[] arr) {
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    static int supplement(int a, int n) {
        return 2 * n - a;
    }

    static int stayOrMirror(int n, int[] arr) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int right = 0;
            int left = 0;
            for (int j = 1; j <= i - 1; j++) {
                if (arr[j] > arr[i]) {
                    left++;
                }
            }
            for (int j = i + 1; j <= n; j++) {
                if (arr[j] > arr[i]) {
                    right++;
                }
            }
            ans += Math.min(left, right);
        }
        return ans;
    }

}