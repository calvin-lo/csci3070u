
import java.util.Scanner;


public class Coins1_dp
{
    /* =============================================================
       make_M(v, result): make the result[] array

	   v[] = values of each type of coin
	   result[i] = min # coins used to make change for $i

       Return: the value result[x] (which is the min # coins used 
		to make change for $x
       ============================================================= */
static int find_min(int x)  {
	int[] deno = {1,5,10,25,100};
	int[] result;
	int[] deno_temp_min, deno_final_min;
	int min;

	result = new int [x + 1];
	deno_temp_min = new int[deno.length];
	deno_final_min = new int[deno.length];

	result[0] = 0;

	for (int i = 1; i <= x; i++ )  {
		for (int j = 0; j < deno.length; j++ )
				deno_final_min[j] = -1;


		for (int j = 0; j < deno.length; j++)  {
			if (deno[j] <= i)
				deno_final_min[j] = result[i-deno[j]] + 1;
		}

		result[i] = -1;

		for (int j = 0; j < deno.length; j++ )
		{
			if (deno_final_min[j] >= 0 )  {
				if (result[i] == -1 || deno_final_min[j] < result[i]) {
		   			result[i] = deno_final_min[j];
				}
			}
		}
	}

	return(result[x]);
}


   public static void main(String[] args)
   {
//       int[] v = {1, 5, 10, 25, 50, 100, 500};
       int[] v = {1, 5, 10, 25, 100 };

       int x, r;

       Scanner in = new Scanner( System.in );

       System.out.print("Values of coins: ");
       for ( int i = 0; i < 5; i++ )
       {
           System.out.print(v[i] + ", ");
       }
       System.out.println("\n");

       while ( true )
       {
          System.out.print(" Change amount = ");
          x = in.nextInt();

          r = find_min(x);

/*
          for (int i = 0; i <= x; i ++)
             System.out.println("result[" + i + "] = " + result[i]);
*/

          System.out.println("Min # coins to make change for " + x + "= " + r);          System.out.println("=======================\n");
       }
   }
}
