import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
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
    public static void showAllContacts(ArrayList<Contact> contacts) {
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
    public static ArrayList addContact(ArrayList contacts) {
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
    public static void searchForContact(ArrayList<Contact> contacts) {
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
    public static void deleteContact(ArrayList<Contact> contacts) {
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
    public static void writeToFile(ArrayList <Contact> contacts) throws IOException {
        Path filepath = Paths.get("contacts.txt");
        ArrayList<String> contactList  = (ArrayList<String>) Files.readAllLines(filepath);
        int i = 0;
        String stringify_contact = contacts.get(i).firstName + " " + contacts.get(i).lastName + " | " + contacts.get(i).phoneNumber;
        Files.write(filepath, Arrays.asList(stringify_contact), StandardOpenOption.APPEND);
    }


    public static void readFromFile(ArrayList<Contact> contacts)  throws IOException {
        Path filepath = Paths.get("contacts.txt");
        ArrayList<String> contactList  = (ArrayList<String>) Files.readAllLines(filepath);
        for(int i = 0; i < contactList.size(); ++i){
            String name = contactList.get(i).substring(0, contactList.get(i).indexOf("|"));
            String firstName = name.substring(0, name.indexOf(" "));
            String lastName = name.substring(name.indexOf(" "), name.lastIndexOf(""));
            lastName = lastName.trim();
            String phoneNumber = contactList.get(i).substring(contactList.get(i).indexOf("|"), contactList.get(i).lastIndexOf(""));
            phoneNumber = phoneNumber.substring(phoneNumber.indexOf(" "), phoneNumber.lastIndexOf(""));
            phoneNumber = phoneNumber.trim();
            Contact contact = new Contact(firstName, lastName, Long.parseLong(phoneNumber));
            System.out.println(contact.firstName);
            contacts.add(contact);
        }
    }
}
