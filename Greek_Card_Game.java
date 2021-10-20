import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Greek_Card_Game {

  public static boolean playerTurn(ArrayList<Integer> playerHand) {
    return true;
  }//end playerTurn

  public static void shuffleDeck(String[] deck) {
    //shuffles the deck that is fed into it:
    for(int i = 0; i < 100; i++) {
      Random random = new Random();
      int randoNum = random.nextInt(deck.length);
      int randoNum2 = random.nextInt(deck.length);
      String holder = deck[randoNum];
      deck[randoNum] = deck[randoNum2];
      deck[randoNum2] = holder;
    }
  }//end shuffleDeck

  public static void main(String[] args) {
    //creates a card deck:
    String[] cardDeck = new String[52];
    String[] suits = {"S", "H", "D", "C"};
    String[] numbers = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
    for(int i = 0; i < suits.length; i++) {
      for(int j = 0; j < numbers.length; j++) {
        cardDeck[(i*numbers.length)+j] = numbers[j]+"-"+suits[i];
      }//end for loop
    }//end for loop

  }//end main

}
