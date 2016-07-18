/**
 * Hanisha Ravuri
 * CS 246
 * Prove 02 Collections and Interfaces
 *
 * This program consist of two classes, Finder
 * and Topic. This program reads in a list of topics, reads in
 * several files, and then finds which topics are in each file.
 */

import java.util.List;
import java.util.Set;
import java.io.File;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.Properties;

/**
 * CLASS FINDER
 * This classs contains the main method and works with the Topic
 * class. It reads in the files and displays the topics from them. 
 */
public class Finder {
   /**
    * MAIN
    * This is the where the program starts. The file names are
    * passed in and the run method is called.
    */
   public static void main(String[] args) throws Exception {
      String propsFileName = args[0];
      Finder finder = new Finder();
      String props[] = finder.getPropValues(propsFileName);
      finder.run(props[0], props[1]);
   }

   /**
    * GET PROPS VALUES
    * This method gets the content that is contained in the proporties
    * file. It returns the content in an array.
    */
   public String[] getPropValues (String propFileName) throws Exception {
      String[] result;
      result = new String[256];
      Properties prop = new Properties();

      InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
      if (inputStream != null) {
         prop.load(inputStream);
      } else {
         throw new FileNotFoundException("property file '" + propFileName
                                         + "' not found in the classpath");
      }

      // Sets properties
      result[0] = prop.getProperty("file");
      result[1] = prop.getProperty("directory");

      return result;
   }

   /**
    * RUN
    * From this Method the program is ran. It calls the methods needed
    * to read in the files and print out the topics.
    */
   public void run(String fileName, String dirPath) throws Exception {
      List <Topic> topics = readTopics(fileName);
      Map <String, Set<String>> topicsFound = readText(dirPath, topics);
      displayList(topicsFound);
   }

   /** 
    * List
    * This method reads in the topic file and sets the topics and their
    * key words.
    */
   public List <Topic> readTopics(String file) {
      // List to store Topics in 
      List <Topic> topics = new ArrayList<Topic>();

      //reads in file
      try{
         BufferedReader reader = new BufferedReader(new FileReader(file));
         String line;

         //takes one line at a time and sets the topic from it
         while ((line = reader.readLine()) != null) {
            Topic topic = new Topic();
            topic.SetTopic(line);
            topics.add(topic);
         }
         reader.close();        
      }catch(Exception e){
         System.out.println("\nOpening file '" + file + "'...\n");
         System.err.println("Error reading '" + file +"'...\n");
      }
      return topics;
   }

   /**
    * READ TEXT
    * This Method reads in the text that will be searched for it's topics.
    * It stores the topics that each text file contians in a Set. It then     
    * stores each Set in a Map using the file name as the key.  
    */
   public Map <String, Set<String>> readText(String dirPath, List <Topic> topics) {
      // Creates Map to store Sets in 
      Map <String, Set<String>> map = new TreeMap<String, Set<String>>();

      // For getting text file names
      String target_dir = dirPath;
      File dir = new File(target_dir);
      File[] files = dir.listFiles();

      String fileName;

      // Reads each file in
      for (File f : files) {
         fileName = f.getName();
         String file = "";

         // Opens file
         try{
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line;

            // Combindes the entire file into one string
            while ((line = reader.readLine()) != null) {
               file = file + line;   
            }
            reader.close();
         }catch(Exception e){
            System.err.println("Error reading file\n");
         }

         // Makes a Set to store the found topics in 
         Set <String> topicsFound = new TreeSet<String>();
         // Checks the file for each topic
         for(int i = 0; i < topics.size(); i++) {      
            String topic = topics.get(i).GetTopicName();
            // Checks the file for each keyWord
            for (int j = 0; j < topics.get(i).GetKeyWords().size(); j++){
               if (file.contains(topics.get(i).GetKeyWords().get(j) )) 
                  topicsFound.add(topics.get(i).GetTopicName());                
            }
         }
         // Puts the Set into the Map
         map.put(fileName, topicsFound);           
      }
      return map;
   }

   /**
    * DISPLAY LIST
    * This method displays the Map. It displays the name of each
    * text file and then the topics that were found in it.
    */
   public void displayList(Map<String, Set<String>> map) {

      for(Map.Entry<String, Set<String>> entry : map.entrySet()) {
         String key = entry.getKey();
         Set <String> set = entry.getValue();
         // Prints out the file name
         System.out.println(key);
         // Prints out the topics
         for(String s : set)
               System.out.println("\t" + s);
         System.out.println();
      }
   } 
}