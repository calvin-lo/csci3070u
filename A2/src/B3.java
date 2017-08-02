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

		B3 conn_comp = new B3(G);
		int n = conn_comp.time;

		System.out.println(n);

		Map<Integer, List<String>> com = new HashMap<Integer, List<String>>();
		for (int i = 0; i < n; i++)
			com.put(i, new ArrayList<String>());
		for (int i = 0 ; i < G.V.size(); i++)
			com.get(conn_comp.k[i]).add(G.V.get(i));

		for (int i  = 0; i < n; i++)  {
			for (String v : com.get(i))
				System.out.print(v + " ");
			System.out.println();
			System.out.println();
		}
	}
}