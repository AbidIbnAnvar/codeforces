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
        int q = in.nextInt();
        int[] result = new int[q];
        for (int i = 0; i < q; i++) {
            int n = in.nextInt();
            Integer[] prices = new Integer[n];
            for (int j = 0; j < n; j++) {
                prices[j] = in.nextInt();
            }
            int x = in.nextInt();
            int a = in.nextInt();
            int y = in.nextInt();
            int b = in.nextInt();
            long k = in.nextLong();
            result[i] = saveTheNature(n, prices, x, a, y, b, k);
        }

        for (int res : result) {
            out.println(res);
        }
    }

    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    static int saveTheNature(int n, Integer[] prices, int x, int a, int y, int b, long K) {
        Arrays.sort(prices, Collections.reverseOrder());
        long[] prefix = new long[n + 1];
        prefix[0] = 0;
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + prices[i - 1];
        }
        int answer = -1;
        long LCM = lcm(a, b);
        int left = 1;
        int right = n;
        if (x < y) {
            int temp = x;
            x = y;
            y = temp;
            temp = a;
            a = b;
            b = temp;
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int bothXY = (int) (mid / LCM);
            int onlyX = mid / a - bothXY;
            int onlyY = mid / b - bothXY;
            long price = 0;
            price += (prefix[bothXY] - prefix[0]) * (x + y) / 100;
            price += (prefix[bothXY + onlyX] - prefix[bothXY]) * (x) / 100;
            price += (prefix[bothXY + onlyX + onlyY] - prefix[bothXY + onlyX]) * (y) / 100;
            if (price >= K) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
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