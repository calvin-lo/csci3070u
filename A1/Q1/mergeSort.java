import java.util.*;
import java.*;
import java.io.*;

public class mergeSort
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
        mergeSort(strArray);

		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(new File("mergeSort_Results.txt"), true));
                writer.println(totalTime);
            writer.close();
        } catch (Exception e)
        {
            System.out.println("Error.");
        }
		//System.out.println("mergeSort: " + totalTime);

	}

	// Merge Sort
	public static void mergeSort(String[] strArray) {
        if (strArray.length >= 2) {
            String[] left = new String[strArray.length / 2];
            String[] right = new String[strArray.length - strArray.length / 2];

            for (int i = 0; i < left.length; i++) {
                left[i] = strArray[i];
            }

            for (int i = 0; i < right.length; i++) {
                right[i] = strArray[i + strArray.length / 2];
            }

            mergeSort(left);
            mergeSort(right);
            merge(strArray, left, right);
        }
    }

    public static void merge(String[] strArray, String[] left, String[] right) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < strArray.length; i++) {
            if (b >= right.length || (a < left.length && left[a].compareToIgnoreCase(right[b]) < 0)) {
                strArray[i] = left[a];
                a++;
            } else {
                strArray[i] = right[b];
                b++;
            }
        }
    }
    
}