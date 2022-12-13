package h04;

/**
 * an interface aimed at providing a basic framework for handling different types of coins one might encounter, such as those found in the CoinType enum
 */
public interface WithCoinTypes {
    /**
     * When encountering different types of coins and having different amounts of them with the desire to change said amounts, one of course needs to have a means of controlling the amount and type to be set.
     * @param type the type of coin to be set
     * @param amount the amount the given type is to be set to
     */
    void setNumberOfCoinsOfType(CoinType type, int amount);

    /**
     * while on could print out a whole list or array of the types and amounts of coins they possess, this could be quite unnecessary, so we decide that a simple method should just return the amount of a single given type.
     * @param type the type one would like to get the amount of coins of
     * @return the amount of coins of the given type
     */
    int getNumberOfCoinsOfType(CoinType type);
}
