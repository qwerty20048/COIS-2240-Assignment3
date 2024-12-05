import java.util.Scanner;

public class LibraryManagement {
    private Library library = new Library();
    private Transaction transaction = Transaction.getTransaction(); // Use Singleton

    public static void main(String[] args) {
        new LibraryManagement().run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("===========================");
            System.out.println("Library Management System");
            System.out.println("1. Add Member");
            System.out.println("2. Add Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Borrowed Books");
            System.out.println("6. View Transaction History");
            System.out.println("7. Exit");
            System.out.println("===========================");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter member ID: ");
                    int memberId = scanner.nextInt();
                    System.out.print("Enter member name: ");
                    String name = scanner.next();

                    Member newMember = new Member(memberId, name);
                    if (library.addMember(newMember)) {
                        System.out.println("Member added successfully.");
                    }
                    break;

                case 2:
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                    System.out.print("Enter book title: ");
                    String title = scanner.next();

                    try {
                        Book newBook = new Book(bookId, title);
                        if (library.addBook(newBook)) {
                            System.out.println("Book added successfully.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Enter member ID: ");
                    memberId = scanner.nextInt();
                    System.out.print("Enter book ID: ");
                    bookId = scanner.nextInt();

                    Member member = library.findMemberById(memberId);
                    Book book = library.findBookById(bookId);

                    if (member != null && book != null) {
                        transaction.borrowBook(book, member);
                    } else {
                        System.out.println("Invalid member or book ID.");
                    }
                    break;

                case 4:
                    System.out.print("Enter member ID: ");
                    memberId = scanner.nextInt();
                    System.out.print("Enter book ID: ");
                    bookId = scanner.nextInt();

                    member = library.findMemberById(memberId);
                    book = library.findBookById(bookId);

                    if (member != null && book != null) {
                        transaction.returnBook(book, member);
                    } else {
                        System.out.println("Invalid member or book ID.");
                    }
                    break;

                case 5:
                    System.out.print("Enter member ID: ");
                    memberId = scanner.nextInt();
                    Member specificMember = library.findMemberById(memberId);

                    if (specificMember != null) {
                        System.out.println("Books borrowed by " + specificMember.getName() + ":");
                        for (Book bk : specificMember.getBorrowedBooks()) {
                            System.out.println(" - " + bk.getTitle());
                        }
                    } else {
                        System.out.println("Invalid member ID.");
                    }
                    break;

                case 6:
                    transaction.displayTransactionHistory(); // View transaction history
                    break;

                case 7:
                    System.out.println("Exiting. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
