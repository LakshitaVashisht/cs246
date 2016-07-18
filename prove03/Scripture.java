/**
 * CLASS SCRIPTURE
 *  This class represents a scripture. It has a book,
 *  chapter, start verse, and end verse. It also implements
 *  the Annotation interface and has its own getDisplayText()
 *  function.
 */
public class Scripture implements Annotation {
   private String book;
   private String chapter;
   private String startVerse;
   private String endVerse;

   /**
    * GETTERS
    *  Gets the Scriptures's member variables 
    */
   public String getBook()       { return book;       }
   public String getChapter()    { return chapter;    }
   public String getStartVerse() { return startVerse; }
   public String getEndVerse()   { return startVerse; }

   /**
    * SETTERS
    *  Sets the Scripture's variables  
    */
   public void setBook(String book) { this.book = book; }
   public void setChapter(String chapter) { this.chapter = chapter; }
   public void setStartVerse(String startVerse) { this.startVerse = startVerse; }
   public void setEndVerse(String endVerse) { this.endVerse = endVerse; }

   /**
    * GET DISPLAY TEXT
    *  This method defines the getDisplayText() function from the
    *  Annotation interface for Scripture objects. It displays the
    *  book, chapter, startVerse, and if the Scripture has one, the
    *  end verse.
    */
   public String getDisplayText() {
      String display = book + " " + chapter + ": " + startVerse;
      if (endVerse.equals(null)) 
         display += "-" + endVerse;
      return display;
   } 
}