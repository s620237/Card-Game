import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

class Greek_Card_Game {

  public static void playerTurn(String playerName, ArrayList<String> playerHand, ArrayList<String> discard, ArrayList<String> draw, Scanner scnr) {
    //sorts cards in player's hand to make game more playable:
    sortHand(playerHand);
    //replaces draw pile if it goes down to zero:
    if(draw.size()==0) {
      replaceDraw(discard, draw);
    }
    //displays the player's cards:
    displayCards(playerName, playerHand);
    boolean turnTaken = false;
    while(!turnTaken) {
      System.out.println("The top card is " + discard.get(0)+".");
      System.out.println("Would you like to draw or play a card?");
      System.out.println("1. draw");
      System.out.println("2. play");
      int playerChoice = scnr.nextInt();
      if(playerChoice == 1) {
        String drawnCard = draw.get(0);
        playerHand.add(drawnCard);
        draw.remove(0);
        System.out.println("You drew a " + drawnCard);
        turnTaken = true;
      } else if (playerChoice == 2) {
        System.out.println("What card would you like to play?");
        scnr.nextLine();
        String playedCard = scnr.nextLine();
        if(!playerHand.contains(playedCard)) {
          System.out.println("This card was not found in your hand. Please make sure it is typed correctly.");
          continue;
        } else {
          String topCard = discard.get(0);
          if((topCard.charAt(0)==playedCard.charAt(0))||(topCard.charAt(2)==playedCard.charAt(2))) {
            discard.add(0, playedCard);
            playerHand.remove(playedCard);
            System.out.println("You played a " + playedCard);
            turnTaken = true;
          } else {
            System.out.println("This card is not playable. You must play a card the same suit or number as the top of the discard pile.");
            continue;
          }
        }
      } else {
        System.out.println("Invalid choice. Try again.");
      }

    }
  }//end playerTurn

  public static void replaceDraw(ArrayList<String> discard, ArrayList<String> draw) {
      String firstCard = discard.get(0);
      discard.remove(0);
      Collections.shuffle(discard);
      for(int i = 0; i < discard.size();i++) {
        draw.add(discard.get(i));
      }
      discard.clear();
      discard.add(firstCard);
  }//end replaceDraw

  public static void displayCards(String playerName, ArrayList<String> playerHand) {
    System.out.println("Hi, "+playerName+", your cards are: ");
    for(int i = 0; i < playerHand.size();i++) {
      if(i==playerHand.size()-1) {
        System.out.print(playerHand.get(i));
        System.out.println();
      } else {
        System.out.print(playerHand.get(i) + ", ");
      }
    }
  }//end displayCards

  public static void sortHand(ArrayList<String> playerHand) {
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
    while(true) {
      Scanner scnr = new Scanner(System.in);
      //creates array lists for discard and draw piles:
      ArrayList<String> discardPile = new ArrayList<String>();
      ArrayList<String> drawPile = new ArrayList<String>();
      ArrayList<String> names = new ArrayList<String>();
      ArrayList<ArrayList<String>> hands = new ArrayList<ArrayList<String>>();
      System.out.println("How many players? (game needs 2 to 5 players):");
      int numPlayers = scnr.nextInt();
      scnr.nextLine();
      if(numPlayers<2||numPlayers>5) {
        System.out.println("Number of players must be between 2 and 5.");
        continue;
      }
      for (int i = 0; i < numPlayers; i++) {
        System.out.println("Player " + (i+1) + " Name: ");
        names.add(scnr.nextLine());
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
      while(!playerWon) {
        for(int i = 0; i < hands.size(); i++) {
          if((hands.get(i)).size()==0) {
          playerWon = true;
          winningPlayer = i;
          }
        }
        if(playerWon) {
          System.out.println("Congrats " + names.get(winningPlayer) + ", you win!");
          break;
        }
        if(turnIndex >= numPlayers) {
          turnIndex = 0;
        }
        if(turnIndex < 0) {
          turnIndex = numPlayers;
        }
        playerTurn(names.get(turnIndex), hands.get(turnIndex), discardPile, drawPile, scnr);
        String topCard = discardPile.get(0);
        if(topCard.charAt(2)=='A') {
          turnIncrement = turnIncrement*-1;
        }
        turnIndex = turnIndex + turnIncrement;
      }

      System.out.println("Play again?");
      int continueChoice = scnr.nextInt();
      if(continueChoice==1) {
        break;
      }
    }

  }//end main

}
