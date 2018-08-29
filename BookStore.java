package BookStoreApp;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// --------------------------------------- //
//       TEST START METHOD WITH BUTTON     //
//                                         //
public class BookStore extends Application {

    Button processItem;
    Button confirmItem;
    Button viewOrder;
    Button finishOrder;
    Button newOrder;
    Button exit;
    Stage window;


    // *---------------------------------------* //
    //     Graphical User Interface Objects      //
    //   Default configuration for a new order   //
    // *---------------------------------------* //
    @Override
    public void start(Stage primaryStage) {
        //Parent root = FXMLLoader.load(getClass().getResource("BookStoreApp.fxml"));
        window = primaryStage;
        window.setTitle("My Book Store");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(35,15,15,15));
        grid.setVgap(15);
        grid.setHgap(20);
        grid.setStyle("-fx-background-color: #373737;");

        // --------------------------------------- //
        //               UI LABELS                 //

        // Number of items label
        Label numItemsLabel = new Label("Enter number of items in this order:");
        numItemsLabel.setTextFill(Color.web("#FFFFFF"));
        numItemsLabel.setMinWidth(30);
        GridPane.setConstraints(numItemsLabel, 0, 0);

        // Book ID label
        Label bookID = new Label("Enter Book ID for Item #1:");
        bookID.setTextFill(Color.web("#FFFFFF"));
        GridPane.setConstraints(bookID, 0, 1);

        // Book quantity label
        Label bookQuantity = new Label("Enter quantity for Item #1:");
        bookQuantity.setTextFill(Color.web("#FFFFFF"));
        GridPane.setConstraints(bookQuantity, 0, 2);

        // Book information in catalog Label
        Label infoLabel = new Label("Item #1 info:");
        infoLabel.setTextFill(Color.web("#FFFFFF"));
        GridPane.setConstraints(infoLabel, 0, 4);

        // Order subtotal label
        Label subtotalLabel = new Label("Order subtotal for 0 items(s):");
        subtotalLabel.setTextFill(Color.web("#FFFFFF"));
        GridPane.setConstraints(subtotalLabel, 0, 6);


        // --------------------------------------- //
        //             UI INPUT FIELDS             //

        // Number of items input field
        TextField numItemsField = new TextField();
        GridPane.setConstraints(numItemsField, 1, 0);

        // Book ID input field
        TextField idField = new TextField();
        GridPane.setConstraints(idField, 1, 1);

        // Book quantity input field
        TextField quantField = new TextField();
        GridPane.setConstraints(quantField, 1, 2);

        // Item Info Field
        TextField infoField = new TextField();
        GridPane.setConstraints(infoField, 0, 5);
        infoField.setMinWidth(300);
        infoField.setDisable(true);

        // Subtotal Field
        TextField subtotalField = new TextField();
        GridPane.setConstraints(subtotalField, 0, 7);
        subtotalField.setDisable(true);


        // --------------------------------------- //
        //            UI ACTION EVENTS             //

        processItem = new Button("Process Item");
        GridPane.setConstraints(processItem, 1, 3);
        processItem.setOnAction(e -> alertPopUp("No book ID match", "Enter the correct book ID!", numItemsField));

        confirmItem = new Button("Confirm Item");
        GridPane.setConstraints(confirmItem, 1, 5);
        confirmItem.setDisable(true);

        viewOrder = new Button("View Order");
        GridPane.setConstraints(viewOrder, 1, 7);
        viewOrder.setDisable(true);

        newOrder = new Button("New Order");
        GridPane.setConstraints(newOrder, 1, 8);

        finishOrder = new Button("Finish Order");
        GridPane.setConstraints(finishOrder, 0, 9);
        finishOrder.setOnAction(e -> System.out.println("This is great!"));
        finishOrder.setDisable(true);

        exit = new Button("Exit");
        GridPane.setConstraints(exit, 1, 9);

        // Adding all UI elements to the grid
        grid.getChildren().addAll(numItemsLabel, numItemsField, bookID, idField, bookQuantity, quantField,
                infoLabel, infoField,subtotalLabel, subtotalField, processItem, confirmItem, viewOrder, finishOrder, newOrder, exit);

        // Creating scene and configuring window content.
        Scene scene = new Scene(grid, 440, 500);
        window.setScene(scene);
        window.show();


    }

    // *---------------------------------------* //
    //              Start Program                //
    // *---------------------------------------* //
    public static void main(String[] args) {

        // Read input file containing the book
        // catalog information
        ArrayList<BookObject> bookCatalog = null;
        bookCatalog = (ArrayList<BookObject>) readFile();
        printInput(bookCatalog);                                //

        // Initiate Graphical User Interface
        launch(args);

    }

    // *-------------------------------------------------------* //
    //  Method will read a file named inventory.txt and parse    //
    //  the data for each book and return a list of bookObjects  //
    // *-------------------------------------------------------* //
    public static ArrayList<BookObject> readFile() {

        Scanner scanner = null;
        BookObject bookInstance = null;
        ArrayList<BookObject> bookCatalog = null;

        try {
            // Get a scanner instance and create catalog list
            scanner = new Scanner(new File("C:\\Users\\Isaias\\IdeaProjects\\BookStoreLogger\\src\\BookStoreApp\\inventory.txt"));
            bookCatalog = new ArrayList<BookObject>();


            // Parse input data to book object and add to catalog
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                bookInstance = new BookObject(Integer.parseInt(data[0]),data[1],Double.parseDouble(data[2]));
                bookCatalog.add(bookInstance);

                // Prevents scanner from reading last line with '0\'
                if (!scanner.hasNext()) {
                    break;
                }
            }
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        finally {
            scanner.close();
        }

        return bookCatalog;
    }

    // *-------------------------------------------------------* //
    //      //
    //      //
    // *-------------------------------------------------------* //
    public static void alertPopUp(String title, String message, TextField field) {

        // Stage configuration
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);

        // Alert message
        Label titleLabel = new Label();
        titleLabel.setText(message);
        titleLabel.setTextFill(Color.web("#FFFFFF"));
        GridPane.setConstraints(titleLabel, 0,0);

        // Confirmation button closes window
        // and returns control to the caller
        Button confirm = new Button("OK");
        confirm.setOnAction(e -> window.close());
        GridPane.setConstraints(confirm, 0, 1);

        // Layout
//        VBox layout = new VBox(10);
//        layout.getChildren().addAll(titleLabel, confirm);
//        layout.setAlignment(Pos.CENTER);

        // Layout and style of alert window
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(0,15,15,15));
        grid.setVgap(15);
        grid.setHgap(20);
        grid.setStyle("-fx-background-color: #373737;");
        grid.getChildren().addAll(titleLabel, confirm);
        grid.setAlignment(Pos.CENTER);
        GridPane.setHalignment(confirm, HPos.CENTER);
        GridPane.setValignment(confirm, VPos.CENTER);

        // Content of window
        //Scene scene = new Scene(layout);
        Scene scene = new Scene(grid, 350,200);
        window.setScene(scene);
        window.showAndWait();
        System.out.println(field.getText());
    }










    // --------------------------------------- //
    // Will print the content of an array list //
    // containing the book catalog data        //
    public static void printInput(ArrayList<BookObject> bookCatalog){

        // Iterate through book catalog
        for (int i = 0; i < bookCatalog.size(); i++) {
            System.out.println(bookCatalog.get(i).getBookID() + " " + bookCatalog.get(i).getBookInfo() + " " + bookCatalog.get(i).getBookCost());
        }
    }

    public static void processInput(){
        // Testing reading input ***
        ArrayList<BookObject> bookCatalog = null;
        bookCatalog = (ArrayList<BookObject>) readFile();
        printInput(bookCatalog);
    }

}
