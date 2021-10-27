
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.util.*;

class Greek_Card_Game {

  public static String playerTurn(String playerName, ArrayList<String> playerHand, ArrayList<String> discard, ArrayList<String> draw, Scanner scnr, String refCard) {
    /*
    playerTurn is a method that is run every time a player takes a turn. It checks
    for special conditions, makes sure that the draw pile is always full, and
    allows players to draw or play cards if they have playable cards.
    */
    //creates a string to keep track of the last card played:
    String lastCardPlayed = "   ";
    //sorts cards in player's hand to make game more playable:
    sortHand(playerHand);
    //replaces draw pile if it goes down to zero:
    if(draw.size()==0) {
      replaceDraw(discard, draw);
    }
    //general turn method sequence if the player doesn't meet any special rules:
    boolean turnTaken = false;
    while(!turnTaken) {
      JOptionPane.showMessageDialog(null, "Please pass the computer to " + playerName);
      String[] actionOptions = {"1. draw","2. play"}; // the options
      String cardString = displayCards(playerName, playerHand);
      int playerChoice = JOptionPane.showOptionDialog(null, cardString + "\nThe top card is "+ discard.get(0)+".","What would you like to do?", JOptionPane.DEFAULT_OPTION,
      JOptionPane.QUESTION_MESSAGE, null,
      actionOptions, actionOptions[0]);// asking the questions
      if(playerChoice == 0) {
        drawCard(draw, playerHand);
        turnTaken = true;
      } else if (playerChoice == 1) {

        int choiceIndex = JOptionPane.showOptionDialog(null, "The top card is "+ discard.get(0)+".\nWhat card would you like to play?",Arrays.toString(toArray(playerHand)), JOptionPane.DEFAULT_OPTION,
        JOptionPane.QUESTION_MESSAGE, null,
        toArray(playerHand), playerHand.get(0));
        String playedCard = playerHand.get(choiceIndex);
        if(!playerHand.contains(playedCard)) {
          String output = "This card was not found in your hand. Please make sure it is typed correctly.";
          JOptionPane.showMessageDialog(null, output);

          continue;
        } else {
          String topCard = discard.get(0);
          if((topCard.charAt(0)==playedCard.charAt(0))||(topCard.charAt(2)==playedCard.charAt(2))) {
            discard.add(0, playedCard);
            playerHand.remove(playedCard);
            String output = "You played a " + playedCard;
            JOptionPane.showMessageDialog(null, output);
            lastCardPlayed = playedCard;
            turnTaken = true;
          } else {
            JOptionPane.showMessageDialog(null, "This card is not playable. You must play a card the same suit or number as the top of the discard pile.");
            continue;
          }
        }
      } else {
        JOptionPane.showMessageDialog(null, "Invalid choice. Try again.");
      }

    }
    return lastCardPlayed;
  }//end playerTurn

  public static void drawCard(ArrayList<String> draw, ArrayList<String> playerHand) {
    //drawCard is a method that adds the first card of the draw deck to the player's hand and removes that card from the draw deck.
      String drawnCard = draw.get(0);
      playerHand.add(drawnCard);
      draw.remove(0);
      String output;
      output = "You drew a " + drawnCard;
      JOptionPane.showMessageDialog(null, output);
  }//end drawCard
  public static void replaceDraw(ArrayList<String> discard, ArrayList<String> draw) {
    /*
    replaceDraw is a method that replaces the draw pile if it becomes empty with
    all but the top card of the discard pile. It simulates taking the first card
    off of the discard pile to be the new discard pile and shuffling the remaining
    cards to be the draw pile.
    */
      String firstCard = discard.get(0);
      discard.remove(0);
      Collections.shuffle(discard);
      for(int i = 0; i < discard.size();i++) {
        draw.add(discard.get(i));
      }
      discard.clear();
      discard.add(firstCard);
  }//end replaceDraw

  public static String displayCards(String playerName, ArrayList<String> playerHand) {
    //displayCards is a method that uses a for loop to show all of a player's cards.
    String output = "";
    output+="Hi, "+playerName+", your cards are: ";
    for(int i = 0; i < playerHand.size();i++) {
      if(i==playerHand.size()-1) {
        output+=(playerHand.get(i));
      } else {
        output+=(playerHand.get(i) + ", ");
      }
    }
    return output;
  }//end displayCards

  public static void sortHand(ArrayList<String> playerHand) {
    //sortHand is a method that sorts a player's hand into suits so that gameplay is easier.
    ArrayList<String> diamonds = new ArrayList<String>();
    ArrayList<String> hearts = new ArrayList<String>();
    ArrayList<String> clubs = new ArrayList<String>();
    ArrayList<String> spades = new ArrayList<String>();
    for (String card : playerHand) {
      switch (card.charAt(2)) {
        case 'D':
          diamonds.add(card);
          break;
        case 'H':
          hearts.add(card);
          break;
        case 'C':
          clubs.add(card);
          break;
        case 'S':
          spades.add(card);
         break;
      }
    }
    playerHand.clear();
    for (String card : diamonds) {
      playerHand.add(card);
    }
    for (String card : hearts) {
      playerHand.add(card);
    }
    for (String card : clubs) {
      playerHand.add(card);
    }
    for (String card : spades) {
      playerHand.add(card);
    }
  }//end sortHand

  public static void shuffleDeck(String[] deck) {
    //shuffleDeck is a method that shuffles the deck that is fed into it:
    for(int i = 0; i < 100; i++) {
      Random random = new Random();
      int randoNum = random.nextInt(deck.length);
      int randoNum2 = random.nextInt(deck.length);
      String holder = deck[randoNum];
      deck[randoNum] = deck[randoNum2];
      deck[randoNum2] = holder;
    }
  }//end shuffleDeck

  public static String[] toArray(ArrayList<String> arrayList) {
    int length = arrayList.size();
    String[] array = new String[length];
    for (int i = 0; i < length; i++) {
      array[i] = arrayList.get(i);
    }
    return array;
  }

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
    while(true) {
      Scanner scnr = new Scanner(System.in);
      //creates array lists for discard and draw piles:
      ArrayList<String> discardPile = new ArrayList<String>();
      ArrayList<String> drawPile = new ArrayList<String>();
      ArrayList<String> names = new ArrayList<String>();
      ArrayList<ArrayList<String>> hands = new ArrayList<ArrayList<String>>();
      //records number of players for indexing purposes:
      int numPlayers = Integer.parseInt(JOptionPane.showInputDialog(
      null, "How many players? (game needs 2 to 5 players):", null, JOptionPane.QUESTION_MESSAGE
      ));
      if(numPlayers<2||numPlayers>5) {
        JOptionPane.showMessageDialog(null, "Number of players must be between 2 and 5.");
        continue;
      }
      for (int i = 0; i < numPlayers; i++) {
        String name = (JOptionPane.showInputDialog(
        null, "Player " + (i+1) + " Name: ", null, JOptionPane.QUESTION_MESSAGE
        ));
        names.add(name);
      }
      //shuffles the deck and deals the cards to the players:
      shuffleDeck(cardDeck);
      for(int i = 0; i < numPlayers; i++) {
        ArrayList<String> hand = new ArrayList<String>();
        for(int j = 0; j < 7; j++) {
          hand.add(cardDeck[i*7+j]);
        }
        hands.add(hand);
      }
      //creates the discard pile and draw pile:
      discardPile.add(cardDeck[numPlayers*7]);
      for(int i = (numPlayers*7+1); i < cardDeck.length; i++) {
        drawPile.add(cardDeck[i]);
      }
      //goes through the turns:
      int turnIndex = 0;
      int turnIncrement = 1;
      boolean playerWon = false;
      int winningPlayer = 0;
      String lastCardPlayed = discardPile.get(0);
      while(!playerWon) {
        for(int i = 0; i < hands.size(); i++) {
          if((hands.get(i)).size()==0) {
          playerWon = true;
          winningPlayer = i;
          }
        }
        if(playerWon) {
          JOptionPane.showMessageDialog(null, "Congrats " + names.get(winningPlayer) + ", you win!");
          break;
        }
        if(turnIndex >= numPlayers) {
          turnIndex = 0;
        }
        if(turnIndex < 0) {
          turnIndex = numPlayers-1;
        }
        lastCardPlayed = playerTurn(names.get(turnIndex), hands.get(turnIndex), discardPile, drawPile, scnr, lastCardPlayed);
        String topCard = discardPile.get(0);
        if(lastCardPlayed.charAt(0)=='A') {
          turnIncrement = turnIncrement*-1;
        }
        turnIndex = turnIndex + turnIncrement;
      }

      System.out.println("Play again?");
      String [] play_again = {"yes","no"};
      int continueChoice = JOptionPane.showOptionDialog(null, "Play again?","(Choose an action)", JOptionPane.DEFAULT_OPTION,
      JOptionPane.QUESTION_MESSAGE, null,
      play_again, play_again[0]);
      if(continueChoice==1) {
        break;
      }
    }

  }//end main

}
