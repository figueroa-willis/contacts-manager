import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ContactsTest {

    public static int usersOption;

    public static void showMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit.");
        System.out.println("Enter an option (1, 2, 3, 4 or 5):");
        usersOption = input.nextInt();
    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        ArrayList<Contact> contacts = new ArrayList<>();

        // TODO: WRITE LIST OF CONTACTS TO CONTACTS.TXT FILE

        showMenu();
        // TODO: Refactor if statement inside do while to switch
        do {

            if (usersOption == 1) {
                Contact.showAllContacts(contacts);
                Contact.readFromFile(contacts);
//                Contact.addFileToMemory(contacts);
                showMenu();
            } else if (usersOption == 2) {
                Contact.addContact(contacts);
//                Contact.readFromFile(contacts);1
//                Contact.writeToFile(contacts);
                showMenu();
            } else if (usersOption == 3) {
                Contact.searchForContact(contacts);
                showMenu();
            } else if (usersOption == 4) {
                Contact.deleteContact(contacts);
                showMenu();
            } else if (usersOption == 5) {
                System.out.println("Goodbye...");
                Contact.writeToFile(contacts);
                break;
            }
        } while (true);
    }
}