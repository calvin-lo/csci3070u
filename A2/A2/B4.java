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
			BFS bfs = null;
			for (String s : com.get(i))  {
				bfs = new BFS(G,s);
				
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
}
