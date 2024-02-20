import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Service {
    private Map<String,ServiceInfo> ServiceBook = new LinkedHashMap<>();
    public Service(Map<String,ServiceInfo> serviceBook) {
        ServiceBook = serviceBook;
    }
    public Map<String, ServiceInfo> getServiceBook() {
        return ServiceBook;
    }
    public String getLastServiceDate(){
        String out= "";
        for (Map.Entry<String, ServiceInfo> entry : ServiceBook.entrySet())
            out = entry.getKey();
        return out;
    }
    public ServiceInfo getLastServiceInfo(){
        ServiceInfo out = new ServiceInfo(null,0);
        for (Map.Entry<String, ServiceInfo> entry : ServiceBook.entrySet())
            out = entry.getValue();
        return out;
    }
    public void LastService(){
        System.out.println("Last service: " + getLastServiceDate() + " | " + getLastServiceInfo().toString()+"\n");
    }
    public void makeServiceDiagnostics(String date, String serviceInfo, int mileage, Car car){
        if(date == null || date.length()!=7 || !date.contains("/") || serviceInfo==null || mileage<this.getLastServiceInfo().getMileage())
            throw new IllegalArgumentException("Wrong input.");
        this.ServiceBook.put(date, new ServiceInfo(serviceInfo, mileage));
        if(car.getActualConsumptionIndex()!=0) {
            car.defaultConsumptionIndex();
            car.setFuelConsumption(car.getFuelConsumption() * 100 / (100 + Car.PERCENT_OF_CONSUMPTION_CHANGE));
        }
        System.out.println("Service Diagnostics was made successfully!\n");
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Objects.equals(ServiceBook, service.ServiceBook);
    }
    @Override
    public int hashCode() {
        return Objects.hash(ServiceBook);
    }
}
