import java.io.Serializable;
import java.util.ArrayList;

public class User implements BaseUser, Serializable{
    
    String name;
    String ID;
    Boolean loggedIn;

    ArrayList<String> notifications;
    ArrayList<Stock> subscribedStocks;

    User() {}
    User(String Name, String id){
        name = Name; ID = id;
        loggedIn = false;

        subscribedStocks = new ArrayList<Stock>();
        notifications = new ArrayList<String>();
    }

    //Setters getters
    public void setName(String Name) { name = Name; }
    public String getName() { return name; }

    public void setID(String id) { ID = id; }
    public String getID() { return ID; }

    public void setLoggedIn(Boolean value) { loggedIn = value; }
    public Boolean getLoggedIn() { return loggedIn; }

    public void setPassword(String p) {}
    public String getPassword() { return "user"; }

    //Functions to store and display notifications
    public ArrayList<String> getNotifications() { return notifications; }
    public void addToNotificatins(String message) { notifications.add(message); }
    public void printAllNotifications(){
        if(notifications.isEmpty()){
            System.out.println("No stock updates");
            return;
        }
        for(String s : notifications) System.out.println(s);
    }

    //Subscribing and unsubscribing stocks
    public ArrayList<Stock> getSubscribedStocks() { return subscribedStocks; }
    public void subscribeStock(Stock s) { subscribedStocks.add(s); }
    public void unsubscribeStock(Stock s) { subscribedStocks.remove(s); }

    //User function for getting updates on count/ price
    public void countUpdate(Stock s, int prevCount, int currentCount){

        String notification = "Count of stock " + s.getName() + " has been updated from " + prevCount + " to " + currentCount;

        if(loggedIn) System.out.println(notification);
        else this.addToNotificatins(notification);
    }

    public void priceUpdate(Stock s, Float prevPrice, Float currentPrice){

        String notification = "Price of stock " + s.getName() + " has been updated from " + prevPrice + " to " + currentPrice;

        if(loggedIn) System.out.println(notification);
        else this.addToNotificatins(notification);
    }

    //User function for viewing all subscribed stocks
    public void viewSubscribedStocks(){
        if(subscribedStocks.isEmpty()){
            System.out.println("No subscribed stockes");
            return;
        }
        
        for(Stock s : subscribedStocks)
            s.printDetail();
        
        return;
    }

    //Interface function, no action
    public void changeCount(Stock s, int amount) {}
    public void changePrice(Stock s, String opcode, Float amount) {}

}
