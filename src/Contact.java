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
        int i = 0;
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
        Long phoneNumber = input.nextLong();
        long length = String.valueOf(phoneNumber).length();
        if(length == 10){
            Contact contact = new Contact(firstName, lastName, phoneNumber);
            contacts.add(contact);
        } else if (length == 7) {
            Contact contact = new Contact(firstName, lastName, phoneNumber);
            contacts.add(contact);
        }else  {
            System.out.println(length);
            while (length  <= 6 || length == 8 || length == 9){
                System.out.println("Enter 7/10 digit phone number: ");
                System.out.println("Enter your phone number: ");
                phoneNumber = input.nextLong();
                length = String.valueOf(phoneNumber).length();
            }
            Contact contact = new Contact(firstName, lastName, phoneNumber);
            contacts.add(contact);
        }

        showAllContacts(contacts);

        return contacts;
    }

    // Method to SEARCH FOR contact.
    public static void searchForContact(List<Contact> contacts) throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("-- Search a contact by name --");

        System.out.println("Enter contact's first name: ");
        String userFirst = input.nextLine();

        System.out.println("Enter contact's last name: ");
        String userLast = input.nextLine();

        String concat = userFirst + " " + userLast;
        concat = concat.trim();
        String file = "contacts.txt";
        Path contactListPath = Paths.get(file);
        List<String> contactList = Files.readAllLines(contactListPath);
        for(String line: contactList){
            String name = line.substring(0, line.indexOf("|"));
            name = name.trim();
            if(concat.equalsIgnoreCase(name)){
                System.out.println("Name | Phone Number");
                System.out.println("-------------------");
                System.out.println(line);
            }
        }
    }

    // Method to DELETE contact.
    public static void deleteContact(List<Contact> contacts) throws IOException {
        // TODO SOMETHING TO LOOK FOR: MAYBE RETURN ARRAY AFTER METHOD DELETES CONTACT.

        Scanner input = new Scanner(System.in);

        System.out.println("-- List of Contacts to Delete --");
        System.out.println("Name | Phone Number");
        System.out.println("-------------------");
        String file = "contacts.txt";
        Path contactListPath = Paths.get(file);
        List<String> contactList = Files.readAllLines(contactListPath);
        for (String line : contactList) {
            System.out.println(line);
        }
        System.out.println("Enter contact's first name: ");
        String userFirst = input.nextLine();

        System.out.println("Enter contact's last name: ");
        String userLast = input.nextLine();

        String concat = userFirst + " " + userLast;
        concat = concat.trim();
        for(int i = 0; i < contactList.size(); ++i){
            String name = contactList.get(i).substring(0, contactList.get(i).indexOf("|"));
            name = name.trim();
            if(concat.equalsIgnoreCase(name)){
                System.out.println("Name | Phone Number");
                System.out.println("-------------------");
                System.out.println(contactList.get(i));
                System.out.println("Are you sure you want to remove?");
                String userInput = input.nextLine();
                if(userInput.equalsIgnoreCase("yes")){
                    contactList.remove(contactList.get(i));
                }
            }
        }

        Path filepath = Paths.get(file);
        for (String line : contactList){
            Files.write(filepath, Arrays.asList(line));
            System.out.println(line);
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

    public static void writeToFile(ArrayList<Contact> contacts) throws IOException {
        String file = "contacts.txt";
        Path filepath = Paths.get(file);
        String stringContact;
        ArrayList<String> contactList = (ArrayList<String>) Files.readAllLines(filepath);
        int i = 0;
        for (Contact contact : contacts) {
            int length = String.valueOf(contact.phoneNumber).length();
            if(length > 7) {
                stringContact = contact.getFirstName() + " " + contact.getLastName() + " | " + String.valueOf(contact.getPhoneNumber()).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1)-$2-$3");
                stringContact = stringContact.trim();
                if (stringContact.equalsIgnoreCase(contactList.get(i))) {
                    System.out.println("These two are equal, SHOULD NOT ADD");
                    continue;
                } else {
                    System.out.println("ADDING " + stringContact + " to text file");
                    Files.write(filepath, Arrays.asList(stringContact), StandardOpenOption.APPEND);
                }
            }else if(length == 7){
                stringContact = contact.getFirstName() + " " + contact.getLastName() + " | " + String.valueOf(contact.getPhoneNumber()).replaceFirst("(\\d{3})(\\d{4})", "($1)-$2");
                stringContact = stringContact.trim();
                    if(stringContact.equalsIgnoreCase(contactList.get(i))){
                        System.out.println("These two are equal, SHOULD NOT ADD");
                        continue;
                    }else {
                        System.out.println("ADDING " + stringContact + "to text file");
                        Files.write(filepath, Arrays.asList(stringContact), StandardOpenOption.APPEND);
                    }
            }
            ++i;
        }
    }
}

