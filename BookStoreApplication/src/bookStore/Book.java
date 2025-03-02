package bookStore;

//import javafx.scene.control.CheckBox;

public class Book{
  private String name;
  private double price;
 // CheckBox selected;
  
  public String getName(){
   return name;
  }

  public double getPrice(){
    return price;
  }

      /*public CheckBox getSelect() {
        return selected;
    }
  */
  public Book(String name, double price){
    this.name = name;
    this.price = price;
    //this.selected = new CheckBox();
  }
}