package main.java.com.magicvet.comparator;

import main.java.com.magicvet.model.Pet;

import java.util.Comparator;

public class PetAgeComparator implements Comparator<Pet> {

    @Override
    public int compare(Pet pet1, Pet pet2) {
        return Integer.parseInt(pet1.getAge()) - Integer.parseInt(pet2.getAge());
    }
}
