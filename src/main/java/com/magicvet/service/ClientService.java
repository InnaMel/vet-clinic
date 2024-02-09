package main.java.com.magicvet.service;

import main.java.com.magicvet.Main;
import main.java.com.magicvet.model.Client;

public class ClientService {

    public Client registerNewClient() {
        Client client = null;

        System.out.println("Please provide client details.");
        System.out.print("Email: ");
        String email = Main.SCANNER.nextLine();

        if (Validation.isEmailValid(email)) {
            client = buildClient(email);
            System.out.println("New client: "
                    + client.getFirstName() + ' '
                    + client.getLastName() + " ("
                    + client.getEmail() + ") "
                    + "from " + client.getLocation());
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
        if (Validation.isNameValid(fistName)) {
            client.setFirstName(fistName);
        } else {
            client.setFirstName(checkUserInput());
        }

        System.out.print("Last name: ");
        String lastName = Main.SCANNER.nextLine();

        if (Validation.isNameValid(lastName)) {
            client.setLastName(lastName);
        } else {
            client.setLastName(checkUserInput());
        }

        System.out.print("Location: ");
        Client.Location location;
        String locationInput = Main.SCANNER.nextLine().toUpperCase();

        try {
            location = Client.Location.valueOf(locationInput);
        } catch (IllegalArgumentException e) {
            location = Client.Location.UNKNOWN;
            System.out.println("Provided data is invalid. \"Location\" save as: " + Client.Location.UNKNOWN);
        }
        client.setLocation(location);

        return client;
    }

    private static String checkUserInput() {
        System.out.println("Provided data is invalid.");
        System.out.println("NOTE: allowed Latin and dash symbol.");
        System.out.print("Your data: ");
        String inputData = Main.SCANNER.nextLine();

        if (!Validation.isNameValid(inputData)) {
            System.out.println("Provided data is invalid.");
            System.out.print("Data save as \"No Data\". ");
            System.out.println("You can change it later.");
            return "NoData";
        }

        return inputData;
    }
}
