package bookStore;

abstract class Person{
  public String username;
  public String password;
  
  public Person(String username, String password) {
    this.username=username;
    this.password=password;
  }
  public abstract String getUsername();
  public abstract String getPassword();


}