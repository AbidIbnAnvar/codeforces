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
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = in.nextInt();
            }
            int[] result = mochaAndHiking(n, arr);
            if (result[0] == -1) {
                System.out.println("-1");
            } else {
                for (int res : result) {
                    System.out.print(res + " ");
                }
                System.out.println();
            }
        }
    }

    static int[] mochaAndHiking(int n, int[] arr) {
        int last0Index = -1;
        int last1Index = -1;
        int[] result = new int[n + 1];
        Arrays.fill(result, 0);
        for (int i = 0; i < n; i++) {
            if (arr[i] == 1) {
                last1Index = i;
            } else if (arr[i] == 0) {
                last0Index = i;
            }
        }
        int index = 0;
        int i;
        for (i = 0; i <= last0Index; i++) {
            result[index++] = i + 1;
        }
        if (last0Index == n - 1) {
            result[index] = n + 1;
        } else {
            result[index++] = n + 1;
            for (i = last0Index + 1; i < n; i++) {
                result[index++] = i + 1;
            }
        }
        return result;
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