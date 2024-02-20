import java.util.Map;

public class CarNew extends Car{

    public CarNew(String brand, String model, String color, String type, char category, int year, int mileage, int fuelConsumption, int fuelTank, Map<String, ServiceInfo> serviceBook) {
        super(brand, model, color, type, category, year, mileage, fuelConsumption, fuelTank, serviceBook);
        System.out.println("New Car was added to Car Fleet.\n");
    }
}
