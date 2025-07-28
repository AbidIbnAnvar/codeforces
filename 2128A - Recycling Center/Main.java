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
        int[] result = new int[t];
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int c = in.nextInt();
            long[] weights = new long[n];
            for (int j = 0; j < n; j++) {
                weights[j] = in.nextInt();
            }
            result[i] = recyclingCenter(n, c, weights);
        }
        for (int i = 0; i < t; i++) {
            out.println(result[i]);
        }

    }

    static void printArray(long[] arr) {
        for (long i : arr) {
            System.out.print(i + ",");
        }
        System.out.print("\n");
    }

    static void printArray(boolean[] arr) {
        for (boolean i : arr) {
            System.out.print(i + ",");
        }
        System.out.print("\n");
    }

    public static Map<Integer, Integer> memo = new HashMap<>();

    static void sortInDecreasingOrder(long[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length / 2; i++) {
            long temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }

    static int recyclingCenter(int n, int c, long[] weights) {
        sortInDecreasingOrder(weights);
        boolean[] visited = new boolean[n];
        int coins = 0;
        int t = 0;
        for (int i = 0; i < n; i++) {
            long max = 0;
            int max_index = -1;
            for (int j = 0; j < n; j++) {
                if (visited[j])
                    continue;
                if ((weights[j] * (1 << t)) <= c) {
                    if (weights[j] * (1 << t) > max) {
                        max = weights[j] * (1 << t);
                        max_index = j;
                    }
                }
            }
            if (max_index == -1) {
                break;
            }
            visited[max_index] = true;
            t++;
            coins++;
        }
        return n - coins;
    }

    static int helper(int n, int c, long[] weights, boolean[] visited, int iteration) {
        // BASE CASE: once we've chosen n items, no more "extra rounds" needed
        if (iteration == n) {
            return 0;
        }

        int minRounds = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;

                int extra = ((weights[i] << iteration) > c) ? 1 : 0;
                int candidate = extra + helper(n, c, weights, visited, iteration + 1);

                minRounds = Math.min(minRounds, candidate);
                visited[i] = false;
            }
        }

        return minRounds;
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