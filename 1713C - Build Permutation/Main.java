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
        int[] arr = new int[t];
        for (int i = 0; i < t; i++) {
            arr[i] = in.nextInt();
            int[] result = buildPermutation(arr[i]);
            if (result[0] == -1) {
                out.println(-1);
            } else {
                for (int res : result) {
                    out.print(res + " ");
                }
                out.println();
            }
        }
    }

    static int[] buildPermutation(int n) {
        Set<Integer> used = new HashSet<>();
        int[] ans = new int[n];
        int maxPossible = (n - 1) * 2;
        int maxPossibleSquareRoot = (int) Math.sqrt(maxPossible);
        int temp = maxPossibleSquareRoot;
        int i = n - 1;
        while (maxPossible >= 0 && i >= 0) {
            maxPossibleSquareRoot = temp;
            maxPossible = (int) Math.pow(temp, 2);
            while (maxPossible >= 0 && maxPossible <= (n - 1) * 2) {
                int needed = maxPossible - i;
                boolean isAvailable = used.contains(needed) == false && needed < n && needed >= 0;
                if (isAvailable) {
                    ans[i] = needed;
                    used.add(needed);
                    break;
                } else {
                    maxPossibleSquareRoot--;
                    maxPossible = (int) Math.pow(maxPossibleSquareRoot, 2);
                }
            }
            i--;
        }
        if (i != -1) {
            Arrays.fill(ans, -1);
        }
        return ans;
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