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
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            int[] res = getArray(n, arr);
            int ans = Math.abs(res[n - 1] - res[0]);
            System.out.println(ans);
            for (int a : res) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }

    static int[] getArray(int n, int[] arr) {
        for (int i = 1; i < n - 1; i++) {
            if (arr[i] == -1) {
                arr[i] = 0;
            }
        }
        if (arr[0] == -1 && arr[n - 1] == -1) {
            arr[0] = 0;
            arr[n - 1] = 0;
        } else if (arr[0] == -1) {
            arr[0] = arr[n - 1];
        } else if (arr[n - 1] == -1) {
            arr[n - 1] = arr[0];
        }
        return arr;
    }

}
