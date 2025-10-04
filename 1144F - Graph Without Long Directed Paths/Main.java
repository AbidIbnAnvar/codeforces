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

  static List<List<Integer>> adj;
  static Set<Integer> visited;
  static int[] colors;

  static boolean dfs(int curr, int c) {
    colors[curr] = c;
    for (int nei : adj.get(curr)) {
      if (colors[nei] == -1) {
        if (!dfs(nei, 1 - c))
          return false;
      } else if (colors[nei] == c) {
        return false;
      }
    }
    return true;
  }

  static void solve(FastReader in, PrintWriter out) throws IOException {
    int n = in.nextInt();
    int m = in.nextInt();
    int[][] edges = new int[m][2];
    adj = new ArrayList<>();
    colors = new int[n + 1];
    visited = new HashSet<>();
    for (int i = 0; i <= n; i++) {
      adj.add(new ArrayList<>());
    }

    for (int i = 0; i < m; i++) {
      edges[i][0] = in.nextInt();
      edges[i][1] = in.nextInt();
      adj.get(edges[i][0]).add(edges[i][1]);
      adj.get(edges[i][1]).add(edges[i][0]);
    }
    Arrays.fill(colors, -1);
    if (!dfs(1, 0)) {
      System.out.println("NO");
      return;
    }
    System.out.println("YES");
    for (int i = 0; i < m; i++) {
      int u = edges[i][0], v = edges[i][1];
      if (colors[u] == 0 && colors[v] == 1)
        System.out.print("1");
      else
        System.out.print("0");
    }
  }
}
