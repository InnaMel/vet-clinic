package main.java.com.magicvet.service;

import main.java.com.magicvet.Main;
import main.java.com.magicvet.model.Client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientService {

    final private static String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    final private static String NAME_PATTERN = "^[a-zA-Z]{2,}+(-[a-zA-Z]{2,}+)?";

    public Client registerNewClient() {
        Client client = null;

        System.out.println("Please provide client details.");
        System.out.print("Email: ");
        String email = Main.SCANNER.nextLine();

        if (isEmailValid(email)) {
            client = buildClient(email);
            System.out.println("New client: "
                    + client.getFirstName() + ' '
                    + client.getLastName() + " ("
                    + client.getEmail() + ")");
        } else {
            System.out.println("Provided email is invalid.");
        }

        return client;
    }

    private static Client buildClient(String email) {
        Client client = new Client();
        client.setEmail(email);

        System.out.print("First name: ");
        String fistName = Main.SCANNER.nextLine();
        if (isNameValid(fistName)) {
            client.setFirstName(fistName);
        } else {
            client.setFirstName(getUserInput());
        }

        System.out.print("Last name: ");
        String lastName = Main.SCANNER.nextLine();
        if (isNameValid(lastName)) {
            client.setLastName(lastName);
        } else {
            client.setLastName(getUserInput());
        }

        return client;
    }

    private static boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private static boolean isNameValid(String name) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);

        return matcher.matches();
    }

    private static String getUserInput() {
        System.out.println("Provided data is invalid.");
        System.out.println("NOTE: allowed Latin and dash symbol.");
        System.out.print("Your data: ");
        String inputData = Main.SCANNER.nextLine();

        if (!isNameValid(inputData)) {
            System.out.println("Provided data is invalid.");
            System.out.print("Data save as \"No Data\". ");
            System.out.println("You can change it later.");
            return "NoData";
        }

        return inputData;
    }
}
