package h05;

/**
 * the first hybrid typ vehicle, has logic to use both fuel and electricity and therefore implements the FuelDriven and ElectricalDriven interfaces
 */
public class HybridType1 implements FuelDriven,ElectricallyDriven{
    private static FuelType fuelType;
    private static double averageConsumption;
    private static boolean standardVoltageChargeable;
    private static boolean highVoltageChargeable;

    /**
     * returns the fuel type
     * @return the fuel type
     */
    @Override
    public FuelType getFuelType() {
        return fuelType;
    }

    /**
     * returns the average consumption as a double value
     * @param speed this parameter is not used.
     * @return the averageConsumption attribute as a double value
     */
    @Override
    public double getAverageConsumption(double speed) {
        return averageConsumption;
    }

    /**
     * returns true, if chargeable using standard voltage, false if not
     * @return true, if chargeable using standard voltage, false if not
     */
    @Override
    public boolean standardVoltageChargeable() {
        return standardVoltageChargeable;
    }

    /**
     * returns true, if chargeable using high voltage, false if not
     * @return true, if chargeable using high voltage, false if not
     */
    @Override
    public boolean highVoltageChargeable() {
        return highVoltageChargeable;
    }

    /**
     *set the fuel type
     * @param fuelType FuelType to set the fuelType attribute to
     */
    public void setFuelType(FuelType fuelType) {
        HybridType1.fuelType = fuelType;
    }
    /**
     *set the average consumption
     * @param averageConsumption double value to set the averageConsumption attribute to
     */
    public void setAverageConsumption(double averageConsumption) {
        HybridType1.averageConsumption = averageConsumption;
    }

    /**
     * toggles whether the vehicle is chargeable using standard voltage
     */
    public void toggleStandardVoltageChargeable(){
        standardVoltageChargeable = !standardVoltageChargeable;
    }
    /**
     * toggles whether the vehicle is chargeable using high voltage
     */
    public void toggleHighVoltageChargeable(){
        highVoltageChargeable = !highVoltageChargeable;
    }

    /**
     * does nothing. is just inherited from the ElectricallyDriven interface.
     * @param additionalChargeVolume amount to be charged. is ignored as the method does nothing.
     * @param distance distance to move. is ignored as the method does nothing.
     */
    @Override
    public void letsGo(byte additionalChargeVolume, int distance) {

    }
}
