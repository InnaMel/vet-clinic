package main.java.com.magicvet.service;

import main.java.com.magicvet.Main;
import main.java.com.magicvet.model.Client;

import java.util.Optional;

public class ClientService {

    public Optional<Client> registerNewClient() {
        Client client = null;

        System.out.println("Please provide client details.");
        System.out.print("Email: ");
        String inputEmail = Main.SCANNER.nextLine();

        if (Validation.isEmailValid(inputEmail)) {
            client = buildClient(inputEmail);
            System.out.println("New client: "
                    + client.getFirstName() + ' '
                    + client.getLastName() + " ("
                    + client.getEmail() + ") "
                    + "from " + client.getLocation());
        } else {
            System.out.println("Provided inputEmail is invalid.");
        }

        return Optional.ofNullable(client);
    }

    private static Client buildClient(String email) {
        Client client = new Client();
        client.setEmail(email);

        System.out.print("First name: ");
        String inputFistName = Main.SCANNER.nextLine();
        if (Validation.isNameValid(inputFistName)) {
            client.setFirstName(inputFistName);
        } else {
            client.setFirstName(checkUserInput());
        }

        System.out.print("Last name: ");
        String inputLastName = Main.SCANNER.nextLine();

        if (Validation.isNameValid(inputLastName)) {
            client.setLastName(inputLastName);
        } else {
            client.setLastName(checkUserInput());
        }

        System.out.print("Location: ");
        Client.Location location;
        String inputLocation = Main.SCANNER.nextLine().toUpperCase();

        try {
            location = Client.Location.valueOf(inputLocation);
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
