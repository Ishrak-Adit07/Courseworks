import java.io.Serializable;
import java.util.ArrayList;

public class Admin implements BaseUser, Serializable{
    
    String name;
    String ID;
    String password;

    ArrayList<Stock> subscribedStocks;

    Admin() {}
    Admin(String Name, String id){
        name = Name; ID = id;
        password = "admin";

        subscribedStocks = new ArrayList<Stock>();
    }

    //Setters getters
    public void setName(String Name) { name = Name; }
    public String getName() { return name; }

    public void setID(String id) { ID = id; }
    public String getID() { return ID; }

    public void setPassword(String p) { password = p; }
    public String getPassword() { return password; }

    //Interface functions
    public ArrayList<Stock> getSubscribedStocks() { return subscribedStocks; }
    public void subscribeStock(Stock s) { subscribedStocks.add(s); }
    public void unsubscribeStock(Stock s) { subscribedStocks.remove(s); }

    //User function for getting updates on count/ price
    public void countUpdate(Stock s, int prevCount, int currentCount){
        System.out.println("Count of stock " + s.getName() + " has been updated from " + prevCount + " to " + s.getCount());
    }

    public void priceUpdate(Stock s, Float prevPrice, Float currentPrice){
        System.out.println("Price of stock " + s.getName() + " has been updated from " + prevPrice + " to " + s.getPrice());
    }

    //User function for viewing subscribed stocks
    public void viewSubscribedStocks(){
        if(subscribedStocks.isEmpty()){
            System.out.println("No subscribed stockes");
            return;
        }

        for(Stock s : subscribedStocks)
            System.out.println(s.getName() + " " + s.getCount() + " " + s.getPrice());
        
        return;
    }


    //Admin Function for manipulating stocks
    public void changeCount(Stock s, int amount){
        s.changeCount(amount);
    }
    public void changePrice(Stock s, String opcode, Float amount){
        s.changePrice(opcode, amount);
    }
    
}
