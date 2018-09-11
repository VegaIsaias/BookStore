package BookStoreApp;

public class BookObject {
    
    private int bookID;             // Number used to search for items in catalog 
    private String bookInfo;        // Info about item in catalog file
    private double bookCost;        // Cost
    private int bookQuantity;       // Object is used for invoice func, stores item qt
    private int bulkPercent;        // Conviniently, stores the total after discount deduction


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
