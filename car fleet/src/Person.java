import java.util.Objects;

public abstract class Person {
    protected String name;
    protected String surname;
    protected int age;

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " +
               name + " " + surname  +" "+ age +" years old.\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name) && Objects.equals(surname, person.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age);
    }

    public void tankCar(int fuel, Car car){
        car.tankCar(fuel);
    };
    public void driveCar(int distance, Car car){
        car.driveCar(distance);
    }

}
