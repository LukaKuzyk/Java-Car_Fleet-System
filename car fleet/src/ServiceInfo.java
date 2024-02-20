public class ServiceInfo implements Comparable<ServiceInfo>{
    private String Info;
    private int mileage;
    private static ServiceInfo serviceInfo;
    public ServiceInfo(String info, int mileage) {
        Info = info;
        this.mileage = mileage;
    }

    // SINGELTON
    public static ServiceInfo getServiceInfo() {
        if (serviceInfo == null) {
            serviceInfo = new ServiceInfo(null,0);
        }
        return serviceInfo;
    }
    @Override
    public String toString() {
        return Info + ". Mileage: " + mileage + " km.";
    }
    public int getMileage() {
        return mileage;
    }
    @Override
    public int compareTo(ServiceInfo anotherServiceInfo) {
        int km = mileage - anotherServiceInfo.mileage;
        if(km!=0) {
            return km;
        }else
            return Info.compareTo(anotherServiceInfo.Info);
    }
}
