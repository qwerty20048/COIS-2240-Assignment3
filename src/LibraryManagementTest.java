import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class LibraryManagementTest {

    private Transaction transaction;
    private Member member;
    private Book book;

    @Before
    public void setUp() throws Exception {
        transaction = Transaction.getTransaction();
        member = new Member(1, "Test Member");
        book = new Book(200, "Test Book");
    }

    @Test
    public void testBookId() throws Exception {
        // Valid IDs
        Book validBook1 = new Book(100, "Valid Book 1");
        Book validBook2 = new Book(999, "Valid Book 2");

        // Assertions for valid IDs
        assertEquals(100, validBook1.getId());
        assertEquals(999, validBook2.getId());

        // Invalid IDs
        try {
            new Book(99, "Invalid Book 1");
            fail("Exception not thrown for ID less than 100.");
        } catch (Exception e) {
            assertEquals("Invalid book ID: 99", e.getMessage());
        }

        try {
            new Book(1000, "Invalid Book 2");
            fail("Exception not thrown for ID greater than 999.");
        } catch (Exception e) {
            assertEquals("Invalid book ID: 1000", e.getMessage());
        }
    }

    @Test
    public void testBorrowReturn() {
        assertTrue(book.isAvailable());

        // Borrow the book
        assertTrue(transaction.borrowBook(book, member));
        assertFalse(book.isAvailable());

        // Attempt to borrow the book again
        assertFalse(transaction.borrowBook(book, member));

        // Return the book
        assertTrue(transaction.returnBook(book, member));
        assertTrue(book.isAvailable());

        // Attempt to return the book again
        assertFalse(transaction.returnBook(book, member));
    }

    @Test
    public void testSingletonTransaction() throws Exception {
        Constructor<Transaction> constructor = Transaction.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));

        // Attempt to create a new instance using reflection
        constructor.setAccessible(true);
        try {
            Transaction newTransaction = constructor.newInstance();
            fail("Singleton violated: Multiple instances created.");
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof IllegalStateException);
            assertEquals("Cannot create another instance of Transaction. Use getTransaction() method.", e.getCause().getMessage());
        }
    }
}
