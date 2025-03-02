package bookStore;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.Cell;
import javafx.stage.WindowEvent;
import javafx.beans.property.BooleanProperty;

public class NewMain extends Application 
{ 
        
         ObservableList<Book> listBooks =
            FXCollections.observableArrayList(Owner.getOwner().readBooks());
           
         ObservableList<Customer> listCustomers =
            FXCollections.observableArrayList(Owner.getOwner().readCustomers());

          Customer user;
  
  @Override
  public void start(Stage primaryStage) {

    //Login Scene Elements
    Label lblWelcome = new Label("Welcome to the Bookstore App");
    lblWelcome.setFont(new Font("Arial", 12));
    
    TextField tfUser = new TextField();
    tfUser.setPromptText("Username");
    tfUser.setMaxWidth(200);
    
    PasswordField pfPass = new PasswordField();
    pfPass.setPromptText("Password");
    pfPass.setMaxWidth(200);

    Button btnLogin = new Button("Login");
    
    VBox loginPane;
    Scene loginScreen;
    HBox paneUser = new HBox(new Label("Username: "),tfUser);;
    HBox panePass = new HBox(new Label("Password: "),pfPass);;

    paneUser.setAlignment(Pos.CENTER);
    panePass.setAlignment(Pos.CENTER);
    paneUser.setSpacing(10);
    panePass.setSpacing(10);
    loginPane = new VBox(lblWelcome, paneUser, panePass, btnLogin);
    loginPane.setSpacing(10);
    loginPane.setAlignment(Pos.CENTER);
    
    loginScreen = new Scene(loginPane, 300, 200);

    
    //Owner Start Screen
    Button btnBooks = new Button("Books");
    btnBooks.setPrefHeight(35);
    btnBooks.setPrefWidth(100);
    
    Button btnCustomers = new Button("Customers");
    btnCustomers.setPrefHeight(35);
    btnCustomers.setPrefWidth(100);
    
    Button btnLogout1 = new Button("Logout");
    btnLogout1.setPrefHeight(35);
    btnLogout1.setPrefWidth(100);
    
    VBox menuOwner = new VBox(btnBooks,btnCustomers,btnLogout1);
    menuOwner.setSpacing(15);
    menuOwner.setAlignment(Pos.CENTER);

    Scene menuOwnerScreen = new Scene(menuOwner,300,200);

    //Owner Books Screen

    //Top
    TableColumn colBookName1 = new TableColumn("Book Name");
    colBookName1.setCellValueFactory(
            new PropertyValueFactory<Book, String>("name")
    );
    
    TableColumn colBookPrice1 = new TableColumn("Book Price");
    colBookPrice1.setCellValueFactory(
      new PropertyValueFactory<Book, Double>("price")
    );
    

    
    TableView<Book> tblBook = new TableView<Book>();
    tblBook.setItems(listBooks);
    tblBook.getColumns().addAll(colBookName1,colBookPrice1);


    //Middle
    TextField tfBookName = new TextField();
    tfBookName.setPromptText("Book Name");

    TextField tfBookPrice  = new TextField();
    tfBookPrice.setPromptText("Book Price");

    Button btnAddBook = new Button("Add");
    
    //Bottom
    Button btnDeleteBook = new Button("Delete");
    Button btnBack1 = new Button("Back");

    //Scene
    HBox paneAddBook = new HBox(tfBookName,tfBookPrice,btnAddBook);
    paneAddBook.setSpacing(5);
    
    HBox paneDeleteBook = new HBox(btnDeleteBook,btnBack1);
    paneDeleteBook.setSpacing(5);
    
    VBox paneBookOwner = new VBox(tblBook,paneAddBook,paneDeleteBook);
    paneBookOwner.setSpacing(10);
    paneBookOwner.setPadding(new Insets(10, 10, 10, 10));
    tblBook.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

    Scene ownerBookScreen = new Scene(paneBookOwner,500,300);
    
    //Owner Customer Screen
    
    //Top
    TableColumn colUsername = new TableColumn("Username");
    colUsername.setMinWidth(150);
    colUsername.setCellValueFactory(
            new PropertyValueFactory<Customer, String>("username")
    );
    
    TableColumn colPassword = new TableColumn("Password");
    colPassword.setMinWidth(150);
    colPassword.setCellValueFactory(
      new PropertyValueFactory<Customer, String>("password")
    );

    TableColumn colPoints = new TableColumn("Points");
    colPoints.setMinWidth(150);
    colPoints.setCellValueFactory(
      new PropertyValueFactory<Customer, Integer>("points")
    );

    
    TableView<Customer> tblCustomer = new TableView<Customer>();
    tblCustomer.setItems(listCustomers);
    tblCustomer.getColumns().addAll(colUsername,colPassword,colPoints);
    
    //Middle
    TextField tfUsername = new TextField();
    tfUsername.setPromptText("Username");

    TextField tfPassword  = new TextField();
    tfPassword.setPromptText("Password");

    Button btnAddCustomer = new Button("Add");
    
    //Bottom
    Button btnBack2 = new Button("Back");
    Button btnDeleteCustomer = new Button("Delete");
    //Use same back button from book owner screen

    //Scene
    HBox paneAddCustomer = new HBox(tfUsername,tfPassword,btnAddCustomer);
    paneAddCustomer.setSpacing(5);
    
    HBox paneDeleteCustomer = new HBox(btnDeleteCustomer,btnBack2);
    paneDeleteCustomer.setSpacing(5);
    
    VBox paneCustomerOwner = new VBox(tblCustomer,paneAddCustomer,paneDeleteCustomer);
    paneCustomerOwner.setSpacing(10);
    paneCustomerOwner.setPadding(new Insets(10, 10, 10, 10));
    tblCustomer.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

    Scene ownerCustomerScreen = new Scene(paneCustomerOwner,500,300);

    //Customer Start Screen

    //Top 
    Label lblMessage = new Label("Welcome Person. You have P points. Your status is S");

    //Middle
    TableView<Book> tblBuyBook = new TableView<Book>();
    tblBuyBook.setEditable(true);
    TableColumn colBookName2 = new TableColumn("Book Name");
    colBookName2.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
    
    TableColumn colBookPrice2 = new TableColumn("Book Price");
    colBookPrice2.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
    
    TableColumn colSelectBook = new TableColumn("Select");
    colSelectBook.setCellFactory(CheckBoxTableCell.forTableColumn(colSelectBook));
    
    //colSelectBook.setCellValueFactory(tempBooks-> );
    
    //colSelectBook.setCellValueFactory(new PropertyValueFactory<Book, CheckBox>("selected"));
    tblBuyBook.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
    tblBuyBook.setItems(listBooks);
    tblBuyBook.getColumns().addAll(colBookName2,colBookPrice2,colSelectBook);
    
    //Bottom
    Button btnBuy = new Button("Buy");
    Button btnRedeem = new Button("Redeem and Buy");
    
    Button btnLogout2 = new Button("Logout");
    btnLogout2.setPrefHeight(35);
    btnLogout2.setPrefWidth(100);
   
    //Scene

    HBox paneBuy = new HBox(btnBuy,btnRedeem,btnLogout2);
    paneBuy.setSpacing(10);
    
    VBox paneBookCustomer = new VBox(lblMessage,tblBuyBook,paneBuy);
    paneBookCustomer.setSpacing(10);
    paneBookCustomer.setPadding(new Insets(10, 10, 10, 10));
    Scene customerBookScreen = new Scene(paneBookCustomer, 500,300); 
    
    //Customer Cost Screen

    Label lblTC = new Label("Total Cost: $1");
    Label lblBuyMsg = new Label("Points: P, Status: S");
    
    Button btnLogout3 = new Button("Logout");
    btnLogout3.setPrefHeight(35);
    btnLogout3.setPrefWidth(100);
    
    VBox paneCost = new VBox(lblTC,lblBuyMsg,btnLogout3);
    paneCost.setSpacing(10);
    paneCost.setAlignment(Pos.CENTER);
    Scene customerTCScreen = new Scene(paneCost,300,200); 
    
    //Event Handlers    
    btnLogin.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        String username = tfUser.getText();
        String password = pfPass.getText();
        user = validateCustomerCreds(username, password); 
        if (username.equals("admin") && password.equals("admin")){
         
          tfUser.clear();
          pfPass.clear();
        // Navigate to the owner menu scene
          primaryStage.setScene(menuOwnerScreen);
        } else if(user != null) {
          //if a customer is found
          lblMessage.setText("Welcome " + user.getUsername() +" You have " + user.getPoints() + " points. Your status is " + user.getStatus());
          //Clear the text fields
          tfUser.clear();
          pfPass.clear();
         
         //navigate to the customer menu scene
          primaryStage.setScene(customerBookScreen);
         }else{
            // if no matching customer is found, show an error message
            System.out.println("Error");
         }
        }
    });

    btnBooks.setOnAction(new EventHandler<ActionEvent>(){
      @Override public void handle(ActionEvent e){
        primaryStage.setScene(ownerBookScreen);
      }
    });
    
    btnCustomers.setOnAction(new EventHandler<ActionEvent>(){
      @Override public void handle(ActionEvent e){
        primaryStage.setScene(ownerCustomerScreen);
      }
    });
    
    btnLogout1.setOnAction(new EventHandler<ActionEvent>(){
     @Override public void handle(ActionEvent e){
       //save data to the text file before logging out 
       saveData();
       //Navigate back to login scene
       primaryStage.setScene(loginScreen);
     }
    });
    
    btnLogout2.setOnAction(new EventHandler<ActionEvent>(){
     @Override public void handle(ActionEvent e){
       //save data to the text file before logging out 
       saveData();
       //Navigate back to login scene
       primaryStage.setScene(loginScreen);
     }
    });

    btnLogout3.setOnAction(new EventHandler<ActionEvent>(){
     @Override public void handle(ActionEvent e){
       //save data to the text file before logging out 
       saveData();
       //Navigate back to login scene
       primaryStage.setScene(loginScreen);
     }
    });

    
    btnBack1.setOnAction(new EventHandler<ActionEvent>(){
     @Override public void handle(ActionEvent e){
       primaryStage.setScene(menuOwnerScreen);
     }
    });
    
    btnBack2.setOnAction(new EventHandler<ActionEvent>(){
     @Override public void handle(ActionEvent e){
       primaryStage.setScene(menuOwnerScreen);
     }
    });

   
    btnAddBook.setOnAction(new EventHandler<ActionEvent>(){
      @Override public void handle(ActionEvent e){
      Owner.getOwner().addBook(tfBookName.getText(),Double.parseDouble(tfBookPrice.getText()));
      listBooks = FXCollections.observableArrayList(Owner.getOwner().getBooks());
      tblBook.setItems(listBooks);
      tblBuyBook.setItems(listBooks);
      tfBookName.clear();
      tfBookPrice.clear();
      }
    });

    btnDeleteBook.setOnAction(new EventHandler<ActionEvent>(){
      @Override public void handle(ActionEvent e){
      Owner.getOwner().removeBook(tblBook.getSelectionModel().getSelectedItem());
      listBooks = FXCollections.observableArrayList(Owner.getOwner().getBooks());
      tblBook.setItems(listBooks);
      tblBuyBook.setItems(listBooks);
      }
    });

    btnAddCustomer.setOnAction(new EventHandler<ActionEvent>(){
      @Override public void handle(ActionEvent e){
        Owner.getOwner().addCustomer(tfUsername.getText(),tfPassword.getText());
        listCustomers = FXCollections.observableArrayList(Owner.getOwner().getCustomers());
        tblCustomer.setItems(listCustomers);
        tfUsername.clear();
        tfPassword.clear();
      }
    });

    btnDeleteCustomer.setOnAction(new EventHandler<ActionEvent>(){
      @Override public void handle(ActionEvent e){
      Owner.getOwner().removeCustomer(tblCustomer.getSelectionModel().getSelectedItem());
      listCustomers = FXCollections.observableArrayList(Owner.getOwner().getCustomers());
      tblCustomer.setItems(listCustomers);
      }
    });

    btnBuy.setOnAction(new EventHandler<ActionEvent>(){
      @Override public void handle(ActionEvent e){
      //  primaryStage.setScene(customerTCScreen);
        boolean BSelected = false;
        for(Book b : Owner.getOwner().getBooks()){
            
          //if(b.getSelect().isSelected()){
            BSelected = true;
          //}
        }
        if(BSelected){
          primaryStage.setScene(customerTCScreen);
        }
        else{
          primaryStage.setScene(customerBookScreen);
        }
        
      }
    });

 btnRedeem.setOnAction(new EventHandler<ActionEvent>(){
      @Override public void handle(ActionEvent e){
              //  primaryStage.setScene(customerTCScreen);
        boolean BSelected = false;
        for(Book b : Owner.getOwner().getBooks()){
         // if(b.getSelect().isSelected()){
            BSelected = true;
          //}
        }
        if(BSelected && user.getPoints() != 0){
          primaryStage.setScene(customerTCScreen);
        }
        else{
          primaryStage.setScene(customerBookScreen);
        }
        
      }
    });

    //save data when pressing [x]
    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
    @Override
    public void handle(WindowEvent event) {
        saveData();
    }
});
    
    //Initial Scene
    primaryStage.setTitle("Bookstore App");
    primaryStage.setScene(loginScreen);
    primaryStage.show();
  } 

  //saving data to the files
   private void saveData() {
    Owner.getOwner().saveBooksToFile();
    Owner.getOwner().saveCustomersToFile();
}
  

// Method is used for checking if the given username and password match any customer in the list of customers 

  private Customer validateCustomerCreds(String username, String password){
  //Iterating through the list of customers
    for (Customer customer : Owner.getOwner().getCustomers()){
    // If the given username and password match the current customer, return the customer object
      if (customer.getUsername().equals(username) && customer.getPassword().equals(password)){
      return customer;
      }
    }
    //if no customer is found within the given creds, it will return null
    return null;
  }

  
  public static void main(String[] args) {
    launch(args);
  }
} 
