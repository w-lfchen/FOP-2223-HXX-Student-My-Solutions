package h05;

/**
 * Since this enumeration had to be implemented alongside the HybridVehicle interface, one might assume this interface
 * implements functionality to assist the hybrid vehicles that might end up implementing this interface.
 */
public enum DriveType {
    /**
     * A DriveType suitable to describe operating at the expense of fuel
     */
    FUEL_BASED,
    /**
     * A DriveType suitable to describe operating at the expense of electricity
     */
    ELECTRICAL,
}
