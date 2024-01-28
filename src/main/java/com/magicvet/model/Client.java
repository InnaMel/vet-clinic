package main.java.com.magicvet.model;

import main.java.com.magicvet.service.PetService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

public class Client {

    private static final DateTimeFormatter FORMATTER =DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
    private String firstName;
    private String lastName;
    private String email;
    private  List<Pet> pets = new ArrayList<>();
    private final LocalDateTime registrationDate = LocalDateTime.now();

    @Override
    public String toString() {
        return "Client: {"
                + "\n\tfirstName = " + firstName
                + ", lastName = " + lastName
                + ", email = \"" + email + "\""
                + ", registration date = \"" + registrationDate.format(FORMATTER) + "\""
                + ",\n\tPets:\n\t" + pets
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(firstName, client.firstName)
                && Objects.equals(lastName, client.lastName)
                && Objects.equals(email, client.email)
                && Objects.equals(pets, client.pets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, pets);
    }

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

    public void setPet(List<Pet> pet) {
        this.pets = pets;
    }

    public List<Pet> getPet() {
        return pets;
    }

    public void addPet(Pet pet){
            pets.add(pet);
    }
}
