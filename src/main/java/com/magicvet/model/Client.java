package main.java.com.magicvet.model;

import main.java.com.magicvet.service.PetService;

public class Client {

    private String firstName;
    private String lastName;
    private String email;
    private Pet pet;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Pet getPet() {
        return pet;
    }

    public void addPet(){
        if (pet == null){
            PetService petService = new PetService();
            pet = petService.registerNewPet();
        } else {
            System.out.println("This client already has a pet.");
        }
    }
}
