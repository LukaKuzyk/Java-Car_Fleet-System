import java.util.Map;

public class CarUsed extends Car{
    public CarUsed(String brand, String model, String color, String type, char category, int year, int mileage, int fuelConsumption, int fuelTank, Map<String, ServiceInfo> serviceBook) {
        super(brand, model, color, type, category, year, mileage, fuelConsumption, fuelTank, serviceBook);
        System.out.println("Car was added. Actual condition of car is unknown. Please, make service diagnostics first.\n");
    }
}
