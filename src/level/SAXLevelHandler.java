package level;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import entity.EntityType;

/**
 * A class that extends DefaultHandler that will Decode the AddressBooks XML encoding
 * @author Christopher Wang 100951354 
 */
public class SAXLevelHandler extends DefaultHandler {
   private Level level = new Level();
   private boolean plantListFlag;
   private boolean zombieListFlag;
   private boolean entityTypeFlag;
   private boolean wavesFlag;
   private boolean intialSunPointsFlag;

   
   /** A method that executes when an element has been opened. Currently sets flags depending on element type
    * @param uri The Namespace URI, or the empty string if the element has no Namespace URI or if Namespace processing is not being performed.
    * @param localName The local name (without prefix), or the empty string if Namespace processing is not being performed.
    * @param qName The qualified name (with prefix), or the empty string if qualified names are not available.
    * @param attributes The attributes attached to the element. If there are no attributes, it shall be an empty Attributes object.*/
   @Override
   public void startElement 
      (String uri, String localName, String qName, Attributes attributes)
      throws SAXException {  
      if (qName.equalsIgnoreCase("PlantList")) {
    	  plantListFlag = true;
      } else if (qName.equalsIgnoreCase("ZombieList")) {
    	  zombieListFlag = true;
      } else if (qName.equalsIgnoreCase("EntityType")) {
    	  entityTypeFlag = true;
      } else if (qName.equalsIgnoreCase("Waves")) {
    	  wavesFlag = true;
      } else if (qName.equalsIgnoreCase("InitialSunPoints")) {
    	  intialSunPointsFlag = true;
      }
   }
   /** A method that executes when an element has been closed. Currently builds the list by adding buddy when the BuddyInfo tag is closed.
    * @param uri The Namespace URI, or the empty string if the element has no Namespace URI or if Namespace processing is not being performed.
    * @param localName The local name (without prefix), or the empty string if Namespace processing is not being performed.
    * @param qName The qualified name (with prefix), or the empty string if qualified names are not available. */
   @Override
   public void endElement(String uri, String localName, String qName) 
    throws SAXException {
	   if (qName.equalsIgnoreCase("PlantList")) {
		   plantListFlag = false;
	   } else if (qName.equalsIgnoreCase("ZombieList")) {
		   zombieListFlag = false;
	   }
   }
   /** A method that executes to reads in the text nodes of the XML tree. Currently builds the buddy by setting its fields.
    * @param ch[] The character array that stores the text node
    * @param start The start address of the text node
    * @param length The end of the text node */
   @Override
   public void characters(char ch[], int start, int length) throws SAXException {
	   String character = new String(ch, start, length);
	  if (plantListFlag && entityTypeFlag) {
         level.addPlantType(EntityType.valueOf(character));
         entityTypeFlag = false;
      } else if (zombieListFlag && entityTypeFlag) {
    	 level.addZombieType(EntityType.valueOf(character));
    	 entityTypeFlag = false;
      } else if (wavesFlag) {
    	 level.setWaves(Integer.parseInt(character));
    	 wavesFlag = false;
      } else if (intialSunPointsFlag) {
    	 level.setInitialSunPoints(Integer.parseInt(character));
    	 intialSunPointsFlag = false;
      } 
   }
   /** 
	* Returns the level encoded from XML
    * @return The level encoded from XML
    * */
   public Level getLevel() {
	   return level;
   }
}
