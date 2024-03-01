package main.java.com.magicvet.component;

import main.java.com.magicvet.Main;
import main.java.com.magicvet.model.Client;
import main.java.com.magicvet.model.Pet;
import main.java.com.magicvet.service.ClientService;
import main.java.com.magicvet.service.PetService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class EntityRegister {
    private final ClientService clientService = new ClientService();
    private final PetService petService = new PetService();

    public void registerClients() {

        List<Client> clients = new ArrayList<>();
        String message = "Do you want to add one more client? (yes(y) / no(n)): ";
        do {
            Optional<Client> client = addClient();
            client.ifPresent(clients::add);
        } while (verifyRepeating(message));

        Map<Client.Location, List<Client>> clientByLocation = clients.stream()
                .collect(Collectors.groupingBy(Client::getLocation));

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

    private Optional<Client> addClient() {
        Optional<Client> client = clientService.registerNewClient();
        client.ifPresent(this::registerPets);

        return client;
    }

    private void registerPets(Client client) {
        String message = "Do you want to add one more pet for current client? (yes(y) / no(n)): ";

        do {
            addPetToClient(client);
            System.out.println(client);
        } while (verifyRepeating(message));
    }

    private void addPetToClient(Client client) {
        System.out.println("Adding a new pet.");

        Optional<Pet> pet = petService.registerNewPet();
        pet.ifPresent(client::addPet);
        addOwnerToPet(pet.get(), client);
        System.out.println("Pet has been added.");
    }

    private void addOwnerToPet(Pet pet, Client client) {
        pet.setOwnerName(client.getFirstName() + " " + client.getLastName());
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
