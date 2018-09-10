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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// --------------------------------------- //
//       TEST START METHOD WITH BUTTON     //
//                                         //
public class BookStore extends Application {

    private static Button processItem;
    private static Button confirmItem;
    private static Button viewOrder;
    private static Button finishOrder;
    private static Button newOrder;
    private static Button exit;
    private static Stage window;

    private static Label numItemsLabel;    private static TextField numItemsField;
    private static Label bookID;           private static TextField idField;
    private static Label bookQuantity;     private static TextField quantField;
    private static Label infoLabel;        private static TextField infoField;
    private static Label subtotalLabel;    private static TextField subtotalField;

    private static int totalItems;
    private static int currentItem;
    private static int numOfCurrentItem;
    private static double orderSubtotal;
    private static int percent;
    private static int index;
    private static ArrayList<BookObject> bookCatalog;
    private static ArrayList<Integer> invoiceItemsLocs;


    // *---------------------------------------* //
    //     Graphical User Interface Objects      //
    //   Default configuration for a new order   //
    // *---------------------------------------* //
    @Override
    public void start(Stage primaryStage) {
        //Parent root = FXMLLoader.load(getClass().getResource("BookStoreApp.fxml"));
        currentItem = 1;
        totalItems = 0;
        window = primaryStage;
        bookCatalog = (ArrayList<BookObject>) readFile();
        invoiceItemsLocs = new ArrayList<Integer>();


        window.setTitle("My Book Store");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(35,15,15,15));
        grid.setVgap(15);
        grid.setHgap(20);
        grid.setStyle("-fx-background-color: #373737;");

        // --------------------------------------- //
        //               UI LABELS                 //

        // Number of items label
        numItemsLabel = new Label("Enter number of items in this order:");
        numItemsLabel.setTextFill(Color.web("#FFFFFF"));
        numItemsLabel.setMinWidth(40);
        GridPane.setConstraints(numItemsLabel, 0, 0);

        // Book ID label
        bookID = new Label("Enter Book ID for Item #" + currentItem + ":");
        bookID.setTextFill(Color.web("#FFFFFF"));
        GridPane.setConstraints(bookID, 0, 1);

        // Book quantity label
        bookQuantity = new Label("Enter quantity for Item #" + currentItem + ":");
        bookQuantity.setTextFill(Color.web("#FFFFFF"));
        GridPane.setConstraints(bookQuantity, 0, 2);

        // Book information in catalog Label
        infoLabel = new Label("Item #" + currentItem + " info:");
        infoLabel.setTextFill(Color.web("#FFFFFF"));
        GridPane.setConstraints(infoLabel, 0, 4);

        // Order subtotal label
        subtotalLabel = new Label("Order subtotal for " + (currentItem - 1) + " items(s):");
        subtotalLabel.setTextFill(Color.web("#FFFFFF"));
        GridPane.setConstraints(subtotalLabel, 0, 6);


        // --------------------------------------- //
        //             UI INPUT FIELDS             //

        // Number of items input field
        numItemsField = new TextField();
        GridPane.setConstraints(numItemsField, 1, 0);

        // Book ID input field
        idField = new TextField();
        GridPane.setConstraints(idField, 1, 1);

        // Book quantity input field
        quantField = new TextField();
        GridPane.setConstraints(quantField, 1, 2);

        // Item Info Field
        infoField = new TextField();
        GridPane.setConstraints(infoField, 0, 5);
        infoField.setMinWidth(350);
        infoField.setDisable(true);

        // Subtotal Field
        subtotalField = new TextField();
        GridPane.setConstraints(subtotalField, 0, 7);
        subtotalField.setDisable(true);




        // --------------------------------------- //
        //            UI ACTION EVENTS             //

        processItem = new Button("Process Item #" + currentItem);
        GridPane.setConstraints(processItem, 1, 3);
        processItem.setText("Process Item #" + currentItem);
        processItem.setOnAction(e -> {

              processItems();

        });

        confirmItem = new Button("Confirm Item #" + currentItem);
        GridPane.setConstraints(confirmItem, 1, 5);
        confirmItem.setOnAction(e -> {

            confirmItem();

        });
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
        exit.setOnAction(e -> window.close());
        GridPane.setConstraints(exit, 1, 9);

        // Adding all UI elements to the grid
        grid.getChildren().addAll(numItemsLabel, numItemsField, bookID, idField, bookQuantity, quantField,
                infoLabel, infoField,subtotalLabel, subtotalField, processItem, confirmItem, viewOrder, finishOrder, newOrder, exit);

        // Creating scene and configuring window content.
        Scene scene = new Scene(grid, 515, 500);
        window.setScene(scene);
        window.show();

        //System.out.println("items: " + totalItems + " size: " + itemQuantity.size());
        //changeValue();
        //System.out.println("items: " + totalItems + " size: " + itemQuantity.size() + " arr(0): " + itemQuantity.get(0));
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

    // *-------------------------------------------------------* //
    public static void viewOrder() {

    }

    // *-------------------------------------------------------* //

    // *-------------------------------------------------------* //
    public static void processItems() {

        // Check that all text fields have data to read, if a field is empty
        // output a messege to the user
        if (idField.getText().isEmpty() || quantField.getText().isEmpty() || numItemsField.getText().isEmpty()) {
            alertPopUp("Missing Information", "There are empty fields, please fill all fields to proceed.");
            return;
        }

        // Seach for location of itemID in catalog
        index = searchItemID(Integer.parseInt(idField.getText()),bookCatalog);

        // If item was found, process it and update UI
        if (index != -1) {

            // If this is the first item to process successfully,
            // disable totalItems text field
            if (currentItem == 1) {
                numItemsField.setDisable(true);

                // Store number of items in order
                totalItems = Integer.parseInt(numItemsField.getText());
            }

            // Check if item count does not exceed totalItems
            if (currentItem > totalItems ) {
                // add alert
            }

            // Storing book quantity info
            bookCatalog.get(index).setBookQuantity(Integer.parseInt(quantField.getText()));
            bookCatalog.get(index).setBulkPercent(findPercent(bookCatalog.get(index).getBookQuantity()));

            // Display item information and calculated cost and discount percentage
            String discount = bookCatalog.get(index).getBookID() + " ''" + bookCatalog.get(index).getBookInfo()
                    + "' $" + bookCatalog.get(index).getBookCost() + " " + bookCatalog.get(index).getBulkPercent() + "% $" + calcBulkDiscount();
            infoField.setText(discount);



            // Enable/Disable buttons for confirmation process
            confirmItem.setDisable(false);
            processItem.setDisable(true);

            // *****************
            // Disable item info text fields
            idField.setDisable(true);
            quantField.setDisable(true);


        } else {

            // The item was not found in the catalog, outpuf msg to user
            alertPopUp("Message Failure", "Book ID " + idField.getText() + " not in file");
        }

    }

    // *-------------------------------------------------------* //
    //  Search for item ID inside book catalog, return the       //
    //  location if found, otherwise return -1                   //
    // *-------------------------------------------------------* //
    public static int searchItemID(int itemID, ArrayList<BookObject> bookCatalog) {

        index = -1;

        for (int i = 0; i < bookCatalog.size(); i++) {

            // Matched ID to one in catalog
            if (itemID == bookCatalog.get(i).getBookID()) {
                index = i;
            }
        }

        return index;
    }


    // *-------------------------------------------------------* //

    // *-------------------------------------------------------* //
    public static int findPercent(int numItems) {

        // Determine discount percent
        if (numItems < 5 && numItems > 0) {
            percent = 0;
        } else if (numItems > 4 && numItems < 10) {
            percent = 10;
        } else if (numItems > 9 && numItems < 15) {
            percent = 15;
        } else {
            percent = 20;
        }

        return percent;
    }

    // *-------------------------------------------------------* //
    //  Confirm Item - Function wil add item to transaction log  //
    //  it will activate process functionality upon success      //
    // *-------------------------------------------------------* //
    public static void confirmItem() {

        // Store item information for invoice creation
        //invoiceItemsLocs.add(index);
        //itemQuantity.add(Integer.parseInt(numItemsField.getText()));
        invoiceItemsLocs.add(index);

        // Calculate subtotal of order
        orderSubtotal = orderSubtotal + calcBulkDiscount();

        currentItem++;

        // Update labels for next item
        subtotalLabel.setText("Order subtotal for " + (currentItem - 1) + " items(s):");
        infoLabel.setText("Item #" + (currentItem - 1) + " info:");
        bookID.setText("Enter Book ID for Item #" + currentItem + ":");
        bookQuantity.setText("Enter quantity for Item #" + currentItem + ":");
        processItem.setText("Process Item #" + (currentItem));
        confirmItem.setText("Confirm Item #" + currentItem);

        // Update subtotal field
        subtotalField.setText("$" + orderSubtotal);

        // Clear input fields for next item
        idField.clear();
        quantField.clear();

        // Enable / Disable buttons
        processItem.setDisable(false);
        confirmItem.setDisable(true);
        idField.setDisable(false);
        quantField.setDisable(false);
        viewOrder.setDisable(false);

        // Let the user know the item was accepted
        alertPopUp("Successful", "Item #" + (currentItem - 1) + " accepted.");


        // Check if this was the last item to be entered
        // If it is, don't allow anymore items to be entered and allow for user to finish process
        if ((currentItem -1) == totalItems) {
            idField.setDisable(true);
            quantField.setDisable(true);
            processItem.setDisable(true);
            confirmItem.setDisable(true);
            confirmItem.setText("Confirm Item");
            processItem.setText("Process Item");
            finishOrder.setDisable(false);
            bookID.setText("");
            bookQuantity.setText("");

        }

    }


    // *-------------------------------------------------------* //

    // *-------------------------------------------------------* //
    public static double calcBulkDiscount() {

        double cost;
        int numItems;

        // If this is
        numItems = bookCatalog.get(index).getBookQuantity();

        // Determine discount percent
        if (numItems < 5 && numItems > 0) {
            percent = 0;
        } else if (numItems > 4 && numItems < 10) {
            percent = 10;
        } else if (numItems > 9 && numItems < 15) {
            percent = 15;
        } else {
            percent = 20;
        }

        System.out.println("book cost: " + bookCatalog.get(index).getBookCost() + " nuItems: " + numItems + " percent:" + percent);
        cost = round(((bookCatalog.get(index).getBookCost()) * numItems * percent) / 10, 2);

        return cost;
    }


    // *-------------------------------------------------------* //

    // *-------------------------------------------------------* //
    public static String calcDiscount(double cost, int numItemsTotal) {

        String text;

        // Determine discount percent
        if (numItemsTotal < 5 && numItemsTotal > 0) {
            percent = 0;
        } else if (numItemsTotal > 4 && numItemsTotal < 10) {
            percent = 10;
        } else if (numItemsTotal > 9 && numItemsTotal < 15) {
            percent = 15;
        } else {
            percent = 20;
        }


        if (percent > 0) {
            text = "$" + cost + " " + percent + "% $" + round((cost * percent) / 10, 2);
        } else {
            text = "$" + cost + " " + percent + "% $" + cost;
        }

        return text;
    }

    // *-------------------------------------------------------* //

    // *-------------------------------------------------------* //
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
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
                bookInstance = new BookObject(Integer.parseInt(data[0]),data[1],Double.parseDouble(data[2]), 0, 0);
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
    public static void alertPopUp(String title, String message) {

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
        grid.setPadding(new Insets(75,15,40,15));
        grid.setVgap(90);
        grid.setHgap(20);
        grid.setStyle("-fx-background-color: #373737;");
        grid.getChildren().addAll(titleLabel, confirm);
        grid.setAlignment(Pos.CENTER);
        GridPane.setHalignment(confirm, HPos.CENTER);
        GridPane.setValignment(confirm, VPos.CENTER);

        // Content of window
        //Scene scene = new Scene(layout);
        Scene scene = new Scene(grid, 420,220);
        window.setScene(scene);
        window.showAndWait();
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
