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
            int k = in.nextInt();
            String binaryString = in.nextLine();
            int[] result = likeTheBitset(n, k, binaryString);
            if (result.length > 0) {
                System.out.println("YES");
                for (int i : result) {
                    System.out.print(i + " ");
                }
                System.out.println();
            } else {
                System.out.println("NO");
            }
        }
    }

    static int[] likeTheBitset(int n, int k, String binaryString) {
        int continuousOnes = 0;
        int current = 1;
        int[] arr = new int[n];
        Set<Integer> filled = new HashSet<>();
        for (int i = 0; i < n; i++) {
            char c = binaryString.charAt(i);
            if (c == '1') {
                continuousOnes++;
                arr[i] = current++;
                filled.add(i);
            } else {
                continuousOnes = 0;
            }
            if (continuousOnes >= k) {
                return new int[0];
            }
        }
        for (int i = 0; i < n; i++) {
            if (!filled.contains(i)) {
                arr[i] = current++;
            }
        }
        return arr;
    }

}
