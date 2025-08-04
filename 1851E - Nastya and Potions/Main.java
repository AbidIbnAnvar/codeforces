import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        solve(in, out);

        out.flush();
    }

    static void printArray(long[] arr) {
        for (long item : arr) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    static void solve(FastReader in, PrintWriter out) throws IOException {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            List<Integer> costs = new ArrayList<>();
            costs.add(null);
            for (int i = 1; i <= n; i++) {
                costs.add(in.nextInt());
            }
            List<Integer> unlimited = new ArrayList<>();
            unlimited.add(null);
            for (int i = 1; i <= k; i++) {
                unlimited.add(in.nextInt());
            }
            List<List<Integer>> mixing = new ArrayList<>();
            mixing.add(null);
            for (int i = 0; i < n; i++) {
                int m = in.nextInt();
                if (m == 0) {
                    mixing.add(null);
                    continue;
                }
                List<Integer> temp = new ArrayList<>();
                for (int j = 1; j <= m; j++) {
                    temp.add(in.nextInt());
                }
                mixing.add(temp);
            }
            printArray(nastyaAndPotions(n, k, costs, unlimited, mixing));
        }
    }

    static long getCost(Integer index, List<Integer> unlimited, List<Integer> costs, List<List<Integer>> mixing,
            Map<Integer, Long> cache) {
        if (cache.containsKey(index)) {
            return cache.get(index);
        }
        long cost = 0;
        if (mixing.get(index) == null) {
            long val = costs.get(index);
            cache.put(index, val);
            return val;
        }
        for (Integer item : mixing.get(index)) {
            cost += getCost(item, unlimited, costs, mixing, cache);
        }
        cost = Math.min(cost, costs.get(index));
        cache.put(index, cost);
        return cost;
    }

    static long[] nastyaAndPotions(int n, int k, List<Integer> costs, List<Integer> unlimited,
            List<List<Integer>> mixing) {
        long[] result = new long[n];
        Map<Integer, Long> cache = new HashMap<>();
        for (Integer item : unlimited) {
            if (item == null) {
                continue;
            }
            cache.put(item, 0L);
        }
        for (int i = 1; i <= n; i++) {
            int currentIndex = i - 1;
            if (cache.containsKey(i)) {
                result[currentIndex] = cache.get(i);
                continue;
            }
            if (mixing.get(i) == null) {
                long val = costs.get(i);
                result[currentIndex] = val;
                cache.put(i, val);
                continue;
            }
            long cost = getCost(i, unlimited, costs, mixing, cache);
            result[currentIndex] = cost;
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