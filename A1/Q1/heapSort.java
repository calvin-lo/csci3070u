import java.util.*;
import java.*;
import java.io.*;
public class heapSort
{
	public static void main(String[] args)  {
    		long startTime = System.currentTimeMillis();
    		String text = "";
    		String possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    		String[] strArray = new String[1000000];
    		for (int i = 0; i < 1000000; i++)  {
    			text = "";
    			for (int j = 0; j < 100; j++)  {
    				text += possible.charAt((int)(Math.floor(Math.random() * possible.length())));
    				strArray[i] = text;
    			}
    		}
    		heapSort(strArray,strArray.length -1);

    		long endTime   = System.currentTimeMillis();
    		long totalTime = endTime - startTime;
            try {
                PrintWriter writer = new PrintWriter(new FileOutputStream(new File("heapSort_Results.txt"), true));
                    writer.println(totalTime);
                writer.close();
            } catch (Exception e)
            {
                System.out.println("Error.");
            }
	}

    // Heap Sort
    public static void buildheap(String []strArray, int n)  {
        for(int i = n/2 ; i >= 0; i--) {
            maxheap(strArray, i, n);
        }
    }
    public static void maxheap(String []strArray, int i, int n)  { 
    	int largest;
    	int left= 2*i;
    	int right= 2*i+1;
    	if(left <= n && strArray[left].compareTo(strArray[i]) > 0)  {
       		largest=left;
    	}
    	else  {
        	largest=i;
    	}
    
    	if(right <= n && strArray[right].compareTo(strArray[largest]) > 0) {
        	largest=right;
    	}
    	if(largest != i){ 
        	swap(strArray, i, largest);
        	maxheap(strArray, largest, n);
    	}
	}
    
    public static void heapSort(String[] strArray, int n){
        buildheap(strArray, n);
        
        for(int i=n ;i>0; i--){
            swap(strArray, 0, i);
            n = n-1;
            maxheap(strArray, 0, n);
        }
    }

    // swap
    private static void swap(String[] strArray, int i, int j)
    {
    	String temp = strArray[i];
    	strArray[i] = strArray[j];
    	strArray[j] = temp;
    }
    
}