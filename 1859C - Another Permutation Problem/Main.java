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
            result[i] = anotherPermutationProblem(n);
        }
        for (int res : result) {
            out.println(res);
        }
    }

    static void printArray(int[] arr) {
        for (int a : arr) {
            System.out.print(a + ",");
        }
        System.out.println();
    }

    static int calculateCost(int n, int[] arr) {
        int cost = 0;
        int max = 0;
        for (int i = 1; i <= n; i++) {
            int val = arr[i] * i;
            max = Math.max(max, val);
            cost += val;
        }
        cost -= max;
        return cost;
    }

    static int getPermutations(int n, int[] arr, int i) {
        int cost = calculateCost(n, arr);
        for (int j = n; j >= 2; j--) {
            int temp = arr[j];
            arr[j] = arr[j - 1];
            arr[j - 1] = temp;
            for (int k = j; k < n; k++) {
                temp = arr[k];
                arr[k] = arr[k + 1];
                arr[k + 1] = temp;
            }
            cost = Math.max(cost, calculateCost(n, arr));
        }
        return cost;
    }

    static int anotherPermutationProblem(int n) {
        int[] permutation = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            permutation[i] = i;
        }
        return getPermutations(n, permutation, 1);
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