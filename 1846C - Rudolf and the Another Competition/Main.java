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
            int m = in.nextInt();
            int h = in.nextInt();
            int[][] arr = new int[n][m];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    arr[j][k] = in.nextInt();
                }
                Arrays.sort(arr[j]);
            }
            result[i] = rudolfAndTheAnotherCompetition(n, m, h, arr);
        }

        for (int res : result) {
            out.println(res);
        }
    }

    static int rudolfAndTheAnotherCompetition(int n, int m, int h, int[][] arr) {
        int rank = 1;
        int rudolphPoints = 0;
        long rudolphPenalty = 0;
        for (int i = 0; i < n; i++) {
            long time = 0;
            long penalty = 0;
            int points = 0;
            int j = 0;
            for (; j < m; j++) {
                if (time + arr[i][j] > h) {
                    break;
                }
                time += arr[i][j];
                penalty += time;
                points += 1;
            }
            if (i != 0) {
                if ((points == rudolphPoints && penalty < rudolphPenalty) || points > rudolphPoints) {
                    rank++;
                }
            } else {
                rudolphPenalty = penalty;
                rudolphPoints = points;
            }
        }
        return rank;
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