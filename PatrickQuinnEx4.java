/*
Patrick Quinn
630002654
10/6/2021
CSCE 111 - 501
PC ~ Make a deck of cards using arrays, and shuffle it using the Random class
*/
import java.util.Random;
class PatrickQuinnEx4{
  public static void main(String[] args) {
    int counter = 0; // counter that lets us cycle through the array

    String[] deck = new String[54]; //Creates array with 54 slots

    String[] suit = new String[4];    //Assigns Suits
    suit[0] = "S";
    suit[1] = "D";
    suit[2] = "H";
    suit[3] = "C";

    String[] value = new String[14]; //Hard codes "face" card to their respective numerical values
    value[1] = "A";
    value[10] = "T";
    value[11] = "J";
    value[12] = "Q";
    value[13] = "K";


    for (int i = 0;i < 4; i++){ //goes through each suit
      for (int j = 1; j <= 13; j++ ) { // goes through numerical values 0-12
        if (j == 1 || j == 13 || j == 10 || j == 11 || j == 12){ //When card values are not numerically represented
          deck[counter] = suit[i] + "" + value[j];
        }
        else{
        deck[counter] = suit[i] + "" + j;
        }
        counter++; //incredments through counter so every card has a unique spot in the deck
      }
    }
      //Putting the jockers into the last 2 slots of the array
      deck[52] = "j0";
      deck[53] = "j1";



System.out.println("Welcome to the deck generator + shuffler! \n"); //For rubric saying we need to greet user.

//Printing the deck in an array
System.out.println("\nDeck ordered by suit and value: \n");//new lines & title for neatness
counter = 0; //resets counter to loop through deck
for (int j=0;j<4;j++) { //goes through each suit
  for (int i=0;i<13 ;i++ ) { // makes sure that only the 13 of the same suit are printed on the same line
    System.out.print(deck[counter++] + " ");
    }
    System.out.println(); //creates new line for the suit
  }
System.out.println(deck[52] + " " + deck[53]); //puts in jokers on a new line



//Randomize the deck used code from https://www.tutorialspoint.com/how-to-randomize-and-shuffle-array-of-numbers-in-java
Random rand = new Random();
      for(int i = 0; i < 54; ++i) {
         int index = rand.nextInt(54 - i);
         String tmp = deck[54 - 1 - i];
         deck[54 - 1 - i] = deck[index];
         deck[index] = tmp;
       }

System.out.println(); // Space for neatness of printing

//Printing randomized deck
System.out.println("Randomized deck: \n");
counter = 0; //resets counter to loop through deck
for (int j=0;j<4;j++) { //same loops as before, prints all of the cards in the deck in a neat array
  for (int i=0;i<13 ;i++ ) {
    System.out.print(deck[counter++] + " ");
    }
    System.out.println(); //creates new line for the next row
  }

System.out.println(deck[52] + " " + deck[53]); //puts whatever cards in the last 2 slots in the last 2 wide row.

  }//end main
}//end class
