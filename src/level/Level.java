package level;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import entity.*;

/**
 * This class implements levels in the Plant vs Zombie games. Each level has 
 * certain unifying characteristics. Every level is played on a fixed 9x5 board.
 * The board consists of a tile that possesses certain terrain hazards (net yet implemented)
 * Every level has a list of different Zombie types that are spawned every turn in waves.
 * A player's goal is to survive every wave. A level determines how many waves a player needs
 * to survive to win.
 * 
 * @author Rahul Anilkumar, Christopher Wang, Christophe Tran, Thomas Leung
 * @version 1.0
 */
public class Level implements Serializable{
	public static final int X_BOUNDARY = 9;
	public static final int Y_BOUNDARY = 5;
	private List<EntityType> zombieList;
	private HashSet<EntityType> plantList;
	private int waves;
	private int initialSunPoints;
	
	/**
	 * Constructor for the Level class.
	 * @param waves The number of waves that a player must endure for a Level
	 * @param initialSunPoints The number of sun points that a player begins with
	 * @param zombieList The list of Zombies types that can be spawned each turn for a Level
	 */
	public Level(int waves, int initialSunPoints) {
		this.waves = waves;
		this.initialSunPoints = initialSunPoints;
		this.zombieList = new ArrayList<EntityType>();
		this.plantList = new HashSet<EntityType>();
	}
	
	/**
	 * Empty Constructor for the Level class.
	 */
	public Level() {
		this.waves = 0;
		this.initialSunPoints = 0;
		this.zombieList = new ArrayList<EntityType>();
		this.plantList = new HashSet<EntityType>();
	}
	
	/**
	 * Retrieves the list of Zombies that can be spawned this Level
	 * @return The  list of Zombies that can be spawned this Level
	 */
	public List<EntityType> getZombieList(){
		return zombieList;
	}
	
	/**
	 * Retrieves the list of Plants hat can be spawned this Level
	 * @return The  list of Plants that can be spawned this Level
	 */
	public Set<EntityType> getPlantSet(){
		return plantList;
	}
	
	/**
	 * Adds a plant type to the PlantList
	 * @param plant The type of plant to be added
	 */
	public void addPlantType(EntityType plant) {
		plantList.add(plant);
	}
	
	/**
	 * Adds a zombie type to the ZombieList
	 * @param zombie The type of zombie to be added
	 */
	public void addZombieType(EntityType zombie) {
		zombieList.add(zombie);
	}

	/**
	 * Retrieves the number of waves that a player must endure for a Level
	 * @return The number of waves that a player must endure for a Level
	 */
	public int getWaves() {
		return waves;
	}
	
	/**
	 * Retrieves the number of sun points that a player begins with
	 * @return The number of sun points that a player begins with
	 */
	public int getInitialSunPoints() {
		return initialSunPoints;
	}
	
	/**
	 * Sets the number of waves that a player must endure for a Level
	 * @param waves The number of waves to be set
	 */
	public void setWaves(int waves) {
		this.waves = waves;
	}
	
	/**
	 * Retrieves the number of sun points that a player begins with
	 * @param initialSunPoints The number of beginning sun points to be set
	 */
	public void setInitialSunPoints(int initialSunPoints) {
		this.initialSunPoints = initialSunPoints;
	}
	
	/**
	 * Encodes an EntityType List as a String in XML format
	 * @param entityList The list to be encoded
	 * @return An EntityType List encoded in as a XML String
	 */
	private String collectionToXML(Collection<EntityType> entityList) {
		StringBuilder xml = new StringBuilder();
		for(EntityType entity: entityList) {
			xml.append("<EntityType>" + entity + "</EntityType>") ;
		}
		return xml.toString();
	}
	/** Encodes a level as a String in XML format
	 * @return A Level String encoded in as XML a 
	 * */
	public String toXML() {
		StringBuilder xml = new StringBuilder("<Level>");
		xml.append("<PlantList>" + collectionToXML(plantList) + "</PlantList>");
		xml.append("<ZombieList>" + collectionToXML(zombieList) + "</ZombieList>");
		xml.append("<Waves>" + waves + "</Waves>");
		xml.append("<InitialSunPoints>" + initialSunPoints + "</InitialSunPoints>");
		xml.append("</Level>");
		return xml.toString();
	}
	
	/** Exports a Level to an XML file
	 * @param filename The name of the file to export the Level to
	 * */
	public void exportToXMLFile(String filename) {
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(filename + ".xml"));
			out.write(this.toXML());
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/** Imports a Level from an XML file
	 * @param filename The name of the file to import the Level from
	 * */
	public static Level importFromXMLFile(String filename) {
		Level level = new Level();
	      try {
	          File inputFile = new File(filename + ".xml");
	          SAXParserFactory factory = SAXParserFactory.newInstance();
	          SAXLevelHandler levelHandler = new SAXLevelHandler();
	          SAXParser saxParser = factory.newSAXParser();
	          saxParser.parse(inputFile, levelHandler);
	          level = levelHandler.getLevel();
	       } catch (Exception e) {
	    	   return null;
	       }
	      return level;
	}
	
	/**
	 * Returns a random zombie type from the zombie list
	 * @return A random zombie type from the zombie list
	 * */
	public EntityType getRandomZombie() {
		return zombieList.get(new Random().nextInt(zombieList.size()));
	}
}



