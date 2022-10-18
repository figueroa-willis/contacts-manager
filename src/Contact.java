
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
//        System.out.println("\n\n");
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

    public static void readFromFile(ArrayList<Contact> contacts) throws IOException {
        String file = "contacts.txt";
        Path contactListPath = Paths.get(file);
        List<String> contactList = Files.readAllLines(contactListPath);
        for (String line : contactList){
            System.out.println(line);
//        for (int i = 0; i < contactList.size(); ++i) {
////            System.out.println((i + 1) + ": " + contactList.get(i));
//            String name = contactList.get(i).substring(0, contactList.get(i).indexOf("|"));
//            String first_name = contactList.get(i).substring(0, contactList.get(i).indexOf(" "));
//            String last_name = contactList.get(i).substring(name.indexOf(" "), name.lastIndexOf(" "));
//            String phone_number = contactList.get(i).substring(contactList.get(i).indexOf("|"), contactList.get(i).lastIndexOf(""));
//            phone_number = phone_number.substring(1, phone_number.lastIndexOf(""));
//            phone_number = phone_number.trim();
//            first_name = first_name.trim();
//            last_name = last_name.trim();
//            Contact contact = new Contact(first_name, last_name, Long.parseLong(phone_number));
//            contacts.add(contact);

        }
    }
    public static void addFileToMemory(ArrayList<Contact> contacts) throws IOException {
        String file = "contacts.txt";
        Path contactListPath = Paths.get(file);
        List<String> contactList = Files.readAllLines(contactListPath);
//        List<String> contactList  = (ArrayList<String>) Files.readAllLines(contactListPath);
        for (String line : contactList) {
            System.out.println(line);
//            System.out.println((i + 1) + ": " + contactList.get(i));
            String name = line.substring(0, contacts.indexOf("|"));
            String first_name = name.substring(0, name.indexOf(" "));
            String last_name = name.substring(name.indexOf(" "), name.lastIndexOf(""));
            String phone_number = line.substring(line.indexOf("|"), line.lastIndexOf(""));
            phone_number = phone_number.substring(phone_number.charAt(1), phone_number.lastIndexOf(""));
            phone_number = phone_number.trim();
            first_name = first_name.trim();
            last_name = last_name.trim();
            Contact contact = new Contact(first_name, last_name, Long.parseLong(phone_number));
            contacts.add(contact);
        }
    }
    public static void writeToFile(ArrayList<Contact> contacts) throws IOException {
        String file = "contacts.txt";
        Path filepath = Paths.get(file);
        String stringContact;
        ArrayList<String> contactList  = (ArrayList<String>) Files.readAllLines(filepath);
        int i = 0;
        for (Contact contact: contacts){
            stringContact = contact.getFirstName() + " " + contact.getLastName() + " | " + contact.getPhoneNumber();
            if(stringContact.equalsIgnoreCase(contactList.get(i))){
                System.out.println("These two are equal, SHOULD NOT ADD");
                continue;
            }else {
                System.out.println("ADDING " + stringContact + "to text file");
                Files.write(filepath, Arrays.asList(stringContact), StandardOpenOption.APPEND);
            }
            ++i;
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

