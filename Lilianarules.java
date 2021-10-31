public static void Liliana2(string card, int player, ArrayList<String> playerHand, ArrayList <String>playershands, ArrayList <String> players){
  if (card.compareTo("2H"||"2D")==0){
    System.out.println("Which player do you want to switch hands with?");
    int player_to_switch = JOptionPane.showOptionDialog(null, "Which player do you want to switch hands with?","(Choose an action)", JOptionPane.DEFAULT_OPTION,
    JOptionPane.QUESTION_MESSAGE, null,
  playershands, playerhands[0]);

  }
  else{}
    ArrayList <String>[] temp = playerhand;
    playerhand = playershands[player_to_switch];
    playershands[player_to_switch] = temp;

}// end of method

public static void Liliana3 (string card, int player, ArrayList <String> playercards){
  if (card.compareTo("3S")==0){
    System.out.println("Which cards do you want to discard? please separate your choices with a comma ");
    System.out.println(playercards);
    int number = Integer.parseInt(JOptionPane.showInputDialog(
    null, "Which cards do you want to discard?", "Please separate your choices with a comma.", JOptionPane.QUESTION_MESSAGE
    ));
    String remove[] = {input.split(",")};
  }
  else{}
    playercards.remove(remove);
    discard.add(remove);

}
