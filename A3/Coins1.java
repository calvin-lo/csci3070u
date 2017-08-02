
import java.util.Scanner;

public class Coins1
{
static int[] result;

static int find_min(int x){
    int[] den = {1,5,10,25,100};
int[] deno_temp_min = new int[5];
int[] deno_final_min = new int[5];
int min;

if (x == 0)
  return(0);

for (int i = 0; i < den.length; i++)
  deno_final_min[i] = -1;


for (int i = 0; i < den.length; i++)  {
    if (den[i] <= x)  {
        if (result[x-den[i]] == 0) {
            deno_temp_min[i] = find_min(x-den[i]);
            result[x-den[i]] = deno_temp_min[i];
        }
        else {
            deno_temp_min[i] = result[x-den[i]];
        }
        deno_final_min[i] = deno_temp_min[i] + 1;
    }
}

//find min
min = -1;
for (int i = 0; i < den.length; i++)  {
  if (deno_final_min[i] >= 0)  {
  if (min == -1 || deno_final_min[i] < min)  {
  min = deno_final_min[i];
}
}
}

return(min);
}


   public static void main(String[] args)
   {
       int C, r;

       Scanner in = new Scanner( System.in );
       
       while ( true )
       {
	         System.out.print(" Change amount = ");
          C = in.nextInt();

          result = new int[C + 1];
          result[0] = 0;

          r = find_min(C);

          System.out.println("Min # coins to make change for " + C + " = " + r);
          System.out.println("=======================\n");
       }
   }

}
