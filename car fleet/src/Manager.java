public class Manager extends Person{
    public Manager(String name, String surname, int age) {
        super(name, surname, age);
    }

    public void readServiceBook (Car car){
        car.ReadServiceBook();
    }

    public void carInfo (Car car){
        System.out.println(car);
    }

    public void readLastService(Car car){
        car.LastService();
    }

    public void addDriverToCar(Driver driver, Car car){
        car.addDriver(driver);
    }

    public void removeDriverFromCar(Car car){
        car.removeDriver();
    }

    public void getManagerInfo(){
        System.out.println(this);
    }

    public void makeServiceDiagnostics(String date, String serviceInfo, Car car) {
                car.makeServiceDiagnostics(date, serviceInfo, car.getMileage(), car);
    }


}
