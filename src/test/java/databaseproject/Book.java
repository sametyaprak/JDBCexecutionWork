package databaseproject;

public class Book {

    int bookId;
    String bookName;
    String writerName;
    int bookPrice;

    public Book(int bookId, String bookName, String writerName, int bookPrice) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.writerName = writerName;
        this.bookPrice = bookPrice;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getWriterName() {
        return writerName;
    }

    public int getBookPrice() {
        return bookPrice;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", writerName='" + writerName + '\'' +
                ", bookPrice=" + bookPrice +
                '}'+"\n";
    }
}
