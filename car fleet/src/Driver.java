import java.util.Objects;

public class Driver extends Person {
    private char DL_category; //Driver Licence category
    private String DL_number; // čislo vodičskeho preukazu
    private String DL_expire; //platnost
    private int drivenDistance;
    public Driver(String name, String surname, int age, char DL_category, String DL_number, String DL_expire) {
        super(name, surname, age);
        this.DL_category = DL_category;
        this.DL_number = DL_number;
        this.DL_expire = DL_expire;
        this.drivenDistance = 0;
    }

    public void addDrivenDistance(int drivenDistance) {
        this.drivenDistance += drivenDistance;
    }

    public char getDL_category() {
        return DL_category;
    }

    @Override
    public String toString() {
        return "Driver:\n" + name +" " + surname +", "+ age + " years old.\n" +
                "Driver Licence: Number: " + DL_number +". Type: '" + DL_category + "'. Expiry: " + DL_expire+".\n" +
                "Drove by car: " + drivenDistance+" km."+
                "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return DL_category == driver.DL_category && drivenDistance == driver.drivenDistance && Objects.equals(DL_number, driver.DL_number) && Objects.equals(DL_expire, driver.DL_expire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(DL_category, DL_number, DL_expire, drivenDistance);
    }

}
