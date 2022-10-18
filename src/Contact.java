import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Contact {

    private String firstName;
    private String lastName;
    private long phoneNumber;

    public Contact(String firstName, String lastName, long phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Method to SHOW ALL contacts.
    public static void showAllContacts(List<Contact> contacts) {
        System.out.println("-- View Contacts --");
        System.out.println("Name | Phone Number");
        System.out.println("-------------------");
        for (Contact contact : contacts) {
            System.out.printf("%s %s | %d%n", contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber());
        }
        // Creating space between contacts and menu with extra print lines
        System.out.println("\n\n");
    }

    // Method to ADD contact.
    public static List addContact(List contacts) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter your first name: ");
        String firstName = input.nextLine();

        System.out.println("Enter your last name: ");
        String lastName = input.nextLine();

        System.out.println("Enter your phone number: ");
        long phoneNumber = input.nextLong();

        Contact contact = new Contact(firstName, lastName, phoneNumber);
        contacts.add(contact);
        showAllContacts(contacts);

        return contacts;
    }

    // Method to SEARCH FOR contact.
    public static void searchForContact(List<Contact> contacts) {
        Scanner input = new Scanner(System.in);

        System.out.println("-- Search a contact by name --");

        System.out.println("Enter contact's first name: ");
        String userFirst = input.nextLine();

        System.out.println("Enter contact's last name: ");
        String userLast = input.nextLine();

        for (Contact contact : contacts) {
            if (userFirst.equalsIgnoreCase(contact.getFirstName()) && userLast.equalsIgnoreCase(contact.getLastName())) {
                System.out.println("Name | Phone Number |");
                System.out.println("---------------------");
                System.out.printf("%s %s | %d%n", contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber());
            }
        }
    }

    // Method to DELETE contact.
    public static void deleteContact(List<Contact> contacts) {
        // TODO SOMETHING TO LOOK FOR: MAYBE RETURN ARRAY AFTER METHOD DELETES CONTACT.

        Scanner input = new Scanner(System.in);

        System.out.println("-- List of Contacts to Delete --");
        System.out.println("Name | Phone Number");
        System.out.println("-------------------");
        for (Contact contact : contacts) {
            System.out.printf("%s %s | %d%n", contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber());
        }

        System.out.println("Enter contact's first name: ");
        String userFirst = input.nextLine();

        System.out.println("Enter contact's last name: ");
        String userLast = input.nextLine();

        for (Contact contact : contacts) {
            if (userFirst.equalsIgnoreCase(contact.getFirstName()) && userLast.equalsIgnoreCase(contact.getLastName())) {
                System.out.println("Are you sure you want to remove this contact? [y / n]");
                String deleteContact = input.nextLine();
                if (deleteContact.equalsIgnoreCase("y")) {
                    contacts.remove(contact);
                } else if (deleteContact.equalsIgnoreCase("n")) {
                    System.out.println("Okay then.");
                }
            }
            break;
        }
    }
    public static void readToFile(List<Contact> contacts) throws IOException {
        String file = "contacts.txt";
        Path contactListPath = Paths.get(file);
        List<String> contactList = Files.readAllLines(contactListPath);
        for(String line: contactList){
            System.out.println(line);
        }
    }
    public static void writeToFile(List<Contact> contacts) throws IOException {
        String file = "contacts.txt";
        Path contactListPath = Paths.get(file);
        List<String> contactList = Files.readAllLines(contactListPath);
        String contacts_to_string;
        for (Contact contact : contacts){
            contacts_to_string = contact.firstName + " " + contact.lastName + " | " + contact.phoneNumber;
            Files.write(Paths.get(file), Arrays.asList(contacts_to_string), StandardOpenOption.APPEND);
        }
    }


}
