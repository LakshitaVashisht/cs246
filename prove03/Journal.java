/**
 * Hanisha Ravuri
 * CS 246
 * Prove 03 Spiritual Journal XML Parsing
 *
 * This program consist of four classes, Journal, Entry, Scripture
 * and Topic, and a interface, Annotation. This program parses
 * through an XML file and displays its content.
 */
import java.util.List;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

/**
 * CLASS Journal
 *  This class contains the main method and works with the other
 *  classes. It parses through a XML file and puts the file's content
 *  into a list of Entries. It then displays the content.
 */
public class Journal {
   private List <Entry> list = new ArrayList <Entry>(); 

   /**
    * MAIN
    *  This is where the program starts. The file name is passed in
    *  and the run and display method is called
    */
   public static void main(String[] args) throws Exception {
      String fileName = args[0];
      Journal journal = new Journal();
      journal.run(fileName);
      journal.display();
   }

   /**
    * RUN
    *  This method uses the DOM classes to read in and parse a XML
    *  file. It creates scripure and topic objects, stores data in
    *  them, and places then in the list of Entries. 
    */
   public void run(String fileName) throws Exception {
      
      try {
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(new File(fileName));
 
         doc.getDocumentElement().normalize();

         // For each entry in the file
         NodeList entryList = doc.getElementsByTagName("entry");
         for (int temp = 0; temp < entryList.getLength(); temp++) {
            Node nNode = entryList.item(temp);

            // Adds a entry to the Journal list
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               Entry entry = new Entry();
               list.add(entry);
               entry.setDate(eElement.getAttribute("date"));

               // For each content in each entry
               NodeList contentList = eElement.getElementsByTagName("content");
               for (int temp2 = 0; temp2 < contentList.getLength(); temp2++) {
                  Node nNode2 = contentList.item(temp2);

                  // Sets the content for each entry
                  if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
                     Element eElement2 = (Element) nNode2;
                     entry.setContent(eElement2.getTextContent());
                  }
               }
               
               // For each scritpure in each entry
               NodeList scriptureList = eElement.getElementsByTagName("scripture");
               for (int temp3 = 0; temp3 < scriptureList.getLength(); temp3++) {
                  Node nNode3 = scriptureList.item(temp3);

                  // Makes, sets, and adds the scriptures to the entry
                  if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                     Element eElement3 = (Element) nNode3;
                     Scripture scripture = new Scripture();
                     scripture.setBook(eElement3.getAttribute("book"));
                     scripture.setChapter(eElement3.getAttribute("chapter"));
                     scripture.setStartVerse(eElement3.getAttribute("startVerse"));
                     scripture.setEndVerse(eElement3.getAttribute("endVerse"));
                     entry.getList().add(scripture);
                  }
               }
               
               //for each topic in each entry
               NodeList topicList = eElement.getElementsByTagName("topic");
               for (int temp4 = 0; temp4 < topicList.getLength(); temp4++) {
                  Node nNode4 = topicList.item(temp4);

                  // Makes, sets, and adds the topics to the entry 
                  if (nNode4.getNodeType() == Node.ELEMENT_NODE) {
                     Element eElement4 = (Element) nNode4;
                     Topic topic = new Topic();
                     topic.setName(eElement4.getAttribute("name"));
                     entry.getList().add(topic);
                  }
               }  
            }
         }
        
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * DISPLAY
    *  The Jounal's display method displays each Entry by calling
    *  each Entries display method.
    */
   public void display() {   
      for (Entry e : list) {
         System.out.println("-----\nEntry");
         e.display();
      }
   }

     
   
}