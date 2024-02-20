import java.util.Map;

public class CarFactory {
    //Tak vytvarame auta
    public static Car createCar(String condition,String brand, String model, String color, String type, char category, int year, int mileage, int fuelConsumption, int fuelTank, Map<String,ServiceInfo> serviceBook ) {
        if (condition.equals("new")) {
            return new CarNew(brand,  model,  color,  type,  category,  year,  mileage,  fuelConsumption,  fuelTank, serviceBook);
        }else if(condition.equals("used")){
            return new CarUsed(brand,  model,  color,  type,  category,  year,  mileage,  fuelConsumption,  fuelTank, serviceBook);
        }else
            throw new IllegalArgumentException("Input is not valid. Expect: \"new\" or \"used\". Not \""+condition+"\".");
    }

}
