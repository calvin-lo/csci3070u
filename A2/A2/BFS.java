import java.io.*;
import java.util.*;


public class BFS {
	int[] d;
	String[] p;
	List<String> V;
	List<List<String>> E;
	int s_index;
	int v_index;
	int u_index;
	private int infinity = Integer.MAX_VALUE;

	public BFS(Graph G, String s) {
		d = new int[G.V.size()];
		p = new String[G.V.size()];
		bfs(G,s);
	}

	public void bfs (Graph G, String s)  {
		V = G.V;
		E = G.E;
		s_index = V.indexOf(s);

		for (int u = 0; u < V.size(); u++)
			d[u] = infinity;
		d[s_index] = 0;

		myQueue<String> Q = new myQueue<String>();
		Q.enqueue(s);

		while (!Q.isEmpty())  {
			String u = Q.dequeue();
			for (String v : G.Adj(u))  {
				v_index = V.indexOf(v);
				u_index = V.indexOf(u);
				if (d[v_index] == infinity)  {
					d[v_index] = d[u_index] + 1;
					p[v_index] = u;
					Q.enqueue(v);
				}
			}
		}

	}
}