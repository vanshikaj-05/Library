import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class User {
    private String name;
    private ArrayList<Book> borrowedBooks;

    public User(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
}

public class LibraryManagementSystem {
    private HashMap<String, Book> books;
    private HashMap<String, User> users;
    private Scanner scanner;

    public LibraryManagementSystem() {
        books = new HashMap<>();
        users = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void addBook(String title, String author) {
        books.put(title, new Book(title, author));
    }

    public void addUser(String name) {
        users.put(name, new User(name));
    }

    public void displayBooks() {
        System.out.println("Available Books:");
        for (Book book : books.values()) {
            if (book.isAvailable()) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        }
    }

    public void borrowBook(String userName, String bookTitle) {
        if (!books.containsKey(bookTitle)) {
            System.out.println("Book not found.");
            return;
        }

        Book book = books.get(bookTitle);
        if (!book.isAvailable()) {
            System.out.println("Book is not available.");
            return;
        }

        if (!users.containsKey(userName)) {
            System.out.println("User not found.");
            return;
        }

        User user = users.get(userName);
        user.borrowBook(book);
        book.setAvailable(false);
        System.out.println("Book borrowed successfully.");
    }

    public void returnBook(String userName, String bookTitle) {
        if (!books.containsKey(bookTitle)) {
            System.out.println("Book not found.");
            return;
        }

        Book book = books.get(bookTitle);
        if (book.isAvailable()) {
            System.out.println("Book is already available.");
            return;
        }

        if (!users.containsKey(userName)) {
            System.out.println("User not found.");
            return;
        }

        User user = users.get(userName);
        user.returnBook(book);
        book.setAvailable(true);
        System.out.println("Book returned successfully.");
    }

    public void generateFine(String userName) {
        if (!users.containsKey(userName)) {
            System.out.println("User not found.");
            return;
        }

        User user = users.get(userName);
        ArrayList<Book> borrowedBooks = user.getBorrowedBooks();
        int fineAmount = borrowedBooks.size() * 10; // $10 fine per book
        System.out.println("Fine for " + userName + ": $" + fineAmount);
    }

    public static void main(String[] args) {
        LibraryManagementSystem librarySystem = new LibraryManagementSystem();
        librarySystem.addBook("Book1", "Author1");
        librarySystem.addBook("Book2", "Author2");
        librarySystem.addUser("User1");
        librarySystem.addUser("User2");

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Library Management System");
            System.out.println("1. Display Available Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Generate Fine");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    librarySystem.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter user name: ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter book title: ");
                    String bookTitle = scanner.nextLine();
                    librarySystem.borrowBook(userName, bookTitle);
                    break;
                case 3:
                    System.out.print("Enter user name: ");
                    userName = scanner.nextLine();
                    System.out.print("Enter book title: ");
                    bookTitle = scanner.nextLine();
                    librarySystem.returnBook(userName, bookTitle);
                    break;
                case 4:
                    System.out.print("Enter user name: ");
                    userName = scanner.nextLine();
                    librarySystem.generateFine(userName);
                    break;
                case 5:
                    System.out.println("Thank you for using the Library Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
}
