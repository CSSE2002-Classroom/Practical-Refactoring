package library;

import java.util.*;

public class Library {
    private Map<Integer, String> borrowerNames = new HashMap<>();
    private Map<Integer, List<Integer>> borrowedBooks = new HashMap<>();

    private Map<Integer, String> bookNames = new HashMap<>();
    private Map<Integer, String> bookAuthors = new HashMap<>();
    private Map<Integer, Boolean> availableBooks = new HashMap<>();

    private Map<Integer, Integer> borrowCount = new HashMap<>();

    private int bookId = 0;

    public void newBorrower(int borrowerId, String name) {
        this.borrowerNames.put(borrowerId, name);
        this.borrowedBooks.put(borrowerId, new ArrayList<>());
    }

    public int addBook(String title, String author) {
        int book = bookId;
        bookNames.put(book, title);
        bookAuthors.put(book, author);
        availableBooks.put(book, true);
        borrowCount.put(bookId, 0);
        bookId++;
        return book;
    }

    public boolean borrowBook(int bookId, int borrowerId) {
        if (availableBooks.containsKey(bookId)) {
            if (availableBooks.get(bookId)) {
                availableBooks.put(bookId, false);
                borrowedBooks.get(borrowerId).add(bookId);
                borrowCount.put(bookId, borrowCount.get(bookId) + 1);
                return true;
            }
        }
        return false;
    }

    public void returnBook(int bookId) {
        if (availableBooks.containsKey(bookId)) {
            if (!availableBooks.get(bookId)) {
                availableBooks.put(bookId, true);
                for (Map.Entry<Integer, List<Integer>> borrowed : borrowedBooks.entrySet()) {
                    if (borrowed.getValue().contains(bookId)) {
                        borrowed.getValue().remove(bookId);
                        break;
                    }
                }
            }
        }
    }

    public void displayBooks() {
        System.out.println("Available Books:");
        for (Map.Entry<Integer, Boolean> book : availableBooks.entrySet()) {
            if (!book.getValue()) {
                continue;
            }
            String title = bookNames.get(book.getKey());
            String author = bookAuthors.get(book.getKey());
            System.out.println(title + " by " + author);
        }
        System.out.println("Borrowed Books:");
        for (Map.Entry<Integer, List<Integer>> entry : borrowedBooks.entrySet()) {
            int borrowerId = entry.getKey();
            String borrowerName = borrowerNames.get(borrowerId);
            for (int bookId : entry.getValue()) {
                String title = bookNames.get(bookId);
                String author = bookAuthors.get(bookId);
                System.out.println(title + " by " + author + " (Borrowed by " + borrowerName + ")");
            }
        }
        System.out.println();
    }

    public void report() {
        for (Integer book : availableBooks.keySet()) {
            String title = bookNames.get(book);
            int borrows = borrowCount.get(book);
            System.out.println(title + " has been borrowed " + borrows + " times");
        }
    }

    public static void main(String[] args) {
        Library library = new Library();
        int gatsby = library.addBook("The Great Gatsby", "F. Scott Fitzgerald");
        int mockingBird = library.addBook("To Kill a Mockingbird", "Harper Lee");
        int orwell = library.addBook("1984", "George Orwell");

        library.displayBooks();

        library.newBorrower(14, "Alice");
        library.newBorrower(24, "Bob");
        library.borrowBook(gatsby, 14);
        library.borrowBook(mockingBird, 24);

        library.displayBooks();

        library.returnBook(gatsby);

        library.displayBooks();

        library.borrowBook(gatsby, 14);
        library.returnBook(gatsby);
        library.borrowBook(gatsby, 14);

        library.report();
    }
}
