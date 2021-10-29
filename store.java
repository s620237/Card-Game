


class store{
  public static void main(String[] args) {
    SkewItem miscItem = new SkewItem();
    SkewItem bike1 = new SkewItem("Trek");
    SkewItem bike2 = new SkewItem("Specalized", 2400.00);
    ProduceItem drink1 = new ProduceItem();

    miscItem.setPrice(10.00);
    miscItem.printPrice();

    bike1.setPrice(1200.00);
    bike1.printPrice();

    bike2.printPrice();

    drink1.setPrice(1.00);
    drink1.setName("milk");
    drink1.setExDate("Tomorrow");
    drink1.printPrice();
    System.out.println("The expiration date is: " + drink1.printExDate());

  }//end main

}// end class
