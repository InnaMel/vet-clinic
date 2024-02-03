package main.java.com.magicvet.component;

import main.java.com.magicvet.Main;
import main.java.com.magicvet.model.Client;
import main.java.com.magicvet.model.Pet;
import main.java.com.magicvet.service.ClientService;
import main.java.com.magicvet.service.PetService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityRegister {
    private final ClientService clientService = new ClientService();
    private final PetService petService = new PetService();

    public void registerClients() {

        List<Client> clients = new ArrayList<>();
        String message = "Do you want to add one more client? (yes(y) / no(n)): ";
        do {
            Client client = addClient();
            if (client != null) {
                clients.add(client);
            }
        } while (verifyRepeating(message));

        Map<Client.Location, List<Client>> clientByLocation = groupClients(clients);

        printClients(clientByLocation);
    }

    private void printClients(Map<Client.Location, List<Client>> clientByLocation) {
        for (Map.Entry<Client.Location, List<Client>> clients : clientByLocation.entrySet()) {
            if (clients.getValue().isEmpty()) {
                continue;
            } else {
                String content = "\nLocation: " + clients.getKey()
                        + "\nClients (" + clients.getValue().size() + "):"
                        + "\n\t" + clients.getValue();
                System.out.println(content);
            }
        }
    }

    private Map<Client.Location, List<Client>> groupClients(List<Client> clients) {
        List<Client> fromKyiv = new ArrayList<>();
        List<Client> fromDnipro = new ArrayList<>();
        List<Client> fromOdesa = new ArrayList<>();

        for (Client client : clients) {
            switch (client.getLocation()) {
                case KYIV -> fromKyiv.add(client);
                case DNIPRO -> fromDnipro.add(client);
                case ODESA -> fromOdesa.add(client);
            }
        }

        Map<Client.Location, List<Client>> clientByLocation = new HashMap<>();
        clientByLocation.put(Client.Location.KYIV, fromKyiv);
        clientByLocation.put(Client.Location.DNIPRO, fromDnipro);
        clientByLocation.put(Client.Location.ODESA, fromOdesa);

        return clientByLocation;
    }

    private Client addClient() {
        Client client = clientService.registerNewClient();

        if (client != null) {
            registerPets(client);
            System.out.println(client);
        }

        return client;
    }

    private void registerPets(Client client) {
        String message = "Do you want to add one more pet for current client? (yes(y) / no(n)): ";

        {
            do {
                addPetToClient(client);
            } while (verifyRepeating(message));
        }
    }

    private void addPetToClient(Client client) {
        System.out.println("Adding a new pet.");

        Pet pet = petService.registerNewPet();

        if (pet != null) {
            client.addPet(pet);
            pet.setOwnerName(client.getFirstName() + " " + client.getLastName());

            System.out.println("Pet has been added.");
        }
    }

    private boolean verifyRepeating(String message) {
        System.out.print(message);

        String answer = Main.SCANNER.nextLine();
        if ("y".equals(answer)) {
            return true;
        } else if ("n".equals(answer)) {
            return false;
        } else {
            System.out.println("Incorrect data. Please try again.");
            return verifyRepeating(message);
        }
    }
}
