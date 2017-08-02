import java.io.*;
import java.util.*;

public class B3 {
	boolean[] visited;
	int[] k;
	int[] size;
	int time;

	public B3(Graph G)  {
		visited = new boolean[G.V.size()];
		k = new int[G.V.size()];
		size = new int[G.V.size()];
		for (int v = 0; v < G.V.size(); v++)  {
			if (!visited[v])  {
				dfs(G,G.V.get(v));
				time++;
			}
		}
	}

	public void dfs (Graph G, String s)  {

		k[G.V.indexOf(s)] = time;
		size[time] ++;
		visited[G.V.indexOf(s)] = true;
		for (String w : G.Adj(s))  {
			if (!visited[G.V.indexOf(w)])
				dfs(G,w);
		}
	}

	public boolean connected (Graph G, String v1, String v2) {
		return k[G.V.indexOf(v1)] == k[G.V.indexOf(v1)];
	}


}