package BookStoreApp;

public class BookObject {
    // Data from input file consists of 3 values
    // Data will be stored in objects to better manage it
    private int bookID;
    private String bookInfo;
    private double bookCost;


    // ----------------- //
    // Class constructor //
    public BookObject(int bookID, String bookInfo, double bookCost) {
        super();
        this.bookID = bookID;
        this.bookInfo = bookInfo;
        this.bookCost = bookCost;
    }


    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(String bookInfo) {
        this.bookInfo = bookInfo;
    }

    public double getBookCost() {
        return bookCost;
    }

    public void setBookCost(double bookCost) {
        this.bookCost = bookCost;
    }


}
