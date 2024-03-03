package main.java.com.magicvet.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Pet {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private String type;
    private String sex;
    private String age;
    private String name;
    private String ownerName;
    private HealthState healthState;
    private final LocalDateTime registrationDate = LocalDateTime.now();
    private final String registrationDateFormatted = registrationDate.format(FORMATTER);

    public enum HealthState {
        EMERGENCY(1),
        HOSPITALIZATION(2),
        HOMETREATMENT(3),
        CONSULTATION(4),
        HEALTHY(5);

        private final int value;

        public int getValue() {
            return value;
        }

        HealthState(int value){
            this.value = value;
        }

        public static HealthState getNameByValue(int value){
            for (HealthState healthState: HealthState.values()){
                if (healthState.getValue() == value){
                    return healthState;
                }
            }
            return null;
        }
    }

    @Override
    public String toString() {
        return "{"
                + "health state = " + healthState
                + ", type = " + type
                + ", sex = " + sex
                + ", age = " + age
                + ", name = " + name
                + ", registration date = " + registrationDateFormatted
                + "}\n\t";
    }

    public String getRegistrationDateFormatted() {
        return registrationDateFormatted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(type, pet.type)
                && Objects.equals(sex, pet.sex)
                && Objects.equals(age, pet.age)
                && Objects.equals(name, pet.name)
                && Objects.equals(ownerName, pet.ownerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, sex, age, name, ownerName);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public HealthState getHealthState() {
        return healthState;
    }

    public void setHealthState(HealthState healthState) {
        this.healthState = healthState;
    }

    public String getOwnerName() {
        return ownerName;
    }
}
