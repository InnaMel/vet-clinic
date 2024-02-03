package main.java.com.magicvet.component;

import main.java.com.magicvet.Main;
import main.java.com.magicvet.model.Client;
import main.java.com.magicvet.model.Pet;
import main.java.com.magicvet.service.ClientService;
import main.java.com.magicvet.service.PetService;

public class EntityRegister {
    private final ClientService clientService = new ClientService();
    private final PetService petService = new PetService();

    public void registerClients() {
        {
            do {
                addClient();
            } while (verifyRepeating("Do you want to add one more client? (yes(y) / no(n)): "));
        }
    }

    private void addClient() {
        Client client = clientService.registerNewClient();

        if (client != null) {
            registerPets(client);
            System.out.println(client);
        }
    }

    private void registerPets(Client client) {
        {
            do {
                addPetToClient(client);
            } while (verifyRepeating("Do you want to add one more pet for current client? (yes(y) / no(n)): "));
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
