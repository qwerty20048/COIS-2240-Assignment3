import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Member> members = new ArrayList<>();
    private List<Book> books = new ArrayList<>();

    public boolean addMember(Member member) { // Prevent duplicate IDs
        if (findMemberById(member.getId()) != null) {
            System.out.println("Member with ID " + member.getId() + " already exists.");
            return false;
        }
        members.add(member);
        return true;
    }

    public boolean addBook(Book book) { // Prevent duplicate IDs
        if (findBookById(book.getId()) != null) {
            System.out.println("Book with ID " + book.getId() + " already exists.");
            return false;
        }
        books.add(book);
        return true;
    }

    public Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Book> getBooks() {
        return books;
    }
}
