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
        presentFromLena(n, out);
    }

    static void presentFromLena(int n, PrintWriter out) {
        int i = 0;
        char[] arr;
        while (i <= n * 2) {
            int spaces = Math.abs(n - i);
            if (i <= n) {
                arr = new char[spaces * 2 + (2 * i + 1) + (2 * i)];
            } else {
                arr = new char[spaces * 2 + (2 * (n * 2 - i) + 1) + (2 * (n * 2 - i))];
            }
            Arrays.fill(arr, ' ');
            int curr = spaces * 2;
            if (i <= n) {
                for (int j = 0; j <= i; j++) {
                    arr[curr++] = (char) ('0' + j);
                    curr++;
                }
                for (int j = i - 1; j >= 0; j--) {
                    arr[curr++] = (char) ('0' + j);
                    curr++;
                }
            } else {
                for (int j = 0; j <= (n * 2) - i; j++) {
                    arr[curr++] = (char) ('0' + j);
                    curr++;
                }
                for (int j = (n * 2) - i - 1; j >= 0; j--) {
                    arr[curr++] = (char) ('0' + j);
                    curr++;
                }
            }
            out.println(arr);
            i++;
        }
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