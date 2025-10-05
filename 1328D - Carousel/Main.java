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

  static int dfs(int curr, int[] col) {
    if (visited.contains(curr)) {
      return col[curr];
    }
    Set<Integer> avail = new HashSet<>();
    avail.add(1);
    avail.add(2);
    avail.add(3);
    for (int nei : adj.get(curr)) {
      if (!visited.contains(nei)) {
        continue;
      }
      avail.remove(col[nei]);
    }
    for (int val : avail) {
      col[curr] = val;
      break;
    }
    visited.add(curr);
    int max = col[curr];
    for (int nei : adj.get(curr)) {
      if (visited.contains(nei)) {
        continue;
      }
      max = Math.max(max, dfs(nei, col));
    }
    return max;
  }

  static void solve(FastReader in, PrintWriter out) throws IOException {
    int q = in.nextInt();
    while (q-- > 0) {
      int n = in.nextInt();
      int[] arr = new int[n + 1];
      for (int i = 1; i <= n; i++) {
        arr[i] = in.nextInt();
      }
      adj = new ArrayList<>();
      for (int i = 0; i <= n; i++) {
        adj.add(new ArrayList<>());
      }
      for (int i = 1; i <= n - 1; i++) {
        if (arr[i] != arr[i + 1]) {
          adj.get(i).add(i + 1);
          adj.get(i + 1).add(i);
        }
      }
      if (arr[1] != arr[n]) {
        adj.get(1).add(n);
        adj.get(n).add(1);
      }
      visited = new HashSet<>();
      int[] col = new int[n + 1];
      int max = 1;
      for (int i = 1; i <= n; i++) {
        if (visited.contains(i)) {
          continue;
        }
        max = Math.max(max, dfs(i, col));
      }
      System.out.println(max);
      for (int i = 1; i <= n; i++) {
        System.out.print(col[i] + " ");
      }
      System.out.println();
    }
  }

}
