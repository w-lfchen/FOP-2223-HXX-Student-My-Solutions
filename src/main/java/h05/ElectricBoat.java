package h05;

import java.util.function.IntSupplier;

/**
 * An electric boat runs off electricity and therefore implements the ElectricallyDriven interface. It is also a means of transport and, as such, extends MeansOfTransport. I assume that the IntSupplier implementation is used in order to teach things.
 */
public class ElectricBoat extends MeansOfTransport implements ElectricallyDriven, IntSupplier {
    private byte specificType;
    private int currentCharge;
    private int capacity;

    /**
     * returns the specific type
     * @return the specific type
     */
    public byte getSpecificType() {
        return specificType;
    }

    /**
     * returns the current charge as read from the currentCharge attribute
     * @return the current charge
     */
    public int getCurrentCharge() {
        return currentCharge;
    }

    /**
     * returns the current capacity as read from the capacity attribute
     * @return the current capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * returns whether the vehicle is compatible with standard voltage. In order to be compatible, the specificType attribute must be either 6,11,12 or 22
     * @return true if chargeable with standard voltage, false if not
     */
    @Override
    public boolean standardVoltageChargeable() {
        return (specificType == 6 || specificType == 11 || specificType == 12 || specificType == 22);
    }

    /**
     * returns whether the vehicle is compatible with high voltage. In order to be compatible, the specificType attribute must be dividable by 2 and specificType+1 must be dividable by 3.
     * @return true if chargeable with high voltage, false if not
     */

    @Override
    public boolean highVoltageChargeable() {
        return ((specificType % 2 == 0) && ((specificType + 1) % 3 == 0));
    }

    /**
     * charges the theoretical battery by a supplied amount, but only up to maximum capacity and needs a distance to then be traveled by invoking the letMeMove method
     * @param additionalChargeVolume amount to be charged
     * @param distance distance to move
     */

    @Override
    public void letsGo(byte additionalChargeVolume, int distance) {
        if(currentCharge+additionalChargeVolume >= capacity){
            currentCharge = capacity;
        } else {
            currentCharge += additionalChargeVolume;
        }
        letMeMove(distance);
    }

    /**
     * discharges the battery by 1 or the traveled distance divided by 100, whichever is lower. Returns the actual covered distance, cant travel further than the charge allows
     * @param distance the distance one should try to move
     * @return how much the battery charge has been reduced by
     */
    @Override
    public int letMeMove(int distance) {
        int reduction = 1;
        if(reduction > distance/100){
            reduction = 0;
        }
        else if(currentCharge-reduction <= 0){
            reduction = currentCharge;
            currentCharge = 0;
        } else {
            currentCharge -= reduction;
        }
        return reduction;
    }

    /**
     * returns the difference between capacity and currentCharge
     * @return the difference between capacity and currentCharge
     */
    @Override
    public int getAsInt() {
        return capacity-currentCharge;
    }

    /**
     * sets the specificType attribute to a value in a range between 0 and 30. If the supplied value is outside that range, it is set to the next bound, that being 0 for negative number or 30 for numbers above 30
     * @param specificType the amount specificType should be set to
     * @return the previous value of specificType
     */
    public byte setSpecificType(byte specificType) {
        byte previousType = this.specificType;
        if(specificType < 0){
            this.specificType = 0;
        } else if (specificType > 30) {
            this.specificType = 30;
        } else {
            this.specificType = specificType;
        }
        return previousType;
    }

    /**
     * constructor for the electric boat. sets the specificType attribute using the setSpecificType method, the currentCharge and the capacity to the supplied values.
     * Also sets the transportType to VESSEL. if capacity is negative, it is set to 0.
     * If the currentCharge value is higher than capacity, it is set to the capacity's value, if this is negative, it is set to 0.
     * @param specificType the value specificType should be set to
     * @param currentCharge the value currentCharge should be set to
     * @param capacity the value the capacity should be set to
     */
    public ElectricBoat(byte specificType, int currentCharge, int capacity){
        setSpecificType(specificType);
        transportType = TransportType.VESSEL;
        this.currentCharge = currentCharge;
        this.capacity = capacity;
        if(this.capacity < 0){
            this.capacity = 0;
        }
        if(this.currentCharge < 0){
            this.currentCharge = 0;
        }
        if(currentCharge > this.capacity){
            this.currentCharge = this.capacity;
        }
    }
}
