package h04;

/**
 * A class that implements the WithCoinTypes interface while staying clear of robots, presumably in order to show the advantages of working with interfaces
 */
public class CoinCollection implements WithCoinTypes{

    private int numberOfSilverCoins;
    private int numberOfBrassCoins;
    private int numberOfCopperCoins;

    /**
     * constructor for the CoinCollection class. Sets up the amounts of coins
     * @param numberOfSilverCoins number of silver coins. should be equal to or greater than 0
     * @param numberOfBrassCoins number of brass coins. should be equal to or greater than 0
     * @param numberOfCopperCoins number of copper coins. should be equal to or greater than 0
     */
    public CoinCollection(int numberOfSilverCoins, int numberOfBrassCoins, int numberOfCopperCoins){
        this.numberOfSilverCoins = numberOfSilverCoins;
        this.numberOfBrassCoins = numberOfBrassCoins;
        this.numberOfCopperCoins = numberOfCopperCoins;
    }

    /**
     * expects a CoinType, returns the amount of coins of that type
     * @param type the type one would like to get the amount of coins of
     * @return the amount of coins of the given type
     */
    public int getNumberOfCoinsOfType(CoinType type){
        return switch (type) {
            case SILVER -> numberOfSilverCoins;
            case BRASS -> numberOfBrassCoins;
            case COPPER -> numberOfCopperCoins;
        };
    }

    /**
     * sets the amount of coins of a given type. if the supplied amount is negative, it is changed to 0
     * @param type the type of coin to be set
     * @param amount the amount the given type is to be set to
     */
    public void setNumberOfCoinsOfType(CoinType type, int amount){
        if(amount < 0){
            amount = 0;
        }
        switch (type){
            case SILVER -> numberOfSilverCoins = amount;
            case BRASS -> numberOfBrassCoins = amount;
            case COPPER -> numberOfCopperCoins = amount;
        }
    }

    /**
     * returns the number of silver coins
     * @return the number of silver coins
     */
    public int getNumberOfSilverCoins() {
        return numberOfSilverCoins;
    }

    /**
     * returns the number of brass coins
     * @return the number of brass coins
     */
    public int getNumberOfBrassCoins() {
        return numberOfBrassCoins;
    }

    /**
     * returns the number of copper coins
     * @return the number of copper coins
     */
    public int getNumberOfCopperCoins() {
        return numberOfCopperCoins;
    }

    /**
     * removes 1 coin from the amount of coins of the given type. does nothing if there are no coins of that type
     * @param type the type of coin whose amount is to be reduced by 1
     */
    public void removeCoin(CoinType type){
        switch (type){
            case SILVER :   if(getNumberOfSilverCoins() > 0)
                                numberOfSilverCoins--;
                            break;
            case BRASS  :   if(getNumberOfBrassCoins() > 0)
                                numberOfBrassCoins--;
                            break;
            case COPPER :   if(getNumberOfCopperCoins() > 0)
                                numberOfCopperCoins--;
                            break;
        }
    }

    /**
     * adds 1 to the amount of coins of the given type.
     * @param type the type of coin whose amount is to be increased by 1
     */
    public void insertCoin(CoinType type){
        switch (type){
            case SILVER -> numberOfSilverCoins++;
            case BRASS -> numberOfBrassCoins++;
            case COPPER -> numberOfCopperCoins++;
        }
    }
}
