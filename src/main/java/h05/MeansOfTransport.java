package h05;

/**
 * there are many means of transport. This abstract class provides a more concrete framework for the different means of transportation one might encounter
 */
public abstract class MeansOfTransport {
    /**
     * of course, we need to specify which type of vehicle we are using.
     */
    protected TransportType transportType;

    /**
     * a get method that returns the current transport type.
     * @return the current transport type.
     */
    public TransportType getTransportType(){
        return transportType;
    }

    /**
     * transport is based on moving around, so it is only fit to be able to do so. This method should allow for that, if implemented properly.
     * @param distance the distance one should try to move
     * @return placeholder integer return, based on implementation this could be the distance that one actually travelled or maybe the amount of fuel that was consumed as a result of moving
     */
    public abstract int letMeMove(int distance);

    /**
     * overrides the toString method that usually returns a string representation of the object to instead return a nice sentence to name the current transport type.
     * the TransportType value is formatted in the following way: If the first character is a Letter, it is capitalised. every other letter is turned to lowercase. numbers are unaffected, however all characters that are not part of the 26-letter alphabet or a number are turned into spaces.
     * @return "I am a/an [the current transport type, nicely formatted]."
     */
    @Override
    public String toString() {
        String returnString;
        if(transportType == null){
            returnString = "undefined";
        } else {
            returnString = transportType.name();
        }
        char[] chars = returnString.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] -=32;
        }
        for(int i = 1;i< chars.length;i++){
            if(chars[i] >= '0' && chars[i] <= '9'){
                continue;
            } else if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i]+=32;
            } else if (chars[i] >= 'a' && chars[i] <= 'z') {
                continue;
            } else {
                chars[i] = ' ';
            }
        }
        returnString = new String(chars);
        if (returnString.charAt(0) == 'A' || returnString.charAt(0) == 'E' || returnString.charAt(0) == 'I' || returnString.charAt(0) == 'O' || returnString.charAt(0) == 'U') {
            return "I am an " + returnString + ".";
        } else {
            return "I am a " + returnString + ".";
        }

    }
}
