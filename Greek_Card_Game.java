/*
Catherine Larson
UIN: 431006908
PC User
Card game
*/
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.io.*;
import java.util.*;

class Greek_Card_Game {
  public static String playerTurn(String playerName, ArrayList<String> playerHand, ArrayList<String> discard,
  ArrayList<String> draw, Scanner scnr, String refCard, ArrayList<ArrayList<String>> hands, ArrayList<String> names) {
    /*
    playerTurn is a method that is run every time a player takes a turn. It checks
    for special conditions, makes sure that the draw pile is always full, and
    allows players to draw or play cards if they have playable cards.
    */
    //creates a string to keep track of the last card played:
    String lastCardPlayed = "   ";
    //sorts cards in player's hand to make game more playable:
    sortHand(playerHand);
    //replaces draw pile if it goes down to four, so that drawing four cards will work:
    if(draw.size()<=4) {
      replaceDraw(discard, draw);
    }
    //This is where special rules go if they are dependent upon the card played before the turn:

    if(refCard.charAt(0)=='4') {
      Catherine1(playerName, playerHand, draw);
      return "   ";
    }
    
    if (refCard.charAt(0)=='T'){
      Luke1(playerName, playerHand, draw, refCard.charAt(2));
      return "   ";
    }

    //general turn method sequence if the player doesn't meet any special rules:
    boolean turnTaken = false;
    while(!turnTaken) {
      JOptionPane.showMessageDialog(null, "Offer the shield to Lord/Lady " + playerName);
      String[] actionOptions = {"1. Gain a warrior","2. Put forth your champion"}; // the options
      String cardString = displayCards(playerName, playerHand);
      int playerChoice = JOptionPane.showOptionDialog(null,
      cardString + "\nThe top card is "+ discard.get(0)+".","How will you proceed, great God(dess) "+playerName+"?", JOptionPane.DEFAULT_OPTION,
      JOptionPane.QUESTION_MESSAGE, null,
      actionOptions, actionOptions[0]);// asking the questions
      if (playerChoice == JOptionPane.CLOSED_OPTION) System.exit(0);
      if(playerChoice == 0) {
        drawCard(draw, playerHand);
        turnTaken = true;
      } else if (playerChoice == 1) {

        int choiceIndex = JOptionPane.showOptionDialog(null, "The top card is "+ discard.get(0)+".\nWho shall be your champion?",
        Arrays.toString(toArray(playerHand)), JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
        toArray(playerHand), playerHand.get(0));
        String playedCard = playerHand.get(choiceIndex);
        if(!playerHand.contains(playedCard)) {
          String output = "This life is not yours to change. Please make sure it is typed correctly."; //check
          JOptionPane.showMessageDialog(null, output);

          continue;
        } else {
          String topCard = discard.get(0);
          if((topCard.charAt(0)==playedCard.charAt(0))||(topCard.charAt(2)==playedCard.charAt(2))) {
            discard.add(0, playedCard);
            playerHand.remove(playedCard);
            String output = "You have set " + playedCard +" on this great quest.";
            JOptionPane.showMessageDialog(null, output);

            //this is where special rules go for players taking action immediately after they play a card:
            if((playedCard.charAt(0)=='2')&&(playedCard.charAt(2)=='H' || playedCard.charAt(2)=='D')) {
              Liliana2(playerName, playerHand, hands, names);
            }
            if(playedCard.charAt(0)=='3' && playedCard.charAt(2)=='S') {
              Liliana3(playerName, playerHand, discard);
              lastCardPlayed = discard.get(0);
              turnTaken = true;
              break;
            }
            if (playedCard.charAt(0) == '9') {
              Luke2(playerName, playerHand, discard);
              lastCardPlayed = discard.get(0);
              turnTaken = true;
              break;
            }
            lastCardPlayed = playedCard;
            turnTaken = true;
          } else {
            JOptionPane.showMessageDialog(null, "This warrior is not suited for this quest. You must select this champion the same suit or number as the top of the discard pile.");
            continue;
          }
        }
      } else {
        JOptionPane.showMessageDialog(null, "Invalid choice. Try again.");
      }

    }
    return lastCardPlayed;
  }//end playerTurn

  public static String drawCard(ArrayList<String> draw, ArrayList<String> playerHand) {
    //drawCard is a method that adds the first card of the draw deck to the player's hand and removes that card from the draw deck.
      String drawnCard = draw.get(0);
      playerHand.add(drawnCard);
      draw.remove(0);
      String output;
      output = "You gained " + drawnCard;
      JOptionPane.showMessageDialog(null, output);
    return drawnCard;
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
    output+="It is an honor, Lord/Lady "+playerName+", your warriors are: ";
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
  //toArray is a method used in other methods that turns an ArrayList of Strings into an array of Strings:
  public static String[] toArray(ArrayList<String> arrayList) {
    int length = arrayList.size();
    String[] array = new String[length];
    for (int i = 0; i < length; i++) {
      array[i] = arrayList.get(i);
    }
    return array;
  }//end toArray
  //Start of Special rules

  //Liliana2 makes it so a player switches hands with another player when it is called.
  public static void Liliana2(String playerName, ArrayList<String> playerHand, ArrayList<ArrayList<String>> hands, ArrayList<String> names){
    int player_to_switch = JOptionPane.showOptionDialog(null,
    "Your Grace! Lord/Lady "+playerName+", which God/Goddess do you want to switch warriors with?","(Choose an action)", JOptionPane.DEFAULT_OPTION,
    JOptionPane.QUESTION_MESSAGE, null,
  toArray(names), names.get(0));
    ArrayList<String> handToSwitch = hands.get(player_to_switch);
    String[] temp1 = toArray(playerHand);
    String[] temp2 = toArray(handToSwitch);
    playerHand.clear();
    hands.get(player_to_switch).clear();
    for (String card : temp1) {
      handToSwitch.add(card);
    }
    for (String card : temp2) {
      playerHand.add(card);
    }
  }// end Liliana2
  //Liliana3 makes it so a player discards all but three cards when it is called.
  public static void Liliana3 (String playerName,  ArrayList<String> playerHand, ArrayList<String> discard){
    JOptionPane.showMessageDialog(null, "Congrats, you get to put forth all but three warriors in your hand!");
    if(playerHand.size() <= 3) {
      JOptionPane.showMessageDialog(null, "Looks like you already have three or less warriors to choose from, no sending people on quests for you!");
    } else {
      while(playerHand.size() > 3) {
        int choiceIndex = JOptionPane.showOptionDialog(null, "Who shall be your champiom?",
        Arrays.toString(toArray(playerHand)), JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
        toArray(playerHand), playerHand.get(0));
        String playedCard = playerHand.get(choiceIndex);
        playerHand.remove(playedCard);
        discard.add(playedCard);
        }
      }
    }
  // end Liliana3
  //Catherine1 makes it so that a player draws four cards when it is called.
  public static void Catherine1 (String playerName, ArrayList<String> playerHand, ArrayList<String> draw) {
    JOptionPane.showMessageDialog(null, "Apologies Lord/Lady " + playerName + ", you must take on four more warriors!");
    for (int i = 0; i < 4; i++) {
      drawCard(draw, playerHand);
    }
  }
  
  //Luke1: if 10 is played, next player must draw until same suite is called (or no more cards remain)
  public static void Luke1(String playerName, ArrayList<String> playerHand, ArrayList<String> draw, char lSuite) {
    JOptionPane.showMessageDialog(null, "Apologies, Lord/Lady " + playerName + ", you have to draw until you get a " + lSuite + "!");
    String newCard = drawCard(draw, playerHand);
    while (newCard.charAt(2) != lSuite) {
      newCard = drawCard(draw, playerHand);
    }
  } // end Luke1

  //Luke2: if 9 is played - every odd is played (contrasts w/ Patrick's rule?)??
  public static void Luke2(String playerName, ArrayList<String> playerHand, ArrayList<String> discard) {
    JOptionPane.showMessageDialog(null, "Congrats Lord/Lady " + playerName +", if you have any other odd warriors in your hand, then you can play all of them!");
    for (int i = 0; i < playerHand.size(); i++) {
      if (Character.isDigit(playerHand.get(i).charAt(0))) {
        if (Character.getNumericValue(playerHand.get(i).charAt(0)) % 2 == 1) {
          JOptionPane.showMessageDialog(null, "Your champion is " + playerHand.get(i));
          String playedCard = playerHand.get(i);
          playerHand.remove(playedCard);
          discard.add(playedCard);
          i--; //since card (plus its position was just removed), go back 1 in position of the cards in the hand
        }
      }
    }
  } // end Luke2

  public static void main(String[] args) {
    //creates a card deck:
    String[] cardDeck = new String[52];
    String[] suits = {"S", "H", "D", "C"};
    String[] numbers = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
    int oddCounter = 0; //This is for a rule pertaining to the amount of odd number cards played.
    for(int i = 0; i < suits.length; i++) {
      for(int j = 0; j < numbers.length; j++) {
        cardDeck[(i*numbers.length)+j] = numbers[j]+"-"+suits[i];
      }//end for loop
    }//end for loop
    while (true) {
      Scanner scnr = new Scanner(System.in);
      //creates array lists for discard and draw piles:
      ArrayList<String> discardPile = new ArrayList<String>();
      ArrayList<String> drawPile = new ArrayList<String>();
      ArrayList<String> names = new ArrayList<String>();
      ArrayList<ArrayList<String>> hands = new ArrayList<ArrayList<String>>();
      //records number of players for indexing purposes:
      String message = "How many Gods/Goddesses? (game needs 2 to 5 players):";
      String [] options = {"2 players","3 players", "4 players", "5 players"};
      int numPlayers = JOptionPane.showOptionDialog(null, message,"(Choose an action)", JOptionPane.DEFAULT_OPTION,
      JOptionPane.QUESTION_MESSAGE, null,
      options, options[0]) + 2;
      if ((numPlayers - 2) == JOptionPane.CLOSED_OPTION) System.exit(0);
      for (int i = 0; i < numPlayers; i++) {
        String name = (JOptionPane.showInputDialog(
        null, "Lord/Lady " + (i+1) + " Name: ", null, JOptionPane.QUESTION_MESSAGE
        ));
        if (name == null) System.exit(0);
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
        //Patrick's Rules, but in main because calling a method with 5 parameters was more work than simply putting the rules in main

        //testing rules
        //rule 7
          if (lastCardPlayed.indexOf('A') != -1 || lastCardPlayed.indexOf('3') != -1 || lastCardPlayed.indexOf('5') != -1 || lastCardPlayed.indexOf('7') != -1 || lastCardPlayed.indexOf('9') != -1){
            oddCounter++;
              } // end if checking for odds
          else { // end reset of odd counter if card is not odd
            oddCounter = 0;
            }
          if (oddCounter == 5){
            playerWon = true;
          } // end if that ends game if 5 odd cards are played in a row.

          //rule 8 : King after Queen
          String previousCard = discardPile.get(0);
          try {
            previousCard = discardPile.get(1);
          } catch (Exception e){
            previousCard = discardPile.get(0);
          }
          if (lastCardPlayed.indexOf('K') != -1 && previousCard.indexOf('Q') != -1){
              JOptionPane.showMessageDialog(null, "A king was played after a Queen, current God/Goddess gets to put forth another warriors.");
            if(turnIncrement == 1){ //if turns are normally progressing, subtracts one because one will be added.
              turnIndex--;
            }
            else{ // if order of players has been reversed. Adds one because -1 will later be subtracted so person who played king will go again
                turnIndex++;
              } // end else
            }//end if






        if(playerWon) {
          JOptionPane.showMessageDialog(null, "Congrats Lord/Lady " + names.get(winningPlayer) + ", you win!");
          break;
        }
        if(turnIndex >= numPlayers) {
          turnIndex = 0;
        }
        if(turnIndex < 0) {
          turnIndex = numPlayers-1;
        }
        lastCardPlayed = playerTurn(names.get(turnIndex), hands.get(turnIndex), discardPile,
        drawPile, scnr, lastCardPlayed, hands, names);
        String topCard = discardPile.get(0);
        if(lastCardPlayed.charAt(0)=='A') {
          turnIncrement = turnIncrement*-1;
        }
        turnIndex = turnIndex + turnIncrement;
      }

      String [] play_again = {"yes","no"};
      int continueChoice = JOptionPane.showOptionDialog(null, "Play again?","(Choose an action)", JOptionPane.DEFAULT_OPTION,
      JOptionPane.QUESTION_MESSAGE, null,
      play_again, play_again[0]);
      if(continueChoice==1) {
        break;
      }
    }

  }//end main
}//end class
