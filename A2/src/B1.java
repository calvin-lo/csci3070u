import java.io.*;
import java.util.*;


public class B1 {
	int[] d;
	String[] p;
	List<String> V;
	List<List<String>> E;
	int s_index;
	int v_index;
	int u_index;
	private int infinity = Integer.MAX_VALUE;

	public B1(Graph G, String s) {
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
		B1 bfs = new B1(G,s);

		for (int v = 0 ; v < G.V.size(); v++)  {
			if (bfs.p[v] != null)
				System.out.println("Distance from \"" + s + "\" to \"" + G.V.get(v) + "\" = " + bfs.d[v]);
		}
	}
}