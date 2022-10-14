import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ContactsTest {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Contact> contacts = new ArrayList<>();

        System.out.println("what is your first name: ");
        String firstName = input.nextLine();
        System.out.println("what is your last name: ");
        String lastName = input.nextLine();
        System.out.println("what is your phone number: ");
        long phoneNumber = input.nextLong();
        // validate phone number format

        Contact contact1 = new Contact(firstName, lastName, phoneNumber);
        contacts.add(contact1);
        System.out.println(contacts.get(0).getFirstName());
        System.out.println(contacts.get(0).getLastName());
        System.out.println(contacts.get(0).getPhoneNumber());
//        System.out.printf("%s%n%s%n%d%n", contact1.getFirstName(), contact1.getLastName(), contact1.getPhoneNumber());
    }
}
