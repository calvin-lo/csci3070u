import java.io.*;
import java.util.*;

public class Graph {
	List<String> V;
	List<List<String>> E;

	public Graph (int size)  {
		V = new ArrayList<String>();
		E = new ArrayList<List<String>>();
		for (int i = 0 ; i < size ; i++)
			E.add(new ArrayList<String>());
	}

	public void addV (String v)  {
		V.add(v);
	}

	public void sortV()  {
		Collections.sort(V);
	}

	public void addE (String v1, String v2)  {
		int i = V.indexOf(v1);
		int j = V.indexOf(v2);

		E.get(i).add(v2);
		E.get(j).add(v1);
	}

	public List<String> Adj(String v)  {
		int index = V.indexOf(v);
		return E.get(index);
	}

}