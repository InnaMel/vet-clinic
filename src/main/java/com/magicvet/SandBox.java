package main.java.com.magicvet;

import main.java.com.magicvet.model.Dog;
import main.java.com.magicvet.model.Cat;
import main.java.com.magicvet.model.Pet;

import java.util.Arrays;
import java.util.Comparator;

public class SandBox {
    public static void main(String[] args) {
        System.out.println("TEST DOGS SIZE");
        PetRepository.testDogCompareSize_shouldBeOrderByAscending();

        System.out.println("\n");

        System.out.println("TEST CATS AGE");
        PetRepository.testPetCompareAge_shouldBeOrderByAscending();

        System.out.println("\n *************");
        System.out.println(new Cat());
    }
}


class PetRepository {

    static void testDogCompareSize_shouldBeOrderByAscending() {
        //ARRANGE
        Dog[] dogs = {
                new Dog(Dog.Size.M),
                new Dog(Dog.Size.S),
                new Dog(Dog.Size.XL),
                new Dog(Dog.Size.XL),
                new Dog(Dog.Size.XS),
                new Dog(Dog.Size.S)
        };

        //ACT
        Arrays.sort(dogs, new Comparator<Dog>() {
            @Override
            public int compare(Dog dog1, Dog dog2) {
                return dog1.getSize().getValue() - dog2.getSize().getValue();
            }
        });

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
        Arrays.sort(cats, new Comparator<Pet>() {
            @Override
            public int compare(Pet pet1, Pet pet2) {
                return Integer.parseInt(pet1.getAge()) - Integer.parseInt(pet2.getAge());
            }
        });

        //ASSERT
        for (Pet cat : cats) {
            System.out.print(cat.getAge()+ " (" + cat.getName()+ ")" + ", ");
        }
    }
}
