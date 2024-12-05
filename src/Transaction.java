import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private static Transaction instance; // Singleton instance

    private Transaction() {} // Private constructor

    public static Transaction getTransaction() { // Getter for Singleton instance
        if (instance == null) {
            instance = new Transaction();
        }
        return instance;
    }

    public boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book);
            System.out.println(member.getName() + " borrowed " + book.getTitle());
            return true;
        } else {
            System.out.println("The book is not available.");
            return false;
        }
    }

    public void returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            System.out.println(member.getName() + " returned " + book.getTitle());
        } else {
            System.out.println("This book was not borrowed by the member.");
        }
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
