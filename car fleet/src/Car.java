import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Car extends Service implements DriverSet, CarSet {
    private String brand;
    private String model;
    private String color;
    private String type;
    private char category;
    private int year;
    private int mileage;
    private float fuelConsumption;
    private float fuelTank;
    private int actualConsumptionIndex; // index pomocov ktoreho sa meni spotreba paliva
    private Driver driver;
    public static final int SERVICE_TIME = 10000; // konštanta, ktorá určuje počet kilometrov medzi servisnými intervalmi
    public static final float FUEL_TANK = 70F;
    public static final int PERCENT_OF_CONSUMPTION_CHANGE = 10; // %
    public Car(String brand, String model, String color, String type, char category, int year, int mileage, int fuelConsumption, int fuelTank, Map<String,ServiceInfo> serviceBook) {
        super(serviceBook);
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.type = type; // sedan, combi
        this.category = category; // B, C
        this.year = year;
        this.mileage = mileage; // 200 000 km
        this.fuelConsumption = fuelConsumption; // spotreba 7 l/km
        this.fuelTank = fuelTank;   // aktualny pocet paliva
        this.actualConsumptionIndex = 0;
    }
    public int getMileage() {
        return mileage;
    }
    public int getActualConsumptionIndex(){
        return actualConsumptionIndex;
    }
    public void defaultConsumptionIndex(){
        this.actualConsumptionIndex = 0;
    }
    public void setFuelConsumption(float fuelConsumption){
        this.fuelConsumption=fuelConsumption;
    }
    public float getFuelConsumption() {
        return fuelConsumption;
    }
    @Override
    public String toString() {
        String out = "Car:\n" +brand + " " +model + " " + year +", "+type +", " + color + "."
                + "\n" +mileage + " km, " + fuelConsumption + " l/100km, " + fuelTank + " liters of fuel."
                + "\nPossible range of travel: " + possibleTravelDistance() + "km."
                + "\n" + RecommendsForService()
                + "\n" + RecommendsForTravel()
                +"\n";
        if(driver != null)
            out +="\n"+ driver;
        return out;
    }
    private String RecommendsForTravel(){
        if(this.NearestVisitOfService()<500 && NearestVisitOfService()>0){
            return "Travel is not recommended, because Service diagnostics will be carried out SOON.";
        } else if (this.NearestVisitOfService()>7000) {
            return "Car is in perfect condition.";
        }else if (this.NearestVisitOfService()<5000) {
            return "Car is normal condition. But fuel consumption increased by 10%.";
        }else if(this.NearestVisitOfService()<=0){
            return "Oops, make service diagnostics now!";
        } else
            return "Car is in good condition.";
    }
    private String RecommendsForService(){
        if(NearestVisitOfService()>0)
            return "The nearest recommended to visit service is "+ NearestVisitOfService()+" km away.";
        else
            return "You should have had a service diagnosis "+ NearestVisitOfService()*-1+" km ago.";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return category == car.category && year == car.year && mileage == car.mileage && Float.compare(car.fuelConsumption, fuelConsumption) == 0 && Float.compare(car.fuelTank, fuelTank) == 0 && actualConsumptionIndex == car.actualConsumptionIndex && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(color, car.color) && Objects.equals(type, car.type) && Objects.equals(driver, car.driver);
    }
    @Override
    public int hashCode() {
        return Objects.hash(brand, model, color, type, category, year, mileage, fuelConsumption, fuelTank, actualConsumptionIndex, driver);
    }
    @Override
    public void addDriver(Driver driver) {
        if (driver.getDL_category() != category){
            throw new IllegalArgumentException("Driver cant drive this category of car.");
        }
        if(this.driver == null) {
            this.driver = driver;
            System.out.println("Driver was added successfully.\n");
        }else
            System.out.println("This car already has a driver. First remove actual driver and when repeat.\n");
    }
    public void removeDriver() {
        this.driver = null;
        System.out.println("Driver was removed successfully.\n");
    }
    public int possibleTravelDistance(){
        return (int) (fuelTank/fuelConsumption*100);
    }
    public float possibleCarTanking(){
        return FUEL_TANK-fuelTank;
    }
    public int NearestVisitOfService(){
        return SERVICE_TIME-(mileage-this.getServiceBook().get(this.getLastServiceDate()).getMileage());
    }
    public void tankCar(float fuel){
        if(fuel<1)
            throw new IllegalArgumentException("Value must be positive.");
        if(fuel>100 || fuel+this.fuelTank>FUEL_TANK)
            throw new IllegalArgumentException("Fuel tank is not infinity. Max tank capacity is "
                    + FUEL_TANK + " l. You can tank max: " + possibleCarTanking() + " l.\n");
        this.fuelTank+=fuel;
        System.out.println("Car was tanked up to " + fuel +" litres. Actual fuel tank: " + this.fuelTank+" litres.\n");
    }
    public void driveCar(int travel) {
        if (travel < 1)
            throw new IllegalArgumentException("Value must be positive.");
        if (travel > this.possibleTravelDistance()){
            throw new IllegalArgumentException("Max distance that you can drive is: " + this.possibleTravelDistance()
                    + " km. "+travel+" km is to far. "+"Please, tank the car.\n");
        }
        this.mileage+=travel;
        this.driver.addDrivenDistance(travel);
        if(this.NearestVisitOfService()<SERVICE_TIME/2 && this.actualConsumptionIndex == 0){
            this.fuelConsumption+=(fuelConsumption * PERCENT_OF_CONSUMPTION_CHANGE/100);
            this.actualConsumptionIndex++;
        }

        this.fuelTank-=((float) travel/100)*this.fuelConsumption;
        System.out.println("Car has traveled " + travel +" km. Actual mileage: " + this.mileage+" km. Fuel tank: "
                + this.fuelTank + " l. Fuel consumption per 100km is: " + this.fuelConsumption + " l.\n");
    }
    public void ReadServiceBook(){
        Map<String,ServiceInfo> ServiceBookCar = getServiceBook();
        System.out.println("Service book:");
        for(Map.Entry<String, ServiceInfo> entry : ServiceBookCar.entrySet())
            System.out.println(entry.getKey() +" "+ entry.getValue());
    }

}
