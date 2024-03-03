package main.java.com.magicvet.service;

import main.java.com.magicvet.Main;
import main.java.com.magicvet.model.Cat;
import main.java.com.magicvet.model.Dog;
import main.java.com.magicvet.model.Pet;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PetService {

    private static final String DOG_TYPE = "dog";
    private static final String CAT_TYPE = "cat";
    private static final String CAT_TYPE_PATTERN__AT = "^.{1}[aA][tT]$";
    private static final String CAT_TYPE_PATTERN_C_T = "^[cC][a-zA-Z].{1}[tT]$";
    private static final String CAT_TYPE_PATTERN_CA_ = "^[cC][aA].{1}$";
    private static final String DOG_TYPE_PATTERN__OG = "^.{1}[oO][gG]$";
    private static final String DOG_TYPE_PATTERN_D_G = "^[dD][a-zA-Z].{1}[gG]$";
    private static final String DOG_TYPE_PATTERN_DO_ = "^[dD][oO].{1}$";


    public Optional<Pet> registerNewPet() {
        Pet pet = null;

        System.out.print("Type (dog / cat): ");
        String inputType = Main.SCANNER.nextLine();

        inputType = checkInputType(inputType);

        if (DOG_TYPE.equals(inputType) || CAT_TYPE.equals(inputType)) {
            pet = buildPet(inputType);
        } else {
            System.out.println("Unknown pet inputType: " + inputType);
        }

        return Optional.ofNullable(pet);
    }

    private String checkInputType(String inputType) {
        String type;

        Pattern catPatternAT = Pattern.compile(CAT_TYPE_PATTERN__AT);
        Pattern catPatternCT = Pattern.compile(CAT_TYPE_PATTERN_C_T);
        Pattern catPatternCA = Pattern.compile(CAT_TYPE_PATTERN_CA_);

        Pattern dogPatternOG = Pattern.compile(DOG_TYPE_PATTERN__OG);
        Pattern dogPatternDG = Pattern.compile(DOG_TYPE_PATTERN_D_G);
        Pattern dogPatternDO = Pattern.compile(DOG_TYPE_PATTERN_DO_);

        Matcher catMatcherAT = catPatternAT.matcher(inputType);
        Matcher catMatcherCT = catPatternCT.matcher(inputType);
        Matcher catMatcherCA = catPatternCA.matcher(inputType);

        Matcher dogMatcherOG = dogPatternOG.matcher(inputType);
        Matcher dogMatcherDG = dogPatternDG.matcher(inputType);
        Matcher dogMatcherDO = dogPatternDO.matcher(inputType);


        type = (catMatcherAT.find() || catMatcherCT.find() || catMatcherCA.find()) ? "cat" : "Unknown";
        if (type != "cat") {
            type = (dogMatcherOG.find() || dogMatcherDG.find() || dogMatcherDO.find()) ? "dog" : "Unknown";
        }

        return type;
    }

    private Pet buildPet(String type) {
        Pet pet = type.equals(CAT_TYPE) ? new Cat() : new Dog();
        pet.setType(type);

        System.out.print("Age: ");
        pet.setAge(Main.SCANNER.nextLine());

        System.out.print("Name: ");
        pet.setName(Main.SCANNER.nextLine());

        System.out.print("Sex (male(m) / female(f)): ");
        String inputGender = Main.SCANNER.nextLine().toLowerCase();
        String gender = (inputGender.equals("m") || inputGender.equals("male")) ? "male" : "female";
        pet.setSex(gender);

        if (type.equals(DOG_TYPE)) {
            System.out.print("Size (XS / S / M / L / XL): ");
            String inputSize = Main.SCANNER.nextLine().toUpperCase();
            ((Dog) pet).setSize(Dog.Size.fromString(inputSize));
        }

        System.out.println("Health State.");
        pet.setHealthState(getCurrentHealthState());

        return pet;
    }

    private Pet.HealthState getCurrentHealthState() {

        System.out.println("\t1 - emergency;\n" +
                "\t2 - hospitalization;\n" +
                "\t3 - homeTreatment;\n" +
                "\t4 - consultation;\n" +
                "\t5 - healthy;");

        System.out.print("Type rate of Health State: ");
        var inputHealthState = Integer.parseInt(Main.SCANNER.nextLine());
        var healthState = Pet.HealthState.getNameByValue(inputHealthState);

        if (healthState == null) {
            healthState = Pet.HealthState.CONSULTATION;
            System.out.println("Provided data is invalid. \"Health State\" save as " + Pet.HealthState.CONSULTATION);
        }

        return healthState;
    }
}
