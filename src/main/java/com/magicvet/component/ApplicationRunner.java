package main.java.com.magicvet.component;

import main.java.com.magicvet.Main;
import main.java.com.magicvet.model.Client;
import main.java.com.magicvet.model.Pet;
import main.java.com.magicvet.service.ClientService;
import main.java.com.magicvet.service.PetService;

public class ApplicationRunner {

    private final ClientService clientService = new ClientService();
    private final PetService petService = new PetService();

    public void run() {
        if (Authenticator.auth()) {
            Client client = clientService.registerNewClient();

            if (client != null) {
                registerPets(client);
                System.out.println(client);
            }
        }
    }

    private void registerPets(Client client){
        boolean isContinueAddPet = true;

        while (isContinueAddPet){
            addPetToClient(client);

            System.out.print("Do you want to add one more pet for current client? (yes(y) / no(n)): ");
            String answer = Main.SCANNER.nextLine();

            if ("n".equals(answer)){
                isContinueAddPet = false;
            }
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
}
