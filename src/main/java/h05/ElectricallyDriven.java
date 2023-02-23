package h05;

/**
 * Implements core infrastructure for an electric vehicle
 */
public interface ElectricallyDriven {
    /**
     * is the vehicle safe to charge using standard voltage?
     * @return true if so, false if not
     */
    boolean standardVoltageChargeable();
    /**
     * is the vehicle safe to charge using high voltage?
     * @return true if so, false if not
     */
    boolean highVoltageChargeable();

    /**
     * charge the vehicle in question, then move by a distance, most likely depleting the battery as we move. Implementation depends on the vehicle
     * @param additionalChargeVolume amount to be charged
     * @param distance distance to move
     */
    void letsGo(byte additionalChargeVolume, int distance);
}
