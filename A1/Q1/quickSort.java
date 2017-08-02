import java.util.*;
import java.*;
import java.io.*;

public class quickSort
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
        quickSort(strArray,0,strArray.length-1);

		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(new File("quickSort_Results.txt"), true));
                writer.println(totalTime);
            writer.close();
        } catch (Exception e)
        {
            System.out.println("Error.");
        }
	}

    // Quick Sort
    private static void quickSort(String[] strArray, int start, int end)
	{
        int i = start;
        int j = end;

        if (j - i >= 1)
        {
            String pivot = strArray[i];
            while (j > i)
            {
                while (strArray[i].compareTo(pivot) <= 0 && i < end && j > i){
                    i++;
                }
                while (strArray[j].compareTo(pivot) >= 0 && j >= start && j >= i){
                    j--;
                }
                if (j > i)
                    swap(strArray, i, j);
            }
            swap(strArray, start, j);
            quickSort(strArray, start, j - 1);
            quickSort(strArray, j + 1, end);
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