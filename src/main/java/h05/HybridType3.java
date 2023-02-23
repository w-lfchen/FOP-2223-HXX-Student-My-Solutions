package h05;

/**
 * the third attempt at making a good hybrid vehicle. This one implements the HybridVehicle interface and as such, has a preferredDriveType attribute.
 */
public class HybridType3 implements HybridVehicle{
    private static FuelType fuelType;
    private static double averageConsumption;
    private static boolean standardVoltageChargeable;
    private static boolean highVoltageChargeable;
    private static DriveType preferredDriveType;

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
     * @param speed the current speed of the vehicle
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
     * set the fuelType attribute to the supplied fuel type
     * @param fuelType the FuelType value to set fuelType to
     */
    public void setFuelType(FuelType fuelType) {
        HybridType3.fuelType = fuelType;
    }

    /**
     * set averageConsumption to a supplied value
     * @param averageConsumption the value to set averageConsumption to
     */
    public void setAverageConsumption(double averageConsumption) {
        HybridType3.averageConsumption = averageConsumption;
    }

    /**
     * toggles the boolean value that is true when the vehicle is chargeable using standard voltage and false if not
     */
    public void toggleStandardVoltageChargeable(){
        standardVoltageChargeable = !standardVoltageChargeable;
    }
    /**
     * toggles the boolean value that is true when the vehicle is chargeable using high voltage and false if not
     */
    public void toggleHighVoltageChargeable(){
        highVoltageChargeable = !highVoltageChargeable;
    }

    /**
     * does nothing.
     * @param additionalChargeVolume amount to be charged
     * @param distance distance to move
     */
    @Override
    public void letsGo(byte additionalChargeVolume, int distance) {

    }

    /**
     * returns the currently preferred DriveType
     * @return the value of the preferredDriveType attribute
     */
    @Override
    public DriveType getPreferredDriveType() {
        return preferredDriveType;
    }

    /**
     * toggles the preferredDriveType attribute between the two DriveType values, ELECTRICAL and FUEL_BASED.
     */
    public void togglePreferredDriveType(){
        preferredDriveType = (preferredDriveType == DriveType.ELECTRICAL ? DriveType.FUEL_BASED : DriveType.ELECTRICAL);
    }
}
