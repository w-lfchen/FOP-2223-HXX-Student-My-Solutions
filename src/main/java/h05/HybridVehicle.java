package h05;

/**
 * hybrid vehicles can run on both fuel and electricity. Therefore, this interface extends the FuelDriven and ElectricallyDriven interfaces and introduces logic to allow choosing the operational mode.
 */
public interface HybridVehicle extends FuelDriven,ElectricallyDriven{
    /**
     * should return the currently preferred DriveType, most likely either electric or fuel-based
     * @return the currently preferred DriveType as an element of the DriveType enumeration
     */
   DriveType getPreferredDriveType();
}
