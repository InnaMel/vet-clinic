package main.java.com.magicvet.model;

import java.util.Objects;

public class Dog extends Pet{

    private String size;

    public void setSize(String size) {
        this.size = size.toUpperCase();
    }

    public String getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "{"
                + "type = " + super.getType()
                + ", sex = " + super.getSex()
                + ", age = " + super.getAge()
                + ", name = " + super.getName()
                + ", size = " + size
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Dog dog = (Dog) o;
        return Objects.equals(size, dog.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), size);
    }
}
