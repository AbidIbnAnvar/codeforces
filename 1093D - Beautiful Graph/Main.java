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

  static final int MOD = 998_244_353;

  static void solve(FastReader in, PrintWriter out) throws IOException {
    int t = in.nextInt();
    while (t-- > 0) {
      int n = in.nextInt();
      int m = in.nextInt();
      int[][] arr = new int[m][2];
      for (int i = 0; i < m; i++) {
        arr[i][0] = in.nextInt();
        arr[i][1] = in.nextInt();
      }
      System.out.println(beautifulGraph(n, m, arr));
    }
  }

  static Set<Integer> visited;
  static int[] val; // ids of components
  static Map<Integer, List<Integer>> comps;
  static List<List<Integer>> l;

  static boolean dfs(int curr, int currId, int c) {
    visited.add(curr);
    comps.computeIfAbsent(currId, k -> new ArrayList<>()).add(curr);
    val[curr] = c;
    for (int nei : l.get(curr)) {
      if (visited.contains(nei)) {
        if (val[nei] == c) {
          return false;
        }
        continue;
      }
      if (!dfs(nei, currId, 1 - c)) {
        return false;
      }
    }
    return true;
  }

  static long modPow(long base, long exp, long mod) {
    long res = 1;
    while (exp > 0) {
      if ((exp & 1) == 1)
        res = (res * base) % mod;
      base = (base * base) % mod;
      exp >>= 1;
    }
    return res;
  }

  static int beautifulGraph(int n, int m, int[][] arr) {
    visited = new HashSet<>();
    val = new int[n + 1];
    comps = new HashMap<>();
    l = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      l.add(new ArrayList<>());
    }
    for (int i = 0; i < m; i++) {
      l.get(arr[i][0]).add(arr[i][1]);
      l.get(arr[i][1]).add(arr[i][0]);
    }
    int currCompId = 1;
    for (int i = 1; i <= n; i++) {
      if (visited.contains(i)) {
        continue;
      }
      if (!dfs(i, currCompId++, 0)) {
        return 0;
      }
    }
    Long ans = null;
    for (Integer comp : comps.keySet()) {
      List<Integer> ele = comps.get(comp);
      int count0 = 0;
      int count1 = 0;
      for (int e : ele) {
        if (val[e] == 0) {
          count0++;
        } else {
          count1++;
        }
      }
      long result = (long) ((modPow(2, count0, MOD) + modPow(2, count1, MOD)) % MOD);
      if (ans == null) {
        ans = result;
      } else {
        ans = (ans * result) % MOD;
      }
    }
    return (int) (ans % MOD);
  }

}
