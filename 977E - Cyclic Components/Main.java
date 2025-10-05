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

  static List<List<Integer>> l;
  static Set<Integer> visited;
  static List<List<Integer>> comps;
  static Set<Integer> compIds;

  static void dfs(int curr, int compId) {
    comps.get(compId).add(curr);
    visited.add(curr);
    for (int nei : l.get(curr)) {
      if (visited.contains(nei)) {
        continue;
      }
      dfs(nei, compId);
    }
  }

  static boolean dfsCheck(int curr, int compId) {
    visited.add(curr);
    List<Integer> adj = l.get(curr);
    if (adj.size() != 2) {
      return false;
    }
    for (int nei : adj) {
      if (visited.contains(nei)) {
        continue;
      }
      if (!dfsCheck(nei, compId)) {
        return false;
      }
    }
    return true;
  }

  static void solve(FastReader in, PrintWriter out) throws IOException {
    int n = in.nextInt();
    int m = in.nextInt();
    int[][] edges = new int[m][2];
    for (int i = 0; i < m; i++) {
      edges[i][0] = in.nextInt();
      edges[i][1] = in.nextInt();
    }
    l = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      l.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      l.get(u).add(v);
      l.get(v).add(u);
    }
    visited = new HashSet<>();
    comps = new ArrayList<>();
    comps.add(null);
    compIds = new HashSet<>();
    int compId = 1;
    for (int i = 1; i <= n; i++) {
      if (visited.contains(i)) {
        continue;
      }
      comps.add(new ArrayList<>());
      dfs(i, compId);
      compIds.add(compId);
      compId++;
    }
    visited = new HashSet<>();
    int total = compIds.size();
    for (int i = 1; i <= total; i++) {
      if (!dfsCheck(comps.get(i).get(0), i)) {
        compIds.remove(i);
      }
    }
    System.out.println(compIds.size());
  }

}
