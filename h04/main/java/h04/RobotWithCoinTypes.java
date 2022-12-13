package h04;

import fopbot.Direction;
import fopbot.Robot;

/**
 * this robot implements functionality to support different types of coins. In order to achieve this, it implements the WithCoinTypes Interface. It also extends the regular Robot.
 */
public class RobotWithCoinTypes extends Robot implements WithCoinTypes{
    private int numberOfSilverCoins;
    private int numberOfBrassCoins;
    private int numberOfCopperCoins;

    /**
     * a constructor for the RobotWithCoinTypes class. calls the super with the given parameters that fit and the sum of all coins since the Robot class only supports one type.
     * @param x the x value the robot should be placed on
     * @param y the y value the robot should be placed on
     * @param direction direction the robot should face upon being created
     * @param numberOfSilverCoins amount of silver coins to give the robot
     * @param numberOfBrassCoins amount of brass coins to give the robot
     * @param numberOfCopperCoins amount of copper coins to give the robot
     */
    public RobotWithCoinTypes(int x, int y, Direction direction, int numberOfSilverCoins, int numberOfBrassCoins, int numberOfCopperCoins){
        super(x, y, direction, numberOfSilverCoins  + numberOfBrassCoins + numberOfCopperCoins);
        this.numberOfSilverCoins = numberOfSilverCoins;
        this.numberOfBrassCoins = numberOfBrassCoins;
        this.numberOfCopperCoins = numberOfCopperCoins;
    }

    /**
     * sets the amount of copper coins and the numberOfCoins attribute of the Robot class.
     * @param amount the value the number of (copper) coins should be set to
     */
    public void setNumberOfCoins(int amount){
        if (amount < 0){
            super.setNumberOfCoins(amount);
        }
        else{
            numberOfCopperCoins = amount;
            super.setNumberOfCoins(amount + numberOfSilverCoins + numberOfBrassCoins);
        }
    }

    /**
     * implements the interface's method to get the amount fo coins of a specific type
     * @param type the type one would like to get the amount of coins of
     * @return the number of coins of the given type
     */
    public int getNumberOfCoinsOfType(CoinType type){
        return switch (type) {
            case SILVER -> numberOfSilverCoins;
            case BRASS -> numberOfBrassCoins;
            case COPPER -> numberOfCopperCoins;
        };
    }

    /**
     * sets the number of a specified coin type to a given value. also recalculates Robot's number of coins at the end to achieve consistency
     * @param type the type of coin to be set
     * @param amount the amount the given type is to be set to
     */
    public void setNumberOfCoinsOfType(CoinType type, int amount){
        if(amount < 0){
            super.setNumberOfCoins(amount);
        }
        else{
            switch (type){
                case SILVER -> numberOfSilverCoins = amount;
                case BRASS -> numberOfBrassCoins = amount;
                case COPPER -> numberOfCopperCoins = amount;
            }
            super.setNumberOfCoins(numberOfSilverCoins + numberOfBrassCoins + numberOfCopperCoins);
        }
    }

    /**
     * robots can pick up coins, every coin found in the world is treated as a copper one.
     */
    public void pickCoin(){
        super.pickCoin();
        numberOfCopperCoins++;
    }

    /**
     * he who has the ability to take must also be able to give (unless they do not have any coins left) (also, they try to get place of the least valuable coins first)
     * calls Robot's putCoin method, the world only accepts one type of coin, but we still reduce the number of our coin types accordingly. tries to place the least valuable coin first (copper),
     * then brass and, as a last resort, silver. If all coin counts are 0 or lower (which should not happen), putCoin should throw an exception since the numberOfCoins attribute should then also be 0.
     */
    public void putCoin(){
        if(numberOfCopperCoins > 0){
            numberOfCopperCoins--;
        } else if (numberOfBrassCoins > 0) {
            numberOfBrassCoins--;
        } else if (numberOfSilverCoins > 0) {
            numberOfSilverCoins--;
        }
        super.putCoin();
    }
}
