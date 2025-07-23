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
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = in.nextInt();
        }
        int q = in.nextInt();
        int[] coins = new int[q];
        for (int i = 0; i < q; i++) {
            coins[i] = in.nextInt();
        }
        int[] result = interestingDrink(n, prices, q, coins);
        for (int i = 0; i < result.length; i++) {
            out.println(result[i]);
        }
    }

    static int getIndex(int n, int[] arr) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (arr[middle] > n) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    static int[] interestingDrink(int n, int[] prices, int q, int[] coins) {
        int[] result = new int[q];
        Arrays.sort(prices);
        for (int i = 0; i < q; i++) {
            result[i] = getIndex(coins[i], prices);
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