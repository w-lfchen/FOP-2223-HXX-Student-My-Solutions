package h05;

/**
 * Interface to add basic functionality for a vehicle based on fuel
 */
public interface FuelDriven {
    /**
     * knowing the type of fuel of the vehicle you are refueling might prolong the vehicle's lifespan. this method should return the fuel type if implemented
     * @return the fuel type if implemented correctly
     */
    FuelType getFuelType();

    /**
     * should be given a speed value in order to estimate average consumption. Of course this estimate is not very accurate, but it is an estimate nonetheless
     * @param speed the current speed of the vehicle
     * @return the average consumption based on the given speed
     */
    double getAverageConsumption(double speed);
}
