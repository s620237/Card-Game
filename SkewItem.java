public class SkewItem{
  private String itemName;
  private double price;

  SkewItem(){
    itemName = "Name not set";
  }

  SkewItem(String n){
    itemName = n;
  }

  SkewItem(String n, double p){
    itemName = n;
    price = p;


  }

  public void setPrice(double p){
    price = p;
  }

  public void setName(String n){
    itemName = n;
  }


  public void printPrice(){
    System.out.println(itemName + " price is " + price);
  }





}//end class
