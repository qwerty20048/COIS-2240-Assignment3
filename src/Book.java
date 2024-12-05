public class Book {
    private int id;
    private String title;
    private boolean available;

    public Book(int id, String title) throws Exception { // Updated constructor to validate IDs
        if (!isValidId(id)) {
            throw new Exception("Invalid book ID: " + id);
        }
        this.id = id;
        this.title = title;
        this.available = true;
    }

    public boolean isValidId(int id) { // Method to check if the book ID is valid
        return id >= 100 && id <= 999;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrowBook() {
        if (available) {
            available = false;
        }
    }

    public void returnBook() {
        available = true;
    }
}
