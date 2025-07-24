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
        int[] lengths = new int[t];
        String[] strings = new String[t];
        for (int i = 0; i < t; i++) {
            lengths[i] = in.nextInt();
            strings[i] = in.nextLine();
        }
        int[] result = distinctSplit(t, lengths, strings);
        for (int item : result) {
            out.println(item);
        }
    }

    static int getMaximumSplit(int n, String s) {
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        Set<Character> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!set.contains(s.charAt(i))) {
                prefix[i] = ++count;
                set.add(s.charAt(i));
            } else {
                prefix[i] = count;
            }
        }
        set.clear();
        count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (!set.contains(s.charAt(i))) {
                suffix[i] = ++count;
                set.add(s.charAt(i));
            } else {
                suffix[i] = count;
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            int sum = prefix[i - 1] + suffix[i];
            max = Math.max(sum, max);
        }
        return max;
    }

    static int[] distinctSplit(int t, int[] lengths, String[] strings) {
        int[] result = new int[t];
        for (int i = 0; i < t; i++) {
            result[i] = getMaximumSplit(lengths[i], strings[i]);
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