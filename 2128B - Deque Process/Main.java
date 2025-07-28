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
        String[] result = new String[t];
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = in.nextInt();
            }
            result[i] = dequeProcess(n, arr);
        }
        for (String res : result) {
            out.println(res);
        }
    }

    static String dequeProcess(int n, int[] arr) {
        int left;
        int right = n - 1;
        StringBuilder sb = new StringBuilder();
        sb.append("L");
        left = 1;
        int prev = arr[0];
        while (left <= right) {
            if (left == right) {
                sb.append("L");
                left++;
                break;
            }
            if (arr[left] < prev && arr[right] < prev && arr[left] < arr[right]) {
                sb.append("LR");
                prev = arr[right];
                left++;
                right--;
            } else if (arr[left] < prev && arr[right] < prev && arr[left] > arr[right]) {
                sb.append("RL");
                prev = arr[left];
                right--;
                left++;
            } else if ((arr[left] > prev && arr[right] < prev) || (arr[left] < prev && arr[right] > prev)) {
                sb.append("LR");
                prev = arr[right];
                left++;
                right--;
            } else if (arr[left] > prev && arr[right] > prev && arr[left] < arr[right]) {
                sb.append("RL");
                prev = arr[left];
                left++;
                right--;
            } else if (arr[left] > prev && arr[right] > prev && arr[left] > arr[right]) {
                sb.append("LR");
                prev = arr[right];
                left++;
                right--;
            }
        }

        return sb.toString();
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