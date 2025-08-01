import java.io.*;
import java.util.*;

public class Main {

    public static class Pair<U, V> {
        public U first;
        public V second;

        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
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
        int[] result = new int[t];
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int[] skiing = new int[n];
            @SuppressWarnings("unchecked")
            Pair<Integer, Integer>[] max_skiing = new Pair[3];
            for (int j = 0; j < 3; j++) {
                max_skiing[j] = new Pair<>(-1, -1);
            }
            for (int j = 0; j < n; j++) {
                skiing[j] = in.nextInt();
                if (skiing[j] > max_skiing[0].first) {
                    max_skiing[2] = max_skiing[1];
                    max_skiing[1] = max_skiing[0];
                    max_skiing[0] = new Pair<>(skiing[j], j);
                } else if (skiing[j] > max_skiing[1].first) {
                    max_skiing[2] = max_skiing[1];
                    max_skiing[1] = new Pair<>(skiing[j], j);
                } else if (skiing[j] > max_skiing[2].first) {
                    max_skiing[2].first = skiing[j];
                    max_skiing[2].second = j;
                }
            }
            int[] movie = new int[n];
            @SuppressWarnings("unchecked")
            Pair<Integer, Integer>[] max_movie = new Pair[3];
            for (int j = 0; j < 3; j++) {
                max_movie[j] = new Pair<>(-1, -1);
            }
            for (int j = 0; j < n; j++) {
                movie[j] = in.nextInt();
                if (movie[j] > max_movie[0].first) {
                    max_movie[2] = max_movie[1];
                    max_movie[1] = max_movie[0];
                    max_movie[0] = new Pair<>(movie[j], j);
                } else if (movie[j] > max_movie[1].first) {
                    max_movie[2] = max_movie[1];
                    max_movie[1] = new Pair<>(movie[j], j);
                } else if (movie[j] > max_movie[2].first) {
                    max_movie[2].first = movie[j];
                    max_movie[2].second = j;
                }
            }
            int[] board = new int[n];
            @SuppressWarnings("unchecked")
            Pair<Integer, Integer>[] max_board = new Pair[3];
            for (int j = 0; j < 3; j++) {
                max_board[j] = new Pair<>(-1, -1);
            }
            for (int j = 0; j < n; j++) {
                board[j] = in.nextInt();
                if (board[j] > max_board[0].first) {
                    max_board[2] = max_board[1];
                    max_board[1] = max_board[0];
                    max_board[0] = new Pair<>(board[j], j);
                } else if (board[j] > max_board[1].first) {
                    max_board[2] = max_board[1];
                    max_board[1] = new Pair<>(board[j], j);
                } else if (board[j] > max_board[2].first) {
                    max_board[2].first = board[j];
                    max_board[2].second = j;
                }
            }
            result[i] = threeActivities(n, skiing, movie, board, max_skiing, max_movie, max_board);
        }
        for (int res : result) {
            out.println(res);
        }
    }

    static int threeActivities(int n, int[] skiing, int[] movie, int[] board, Pair<Integer, Integer>[] max_skiing,
            Pair<Integer, Integer>[] max_movie, Pair<Integer, Integer>[] max_board) {
        int ans = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (max_movie[j].second.equals(max_skiing[i].second)) {
                    continue;
                }
                for (int k = 0; k < 3; k++) {
                    if (max_board[k].second.equals(max_movie[j].second)
                            || max_board[k].second.equals(max_skiing[i].second)) {
                        continue;
                    }
                    ans = Math.max(max_skiing[i].first + max_movie[j].first + max_board[k].first, ans);
                }
            }
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