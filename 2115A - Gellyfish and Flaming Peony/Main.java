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
            System.out.println(noOfSteps(n, arr));
        }
    }

    static int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    static int noOfSteps(int n, int[] arr) {
        int g = arr[0];
        for (int i = 1; i < n; i++) {
            g = gcd(g, arr[i]);
        }

        int diff = 0;
        boolean isPresent = false;
        for (int i = 0; i < n; i++) {
            if (arr[i] != g) {
                diff++;
            } else {
                isPresent = true;
            }
        }
        if (diff == 0)
            return 0;
        if (isPresent)
            return diff;

        Map<Integer, Integer> best = new HashMap<>();
        for (int a : arr) {
            Map<Integer, Integer> next = new HashMap<>();
            next.put(a, 1);

            for (Map.Entry<Integer, Integer> e : best.entrySet()) {
                int key = e.getKey();
                int val = e.getValue();
                int common = gcd(key, a);
                if (!next.containsKey(common) || next.get(common) > val + 1) {
                    next.put(common, val + 1);
                }
            }

            for (Map.Entry<Integer, Integer> e : next.entrySet()) {
                int key = e.getKey();
                int val = e.getValue();
                if (!best.containsKey(key) || best.get(key) > val) {
                    best.put(key, val);
                }
            }
        }
        int l = best.get(g);
        return (l - 1) + (diff - 1);
    }

}