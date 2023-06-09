import java.util.Arrays;
import java.util.Random;

//Can have stats be a list of integers, and use an index to collect a statistic when its relevant
//eg: 0 = health, 1 = mana etc.
public class StatGenerator {

  //Initializing a new array to hold the six basic stats
  //Strength, Dexterity, Constitution, Intelligence, Wisdom, and Charisma
  //Side Note: Will have to copy these references if we are creating a new stat block
  private static String[] stringStatBlock = new String[6];
  private static int[] intStatBlock = new int[6];
  private static int[] modifiers = new int[6];

  public StatGenerator(){
    statGenerator();
  }
  //Constructor to create an array of random statistics from 10 to 20
  private static void statGenerator()
  {
    //looping through and assigning random values to each index in the array
    for(int i = 0; i < 6; i++)
    {
      //Creating an instance of the random class
      Random rand = new Random();
      //Generating a random object in the range from 0 to 20 - 1
      int stat = rand.nextInt(11) + 10;
      modifiers[i] = setModifier(stat);
      intStatBlock[i] = stat;
      String statString = String.valueOf((stat));
      setStatBlock(i, statString);

    }
  }

  private static int setModifier(int stat){
    return (stat - 10) / 2;
  }

  public String[] getStatBlock() {
    return stringStatBlock;
  }

  public static void setStatBlock(int index, String value) {
    stringStatBlock[index] = value;
  }

  public static void main(String[] args) {
    //Constructs an array of statistics
    StatGenerator test = new StatGenerator();
    for(String i : test.getStatBlock()){
      System.out.println(i);
    }
    for(int i : modifiers){
      System.out.println(i);
    }
  }
}



//instance variable
//ctors
//business methods
//getter setters