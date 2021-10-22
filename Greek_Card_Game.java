import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
// make changes

class Greek_Card_Game {

  public static boolean playerTurn(String playerName, ArrayList<String> playerHand, ArrayList<String> discard, ArrayList<String> draw) {
    //sorts cards in player's hand to make game more playable:
    sortHand(playerHand);
    //replaces draw pile if it goes down to zero:
    if(draw.size()==0) {
      String firstCard = discard.get(0);
      discard.remove(0);
      shuffleDeck(discard);
      for(int i = 0; i < discard.size();i++) {
        draw.add(discard.get(i));
      }
      discard.clear();
      discard.add(firstCard);
    }
    System.out.println("Hi, "+playerName+" your cards are: ");
    for(int i = 0; i < playerHand.size();i++) {
      if(i==playerHand.size()-1) {
        System.out.print(playerHand.get(i));
        System.out.println();
      } else {
        System.out.print(playerHand.get(i) + ", ");
      }
    }
    System.out.println("The top card is " + discard.get(0)+".");
    //to do: let player pick if they wanna draw or play, check if card is playable, do shifting, return things if ace.
    return false;
  }//end playerTurn

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
      //stores player names and makes array lists for their cards:
      System.out.println("input player one's name: ");
      String p1Name = scnr.nextLine();
      ArrayList<String> p1Cards = new ArrayList<String>();
      System.out.println("input player two's name: ");
      String p2Name = scnr.nextLine();
      ArrayList<String> p2Cards = new ArrayList<String>();
      System.out.println("input player three's name: ");
      String p3Name = scnr.nextLine();
      ArrayList<String> p3Cards = new ArrayList<String>();
      System.out.println("input player four's name: ");
      String p4Name = scnr.nextLine();
      ArrayList<String> p4Cards = new ArrayList<String>();
      //shuffles the deck and deals the cards to the players:
      shuffleDeck(cardDeck);
      for(int i = 0; i < 7; i++) {
        p1Cards.add(cardDeck[i]);
        p2Cards.add(cardDeck[i+7]);
        p3Cards.add(cardDeck[i+14]);
        p4Cards.add(cardDeck[i+21]);
      }
      //creates the discard pile and draw pile:
      discardPile.add(cardDeck[28]);
      for(int i = 29; i < cardDeck.length; i++) {
        drawPile.add(cardDeck[i]);
      }
      //makes it so that the turns are ordered in the correct way:
      int turnCounter = 1;
      int turnIncrement = 1;
      //goes through the turns:
      while(p1Cards.size()!=0&&p2Cards.size()!=0&&p3Cards.size()!=0&&p4Cards.size()!=0) {
        if(turnCounter >= 5) {
          turnCounter = 1;
        }
        if(turnCounter <= 0) {
          turnCounter = 4;
        }
        boolean a1Played = false, a2Played = false, a3Played = false, a4Played = false;
        switch (turnCounter) {
          case 1:
            a1Played = playerTurn(p1Name, p1Cards, discardPile, drawPile);
            break;
          case 2:
            a2Played = playerTurn(p2Name, p2Cards, discardPile, drawPile);
            break;
          case 3:
            a3Played = playerTurn(p3Name, p3Cards, discardPile, drawPile);
            break;
          case 4:
            a4Played = playerTurn(p4Name, p4Cards, discardPile, drawPile);
            break;
        }

        if(a1Played || a2Played || a3Played || a4Played) {
          turnIncrement*=-1;
        }
        turnCounter+=turnIncrement;

      }
      //prints out victory messages:
      if(p1Cards.size()==0) {
        System.out.println(p1Name + " won!");
      }
      if(p2Cards.size()==0) {
        System.out.println(p2Name + " won!");
      }
      if(p3Cards.size()==0) {
        System.out.println(p3Name + " won!");
      }
      if(p4Cards.size()==0) {
        System.out.println(p4Name + " won!");
      }
      System.out.println("Play again?");
      int continueChoice = scnr.nextInt();
      if(continueChoice==1) {
        break;
      }
    }

  }//end main

}
