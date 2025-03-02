package bookStore;

//import java.util.ArrayList;
//import java.io.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.io.FileWriter;
import java.io.IOException;
public class Owner extends Person{
 // private static Owner instance;
 // public static ArrayList<Book> books;
//public static ArrayList<Customer> customers;
  
  private ObservableList<Book> books = FXCollections.observableArrayList();
  private ObservableList<Customer> customers = FXCollections.observableArrayList();
  private static Scanner c,b;
  private static File book,cust;
  private static Owner instance;
  public Owner(String username, String password) {
    super(username,password);
    //books = new ArrayList<Book>();
    //customers =new ArrayList<Customer>();
    //instance.getBooks();
    //instance.getCustomers();
  }

 public static Owner getOwner(){
    if (instance==null)
     instance = new Owner("admin","admin");
    
    return instance;
  }
  
  @Override
  public String getUsername() {
    return username;
  } 
  @Override
  public String getPassword() {
    return password;
  } 
 // public ArrayList<Book> getBooks(){
    public ObservableList<Book> readBooks(){
    String tempName;
    String tempPrice;
    try{
        try{
        book = new File("books.txt");
        b = new Scanner(book);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            System.exit(0);
        }
        while (b.hasNext()){
            tempName=b.nextLine();        
            tempPrice=b.nextLine();
            boolean add=true;
            for(int i=0; i < books.size(); i++){
                if (tempName.equals(books.get(i).getName()) && Double.parseDouble(tempPrice)==books.get(i).getPrice())
                    add=false;
            }
            if(add==true)
            books.add(new Book(tempName,Double.parseDouble(tempPrice)));
        }
        } catch (NoSuchElementException e) {
            System.out.println("An error occurred.");
            System.exit(0);
        }
    return books;
  }

  public ObservableList<Book> getBooks(){
    return books;
  }
  
  public ObservableList<Customer> getCustomers(){
    return customers;
  }
    
  //public ArrayList<Customer> getCustomers() {
    public ObservableList<Customer> readCustomers() {
    String tempUserName;
    String tempPassword;
    String tempPoints;
    try{
        try{
        cust = new File("customers.txt");
        c = new Scanner(cust);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            System.exit(0);
        } 
        
        while (c.hasNext()){
            tempUserName=c.nextLine();
            tempPassword=c.nextLine();
            tempPoints=c.nextLine();
            boolean add=true;
            for(int i=0; i < customers.size(); i++){
                if (tempUserName.equals(customers.get(i).getUsername()) && tempPassword.equals(customers.get(i).getPassword()) && Integer.parseInt(tempPoints)==customers.get(i).getPoints())
                    add=false;
            }
            if(add==true)
             customers.add(new Customer(tempUserName,tempPassword, Integer.parseInt(tempPoints)));
        }
        } catch (NoSuchElementException e) {
            System.out.println("An error occurred.");
            System.exit(0);
        }
    return customers;
  }
  public void addBook(String n, Double p) {
    boolean add=true;
    for(int i=0; i < books.size(); i++){
        if (n.equals(books.get(i).getName()) && p==books.get(i).getPrice())
            add=false;
        }
    if(add==true)   
        books.add(new Book(n,p));
  } 
  public void removeBook(Book b) {
    //iterate through the books list
    books.remove(b);
  }
  public void addCustomer(String u, String p) {
    boolean add=true;
        for(int i=0; i < customers.size(); i++){
            if (u.equals(customers.get(i).getUsername()))
                add=false;
            }
            if(add==true)  
    customers.add(new Customer(u,p,0));
  }
  public void removeCustomer(Customer c) {
      if(!(customers.remove(c)))
      System.exit(0);
  }
  //NEWLY ADDED

  //save books to the books.txt file
  public void saveBooksToFile() {
        try {
            FileWriter writer = new FileWriter("books.txt");

            for (Book book : books) {
                writer.write(book.getName() + "\n");
                writer.write(book.getPrice() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving books.");
            e.printStackTrace();
        }
    }
  
    //NEWLY ADDED

    // Save customers to the customers.txt file
    public void saveCustomersToFile() {
        try {
            FileWriter writer = new FileWriter("customers.txt");

            for (Customer customer : customers) {
                writer.write(customer.getUsername() + "\n");
                writer.write(customer.getPassword() + "\n");
                writer.write(customer.getPoints() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving customers.");
            e.printStackTrace();
        }
    }
}