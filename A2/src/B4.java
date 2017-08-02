import java.io.*;
import java.util.*;

public class B4 {

	int[] num_vertices;
	int[] radius;


	public B4(Map<Integer, List<String>> com, Graph G) {
		num_vertices = new int[com.size()];
		radius = new int[com.size()];
		b4(com, G);
		
	}

	public void b4(Map<Integer, List<String>> com, Graph G) {
		for (int i = 0; i < com.size(); i++)  {
			num_vertices[i] = com.get(i).size();
		}

		for (int i = 0; i < com.size(); i++)  {
			int[] d;
			int[] ecc = new int[com.get(i).size()];
			int min = 0;
			int max = 0;
			B1 bfs = null;
			for (String s : com.get(i))  {
				bfs = new B1(G,s);
				
				d = bfs.d;
				if (bfs.p[0] != null)
					max = d[0];
				for (int k = 0; k < d.length; k++)  {
					if (bfs.p[k] != null) {
						if (d[k] > max){
							max = d[k];
						}
					}
				}
				ecc[com.get(i).indexOf(s)] = max;
			}
			//System.out.println("max: " + max);
			min = ecc[0];
			for (int n = 0; n < ecc.length; n++)  {
				if (ecc[n] < min)
					min = ecc[n];
			}

			radius[i] = min;
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

		B3 conn_comp = new B3(G);
		int n = conn_comp.time;

		System.out.println(n);

		Map<Integer, List<String>> com = new HashMap<Integer, List<String>>();
		for (int i = 0; i < n; i++)
			com.put(i, new ArrayList<String>());
		for (int i = 0 ; i < G.V.size(); i++)
			com.get(conn_comp.k[i]).add(G.V.get(i));


		B4 b4 = new B4(com, G);
		for (int i = 0; i < com.size(); i++)  {
			System.out.println(i + "	" + b4.num_vertices[i] + "	" + b4.radius[i]);
		}

	}
}
