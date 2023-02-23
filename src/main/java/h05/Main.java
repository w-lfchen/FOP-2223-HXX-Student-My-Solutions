package h05;

/**
 * Main entry point in executing the program.
 */
public class Main {

    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        /*System.out.println("Hello World!");
        FuelDrivenVehicle v = new FuelDrivenVehicle(FuelType.DIESEL, TransportType.AIRCRAFT, 23);
        String test = v.toString();
        System.out.println(test);*/
        for(TransportType d: TransportType.values()){
            FuelDrivenVehicle v = new FuelDrivenVehicle(FuelType.DIESEL, d, 23);
            String test = v.toString();
            System.out.println(test);
        }
    }
}
