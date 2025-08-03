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
        while (t-- > 0) {
            int n = in.nextInt();
            long W = in.nextLong();
            Pair[] items = new Pair[n];
            for (int i = 0; i < n; i++) {
                items[i] = new Pair(in.nextInt(), i + 1);
            }
            Integer[] result = knapsack(n, W, items);
            if (result[0] == -1) {
                out.println("-1");
                continue;
            }
            out.println(result.length);
            for (int res : result) {
                out.print(res + " ");
            }
            out.println();
        }
    }

    static <T> void printArray(T[] arr) {
        for (T element : arr) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    static class Pair {
        public int first;
        public int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static Integer[] knapsack(int n, long W, Pair[] items) {
        long minLimit = (W + 1) / 2;
        long maxLimit = W;
        for (Pair item : items) {
            if (item.first >= minLimit && item.first <= maxLimit) {
                return new Integer[] { item.second };
            }
        }
        Arrays.sort(items, (a, b) -> Integer.compare(a.first, b.first));
        List<Integer> chosen = new ArrayList<>();
        long sum = 0;
        for (Pair item : items) {
            if (sum + item.first <= maxLimit) {
                sum += item.first;
                chosen.add(item.second);
            }
            if (sum >= minLimit) {
                return chosen.toArray(Integer[]::new);
            }
        }
        return new Integer[] { -1 };
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