//could say import java.io.*
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/*******************************************************
 * CLASS WORDCOUNTER
 *      This class reads in a file and displays the number
 *      of words on each line followed by the contents of
 *      each line.
 *******************************************************/
public class WordCounter {
   public static void main(String[] args)throws Exception {

//      int s[] = new int[7];
      String fileName = args[0];      
      InputStream is = null; 
      InputStreamReader isr = null;
      BufferedReader br = null;

      try{
         // open input stream test.txt for reading purpose.
         is = new FileInputStream(fileName);
         System.out.println("\nOpening file '" + fileName + "'...\n");
         
         // create new input stream reader
         isr = new InputStreamReader(is);
         
         // create new buffered reader
         br = new BufferedReader(isr);
      
         String thisLine;
         
         // reads to the end of the stream 
         while((thisLine = br.readLine()) != null) {
            //gets number of words per line
            String[] words = thisLine.split("\\s+");
            System.out.println(words.length + ": " + thisLine );
         }
         
      }catch(Exception e){
         System.out.println("\nOpening file '" + fileName + "'...\n");
         System.err.println("Error reading '" + fileName +"'...\n");
      }finally{
         
         // releases resources associated with the streams
         if(is != null)
            is.close();
         if(isr != null)
            isr.close();
         if(br != null)
            br.close();
      }
   }
}