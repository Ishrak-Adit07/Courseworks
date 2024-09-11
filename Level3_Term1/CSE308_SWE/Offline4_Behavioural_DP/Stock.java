import java.io.Serializable;
import java.util.ArrayList;

class Stock implements Serializable{

    String name;
    int count;
    Float price;
    ArrayList<User> subscribedUsers;

    Stock(String Name, int Count, Float Price){
        name = Name;
        count = Count;
        price = Price;

        subscribedUsers = new ArrayList<User>();
    }

    //Setters getters
    public void setName(String Name) { name = Name; }
    public String getName() { return name; }

    public void setCount(int Count) { count = Count; }
    public int getCount() { return count; }

    public void setPrice(Float Price) { price = Price; }
    public Float getPrice() { return price; }

    public ArrayList<User> getStockUsers() { return subscribedUsers; }

    //Functions for subscribing/unsubscribing stock 
    public void userSubscribe(User u) { 
        if(subscribedUsers.contains(u)) return;
        
        subscribedUsers.add(u);
        u.subscribeStock(this);
    }
    public void userUnsubscribe(User u) { 
        if(!subscribedUsers.contains(u)) return;

        subscribedUsers.remove(u);
        u.unsubscribeStock(this);
    }

    // Functions for notifying users of count/ price change
    public void notifyCount(int prevCount){
        for(User u : subscribedUsers)
            u.countUpdate(this, prevCount, this.getCount());
    }
    public void notifyPrice(Float prevPrice){
        for(User u : subscribedUsers){
            u.priceUpdate(this, prevPrice, this.getPrice());
        }
    }

    // Functions for changing count/ price, which call notify actions
    public void changeCount(int amount){ setCount(count + amount); }

    public void changePrice(String opcode, Float amount){

        if(opcode.equalsIgnoreCase("I")) setPrice(price + amount);
        if(opcode.equalsIgnoreCase("D")) setPrice(price - amount);

    }

    //Function for shortcut updates /aux/
    public void updateCount(int Count) { count = Count; }
    public void updatePrice(Float Price) { price = Price; }

    //Printing stock details
    public void printDetail(){ System.out.println(name + " " + count + " " + price); }

}