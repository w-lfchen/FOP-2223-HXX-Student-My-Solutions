package h05;

/**
 * a class to represent a fuel-powered vehicle. As such, it extends MeansOfTransport since that is what vehicles are currently designed to be and implements the FuelDriven interface.
 */
public class FuelDrivenVehicle extends MeansOfTransport implements FuelDriven{
    private FuelType fuelType;
    private int fillingLevel;

    /**
     * returns the current fuel type
     * @return the current fuel type
     */
    @Override
    public FuelType getFuelType() {
        return fuelType;
    }

    /**
     * returns the average consumption for a given speed. if the speed is negative or 0, we do not consume fuel on average. Average fuel consumption linearly scales by one extra
     * unit of fuel consumed on average per 10 units of speed gained. This is capped at 200 speed units, where fuel consumption calculations return a flat 20 average fuel consumed,
     * with this remaining the same for speeds above 200, implying that friction is not real.
     * @param speed the current speed of the vehicle
     * @return the calculated average consumption
     */
    @Override
    public double getAverageConsumption(double speed) {
        if(speed < 0){
            return 0;
        } else if (speed > 200) {
           return 20;
        } else {
            return speed*0.1;
        }
    }

    /**
     * how full is your fuel tank? This method returns the answer to that question as an integer.
     * @return the fillingLevel attribute as an integer value
     */
    public int getFillingLevel() {
        return fillingLevel;
    }

    /**
     * will increase fillingLevel by the supplied value. Does nothing if 0 or the given value is negative
     * @param fillValue amount to be added to the fillingLevel. Expected to be positive, will do nothing if it isn't.
     */
    public void fillUp(int fillValue){
        if(fillValue > 0){
            fillingLevel+=fillValue;
        }
    }

    /**
     * fuel is consumed at 1 unit per 10 units of distance covered. If the distance is equal to or less than 0, no fuel will be consumed. It is not possible to move beyond what is possible with the current fillingLevel. Returns the actual distance traveled.
     * @param distance the distance one should try to move
     * @return the actual distance traveled as integer.
     */
    @Override
    public int letMeMove(int distance) {
        int consumption;
        if(distance < 0){
            consumption = 0;
        } else if (distance < 10*fillingLevel) {
            fillingLevel -= distance/10;
            consumption = distance/10;
        } else {
            consumption = fillingLevel;
            fillingLevel = 0;
        }
        return consumption*10;
    }

    /**
     * constructor for a fuel-powered vehicle. expects the fuel and transport type and the initial amount of fuel and sets the attributes accordingly.
     * @param fuelType the fuel type the vehicle should use
     * @param transportType the type of transport vehicle to be constructed
     * @param fillingLevel th initial amount of fuel to give the vehicle
     */
    public FuelDrivenVehicle(FuelType fuelType, TransportType transportType, int fillingLevel){
        this.fuelType = fuelType;
        this.transportType = transportType;
        this.fillingLevel = fillingLevel;
    }
}
