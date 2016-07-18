/**
 * This file contains the Topic Class.
 */
import java.util.List;
import java.util.ArrayList;

/**
 * CLASS TOPIC
 * This class contains a topic name and a list of keywords that
 * describe the topic. 
 */
public class Topic {
   private String topicName;
   private List <String> keyWords; 

   /**
    * Default Constructor
    * Initializes a Topic with an empty string for a topicName and
    * empty List of keywords.
    */
   public Topic() { topicName = ""; keyWords = new ArrayList<String>();  }

   // topicName getter
   public String GetTopicName() {
      return topicName;
   }

   // topicName setter
   public void SetName(String topicName) {
      this.topicName = topicName;
   }

   //keyWords getter
   public List <String> GetKeyWords() {
      return keyWords;
   }

   //keyWords setter
   public void SetKeyWords(String s) {
      this.keyWords.add(s);
   }

   /**
    * SetTopic
    * This method takes a line of text as a paramater and parses
    * through it find the topicName and the keywords.
    */
   public void SetTopic(String line) {
      //gets topicName
      String[] firstSplit = line.split(":");
      topicName = firstSplit[0];

      //gets keyWords
      String[] keyWordsSplit = firstSplit[1].split(",");
      for (String i : keyWordsSplit) { 
         keyWords.add(i);
       }
   }
}