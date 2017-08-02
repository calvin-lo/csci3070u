import java.io.*;
import java.util.*;

// Reference:   http://www.ashishsharma.me/2011/08/external-merge-sort.html
//              https://en.wikipedia.org/wiki/External_sorting
//              http://www.params.me/2012/01/n-way-merge-algorithm-in-java.html
//              http://stackoverflow.com/questions/8402106/how-to-speed-up-external-merge-sort-in-java
//              http://www.sanfoundry.com/java-program-k-way-merge-algorithm/


public class sorted {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        ArrayList<File> files = new ArrayList<File>();
    	int chunk_size = 1024 * 1024 * 1024;
    	
	    File file = new File("data.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        ArrayList<String> strArrayList =  new ArrayList<String>();   
        String line = "";
        while(line != null) {
            int currentchunksize = 0;
            while (((line = reader.readLine()) != null) && (currentchunksize < chunk_size))  {
	        	strArrayList.add(line);
                currentchunksize += line.length();
	    	}

            String[] strArray =  strArrayList.toArray(new String[strArrayList.size()]);
            quickSort(strArray,0,strArray.length-1);

            File temp_file = File.createTempFile("reading", "flatfile");
            temp_file.deleteOnExit();
            PrintWriter writer = new PrintWriter(new FileWriter(temp_file));
          
           for(String s : strArray) {
              writer.write(s + "\n");
            }

            writer.close();

            files.add(temp_file);
            strArrayList.clear();
        }

    	reader.close();

        ArrayList<String> sorted = merge(files);

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        PrintWriter sorted_writer = new PrintWriter(new FileWriter("sorted.txt"));
        
        sorted_writer.println("Runtime: " + totalTime + "s.");

        for (String s : sorted)
            sorted_writer.println(s);


    }

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


    public static ArrayList<String> merge(List<File> files) throws IOException {
        ArrayList<String> sorted = new ArrayList<String>();

        final Comparator<String> cmp = new Comparator<String>() {
            public int compare(String s1, String s2){
                return s1.compareTo(s2);
            }
        };
        final Comparator<cusBuffer> bfcmp = new Comparator<cusBuffer>() {
            public int compare(cusBuffer i, cusBuffer j) {
                return cmp.compare(i.peek(), j.peek());
            }
        };

        PriorityQueue<cusBuffer> pq = new PriorityQueue<cusBuffer>(files.size(), bfcmp);
  
        for (File file : files) {
            cusBuffer buffer = new cusBuffer(file);
            pq.add(buffer);
        }

        while(pq.size() > 0) {
            cusBuffer buffer = pq.poll();
            String s = buffer.pop();
            sorted.add(s);

            if(buffer.empty()) {
                buffer.reader.close();
                buffer.originalfile.delete();
            } else {
                pq.add(buffer);
            }
        }

        for(cusBuffer b : pq ) 
            b.close();

        return sorted;
    }
    static class cusBuffer  {
        public BufferedReader reader;
        public File originalfile;
        private String line;
        private boolean empty;
         
        public cusBuffer(File file) throws IOException {
            originalfile = file;
            reader = new BufferedReader(new FileReader(file));
            read();
        }
         
        public boolean empty() {
            return empty;
        }
         
        private void read() throws IOException {
          if((this.line = reader.readLine()) != null){
            empty = false;
          }
          else{
            this.line = null;
            empty = true;
          }
        }
         
        public void close() throws IOException {
            reader.close();
        }
         
         
        public String peek() {
            if(empty() == true) 
                return null;

            return this.line;
        }

        public String pop() throws IOException {
            String temp = peek();
                read();

            return temp;
        }
         
    }
}