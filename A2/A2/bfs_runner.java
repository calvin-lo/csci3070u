import java.io.*;
import java.util.*;

public class bfs_runner {
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
		BFS bfs = new BFS(G,s);

		for (int v = 0 ; v < G.V.size(); v++)  {
			//if (bfs.p[v] != null)
				//System.out.println(G.V.get(v));
				//System.out.println("Distance from \"" + s + "\" to \"" + G.V.get(v) + "\" = " + bfs.d[v]);
		}

		//PrintWriter writer = new Princonnected componenttWriter(new File("result.txt"));
		DFS dfs = new DFS(G,s);
		for (int v = 0; v < G.V.size(); v++)  {
			// /if (dfs.visited[v])
				//System.out.println(G.V.get(v) + " " + dfs.d[v]);
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
			//for (String v : com.get(i))
				//System.out.print(v + " ");
			//System.out.println();
			//System.out.println();
		}

		B4 b4 = new B4(com, G);
		for (int i = 0; i < com.size(); i++)  {
			System.out.println(i + "	" + b4.num_vertices[i] + "	" + b4.radius[i]);
		}

	}
}