import java.io.*;
import java.util.*;


public class B2 {
	boolean[] visited;
	int time;
	int d[];
	int f[];
	String color[];
	String pi[];
	List<String> V;
	List<List<String>> E;
	private int infinity = Integer.MAX_VALUE;

	public B2(Graph G, String s) {
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

		public static void main (String[] args) throws IOException {
		File file = new File("instructor-pair.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "";
 		
		List<String> str1 =  new ArrayList<String>();
 		while (((line = reader.readLine()) != null))  {
	        	str1.add(line);
	    }

		List<String> str2 =  new ArrayList<String>();
	    Set<String> temp = new HashSet<String>();
	    List<String> V = new ArrayList<String>();
	    
	    for (String s : str1) {
	    	String[] parts = s.split("\\s{3,}");
	    	for (String p : parts)  {
				str2.add(p);
			}
	    }
      
	    for (String s : str2)  {
			String[] parts = s.split("\\s{2,}");
	    	for (String p : parts)  {
				V.add(p);
			}
		}

		List<String> pair = V;

		V = new ArrayList<String>(new LinkedHashSet<String>(V));

	   	Graph G = new Graph(V.size());

	   	for (int i = 0; i < V.size(); i++)
	   		G.addV(V.get(i));

	   	G.sortV();

	   	for (int i = 0; i < pair.size(); i = i + 2)  {
			G.addE(pair.get(i), pair.get(i+1));
		}

		String s = "Ken Pu";
		B2 dfs = new B2(G,s);

		for (int v = 0; v < G.V.size(); v++)  {
			if (dfs.visited[v])
				System.out.println(G.V.get(v));
		}
	}
}