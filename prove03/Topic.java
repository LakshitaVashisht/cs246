/**
 * CLASS TOPIC
 *  This class represents a Topic. It just has a name
 *  for its member variables. It has its own getDisplayText()
 *  function. 
 */
public class Topic implements Annotation {
   private String name;

   /**
    * GETTER and SETTER
    *  The getter and setter for name
    */
   public String getName() { return name; }
   public void setName(String name) { this.name = name; }

   /**
    * GET DISPLAY TEXT
    *  This method difines the getDisplayText() function from the
    *  Annotation interface for the Topic objects. It returns the
    *  topic name.
    */
   public String getDisplayText() {
      return name;
   }  
}