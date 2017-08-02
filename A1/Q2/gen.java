import java.util.*;
import java.*;
import java.io.*;

public class gen
{
	public static void main(String[] args)  {
		String text = "";
		String possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String[] strArray = new String[100000000];
		for (int i = 0; i < 100000000 ; i++)  {
			text = "";
			for (int j = 0; j < 100; j++)  {
				text += possible.charAt((int)(Math.floor(Math.random() * possible.length())));
				strArray[i] = text;
			}
		}
		try {
			PrintWriter writer = new PrintWriter(new FileOutputStream(new File("data.txt"), true));
			for (String s : strArray)
				writer.println(s);
			writer.close();
		} catch (Exception e)
		{
			System.out.println("Error.");
		}
	}

}