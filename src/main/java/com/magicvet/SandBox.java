package main.java.com.magicvet;

import main.java.com.magicvet.comparator.DogSizeComparator;
import main.java.com.magicvet.comparator.PetAgeComparator;
import main.java.com.magicvet.model.Dog;
import main.java.com.magicvet.model.Cat;
import main.java.com.magicvet.model.Pet;

import java.util.Arrays;

public class SandBox {
    public static void main(String[] args) {
        System.out.println("TEST DOGS SIZE");
        PetRepository.testDogCompareSize_shouldBeOrderByAscending();

        System.out.println("\n");

        System.out.println("TEST CATS AGE");
        PetRepository.testPetCompareAge_shouldBeOrderByAscending();
    }
}


class PetRepository {

    static void testDogCompareSize_shouldBeOrderByAscending() {
        //ARRANGE
        Dog[] dogs = {
                new Dog(Dog.M),
                new Dog(Dog.S),
                new Dog(Dog.XL),
                new Dog(Dog.XL),
                new Dog(Dog.XS),
                new Dog(Dog.S)
        };

        //ACT
        Arrays.sort(dogs, new DogSizeComparator());

        //ASSERT
        for (Dog dog : dogs) {
            System.out.print(dog.getSize() + ", ");
        }
    }

    static void testPetCompareAge_shouldBeOrderByAscending() {
        //ARRANGE
        Pet[] cats = {
                new Cat("2", "Nora"),
                new Cat("20", "Teodor"),
                new Cat("12", "Luna"),
                new Cat("4", "Murka")
        };

        //ACT
        Arrays.sort(cats, new PetAgeComparator());

        //ASSERT
        for (Pet cat : cats) {
            System.out.print(cat.getAge()+ " (" + cat.getName()+ ")" + ", ");
        }
    }
}
