import java.util.ArrayList;

/**
 * BaseUser
 */
public interface BaseUser {

    //Setter getters
    public void setName(String Name);
    public String getName();

    public void setID(String id);
    public String getID();

    public void setPassword(String p);
    public String getPassword();

    //User functions for stock subscriptions
    public ArrayList<Stock> getSubscribedStocks();
    public void subscribeStock(Stock s);
    public void unsubscribeStock(Stock s);

    //User function for getting updates on count/ price
    public void countUpdate(Stock s, int prevCount, int currentCount);
    public void priceUpdate(Stock s, Float prevPrice, Float currentPrice);

    //User function for viewing subscribed stocks
    public void viewSubscribedStocks();

    //Admin Function for manipulating stocks
    public void changeCount(Stock s, int amount);
    public void changePrice(Stock s, String opcode, Float amount);
}