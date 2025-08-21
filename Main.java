public

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }
}

class Student {
    int id;
    String name;
    String password;

    Student(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}

class Library {
    private List<Book> books = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private Student loggedInStudent = null;

    // Add book
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.title);
    }

    // Show books
    public void showBooks() {
        for (Book b : books) {
            System.out.println(b.id + " - " + b.title + " by " + b.author +
                    (b.isIssued ? " [Issued]" : " [Available]"));
        }
    }

    // Issue book
    public void issueBook(int id) {
        if (loggedInStudent == null) {
            System.out.println("Login required!");
            return;
        }
        for (Book b : books) {
            if (b.id == id && !b.isIssued) {
                b.isIssued = true;
                System.out.println("Book issued to " + loggedInStudent.name + ": " + b.title);
                return;
            }
        }
        System.out.println("Book not available.");
    }

    // Return book
    public void returnBook(int id) {
        if (loggedInStudent == null) {
            System.out.println("Login required!");
            return;
        }
        for (Book b : books) {
            if (b.id == id && b.isIssued) {
                b.isIssued = false;
                System.out.println("Book returned by " + loggedInStudent.name + ": " + b.title);
                return;
            }
        }
        System.out.println("Invalid return request.");
    }

    // Add student
    public void addStudent(Student s) {
        students.add(s);
        System.out.println("Student registered: " + s.name);
    }

    // Student login
    public boolean login(int id, String password) {
        for (Student s : students) {
            if (s.id == id && s.password.equals(password)) {
                loggedInStudent = s;
                System.out.println("Login successful! Welcome " + s.name);
                return true;
            }
        }
        System.out.println("Invalid ID or password.");
        return false;
    }

    // Student logout
    public void logout() {
        if (loggedInStudent != null) {
            System.out.println("Goodbye, " + loggedInStudent.name);
            loggedInStudent = null;
        } else {
            System.out.println("No user is logged in.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();

        // Register some students
        lib.addStudent(new Student(1, "Alice", "pass123"));
        lib.addStudent(new Student(2, "Bob", "1234"));

        while (true) {
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. Login");
            System.out.println("2. Add Book");
            System.out.println("3. Show Books");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Logout");
            System.out.println("7. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();
                    lib.login(id, password);
                    break;

                case 2:
                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    lib.addBook(new Book(bookId, title, author));
                    break;

                case 3:
                    lib.showBooks();
                    break;

                case 4:
                    System.out.print("Enter Book ID to issue: ");
                    int issueId = sc.nextInt();
                    lib.issueBook(issueId);
                    break;

                case 5:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    lib.returnBook(returnId);
                    break;

                case 6:
                    lib.logout();
                    break;

                case 7:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}Main{

}
