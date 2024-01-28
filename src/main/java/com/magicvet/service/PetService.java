package main.java.com.magicvet.service;

import main.java.com.magicvet.Main;
import main.java.com.magicvet.model.Cat;
import main.java.com.magicvet.model.Dog;
import main.java.com.magicvet.model.Pet;

public class PetService {

    private static final String DOG_TYPE = "dog";
    private static final String CAT_TYPE = "cat";

    public Pet registerNewPet() {
        Pet pet = null;

        System.out.print("Type (dog / cat): ");
        String type = Main.SCANNER.nextLine().toLowerCase();

        if (DOG_TYPE.equals(type) || CAT_TYPE.equals(type)) {
            pet = buildPet(type);
        } else {
            System.out.println("Unknown pet type: " + type);
        }

        return pet;
    }

    private Pet buildPet(String type) {
        Pet pet = type.equals(CAT_TYPE) ? new Cat() : new Dog();
        pet.setType(type);

        System.out.print("Age: ");
        pet.setAge(Main.SCANNER.nextLine());

        System.out.print("Name: ");
        pet.setName(Main.SCANNER.nextLine());

        System.out.print("Sex (male(m) / female(f)): ");
        String gender=(Main.SCANNER.nextLine()).equals('m') ? "male" : "female";
        pet.setSex(gender);

        if (type.equals(DOG_TYPE)) {
            System.out.print("Size (XS / S / M / L / XL): ");
            String size = Main.SCANNER.nextLine().toUpperCase();
            ((Dog) pet).setSize(Dog.Size.valueOf(size));
        }

        System.out.println("Health State.");
        pet.setHealthState(getCurrentHealthState());

        return pet;
    }

    private Pet.HealthState getCurrentHealthState() {
        Pet.HealthState currentHealthState = null;
        boolean isInputCorrect = false;

        System.out.println("\t1 - emergency;\n" +
                "\t2 - hospitalization;\n" +
                "\t3 - homeTreatment;\n" +
                "\t4 - consultation;\n" +
                "\t5 - healthy;");

        System.out.print("Type rate of Health State: ");
        String inputHealthState = Main.SCANNER.nextLine();

        try {

            for (var state : Pet.HealthState.values()) {
                if (state.getValue() == Integer.parseInt(inputHealthState)) {
                    currentHealthState = state;
                    isInputCorrect = true;
                    break;
                }
            }
        }
        catch (NumberFormatException e){
            isInputCorrect = false;
        }

        if (!isInputCorrect) {
            System.out.println("Incorrect input. \"Health State\" recorded as \"Consultation\"");
            currentHealthState = Pet.HealthState.consultation;
        }
        return currentHealthState;
    }
}
