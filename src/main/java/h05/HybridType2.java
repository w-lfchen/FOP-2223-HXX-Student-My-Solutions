package h05;

/**
 * this hybrid vehicle extends MeansOfTransport and implements FuelDriven and ElectricallyDriven in oder to create a better hybrid vehicle. it uses a HybridType1 Object to store its values.
 */
public class HybridType2 extends MeansOfTransport implements FuelDriven, ElectricallyDriven{
    private HybridType1 hybridObject;

    /**
     * constructor for the HybridType2 class. Does nothing special except assigning a new HybridType1 Object to the hybridObject attribute.
     */
    public HybridType2(){
        hybridObject = new HybridType1();
    }

    /**
     * returns the fuel type
     * @return the fuel type
     */
    @Override
    public FuelType getFuelType() {
        return hybridObject.getFuelType();
    }

    /**
     * returns the average consumption as a double value
     * @param speed the current speed of the vehicle
     * @return the averageConsumption attribute as a double value
     */
    @Override
    public double getAverageConsumption(double speed) {
        return hybridObject.getAverageConsumption(speed);
    }

    /**
     * returns true, if chargeable using standard voltage, false if not
     * @return true, if chargeable using standard voltage, false if not
     */
    @Override
    public boolean standardVoltageChargeable() {
        return hybridObject.standardVoltageChargeable();
    }

    /**
     * returns true, if chargeable using high voltage, false if not
     * @return true, if chargeable using high voltage, false if not
     */
    @Override
    public boolean highVoltageChargeable() {
        return hybridObject.highVoltageChargeable();
    }

    /**
     * calls the hybridObject's letsGo method using the supplied parameters
     * @param additionalChargeVolume amount to be charged
     * @param distance distance to move
     */
    @Override
    public void letsGo(byte additionalChargeVolume, int distance) {
        hybridObject.letsGo(additionalChargeVolume, distance);
    }

    /**
     * returns 0.
     * @param distance does nothing
     * @return returns 0
     */
    @Override
    public int letMeMove(int distance) {
        return 0;
    }
}
