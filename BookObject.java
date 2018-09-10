package BookStoreApp;

public class BookObject {
    // Data from input file consists of 3 values
    // Data will be stored in objects to better manage it
    private int bookID;
    private String bookInfo;
    private double bookCost;
    private int bookQuantity;       // How many of this particular item
    private int bulkPercent;

    // ----------------- //
    // Class constructor //
    public BookObject(int bookID, String bookInfo, double bookCost, int bookQuantity, int bulkPercent) {
        super();
        this.bookID = bookID;
        this.bookInfo = bookInfo;
        this.bookCost = bookCost;
        this.bookQuantity = bookQuantity;
        this.bulkPercent = bulkPercent;

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

    public int getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    public int getBulkPercent() {
        return bulkPercent;
    }

    public void setBulkPercent(int bulkPercent) {
        this.bulkPercent = bulkPercent;
    }

}
