import java.io.*;
import java.util.*;


public class DFS {
	boolean[] visited;
	int time;
	int d[];
	int f[];
	String color[];
	String pi[];
	List<String> V;
	List<List<String>> E;
	private int infinity = Integer.MAX_VALUE;

	public DFS(Graph G, String s) {
		V = G.V;
		E = G.E;
		visited = new boolean[G.V.size()];
		color = new String[G.V.size()];
		d = new int[G.V.size()];
		f = new int[G.V.size()];
		pi = new String[G.V.size()];
		dfs(G,s);
	}

	public void dfs (Graph G, String s)  {

		time++;
		visited[G.V.indexOf(s)] = true;
		for (String w : G.Adj(s))  {
			d[G.V.indexOf(w)] = time;
			if (!visited[G.V.indexOf(w)])
				dfs(G,w);
		}

		/*for (String u : G.V)  {
			color[G.V.indexOf(u)] = "WHITE";
			pi[G.V.indexOf(u)] = null;
		}
		time = 0;
		for (String u : G.V) {
			if (color[G.V.indexOf(u)].equals("WHITE"))
				dfs_visit(G, u);
		}*/
	}

	public void dfs_visit(Graph G, String u)  {
		int u_index = G.V.indexOf(u);
		color[u_index] = "GRAY";
		time ++;
		d[u_index] = time;
		for (String v : G.Adj(u))  {
			int v_index = G.V.indexOf(v);
			if (color[v_index].equals("WHITE"))  {
				pi[v_index] = u;
				dfs_visit(G,v);
			}
		}
		color[u_index] = "BLACK";
		time ++;
		f[u_index] = time;
	}
}