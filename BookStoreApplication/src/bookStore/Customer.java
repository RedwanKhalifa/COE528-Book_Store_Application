package bookStore;

public class Customer extends Person {
  private int points;
  private String status;
  public Customer(String username, String password, int points) {
    super(username,password);
    this.points = points;
  }
  @Override
  public String getUsername() {
    return username;
  } 
  @Override
  public String getPassword() {
    return password;
  } 
  public int getPoints() {
    return points; 
  }


  public String getStatus() {
    if (points >= 1000) {
      status = "Gold";
    } else {
      status = "Silver";
    }
    return status;
  }
  public double buyWithPoints(double price){
    double cost = price;
    int disc = (int)cost * 100;
    if (points >= disc){
      cost = 0;
      points= points - disc;
    } else {
      cost = (disc-points) / 100;
      points = (int)cost * 10;
    }
    return cost;
  }
  public double buy(double price){
    double cost = price; 
    points = points + (int)cost * 10;
    return cost;
  }
}