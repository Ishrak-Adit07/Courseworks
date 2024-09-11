import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class StockServerBackend implements Serializable{
    
    ArrayList<Stock> stocks;
    ArrayList<User> users;

    StockServerBackend() {
        stocks = new ArrayList<Stock>();
        users = new ArrayList<User>();
    }

    //Setters getters + all aux functions
    public void setUsersList(ArrayList<User> ul) { users = ul; }
    public ArrayList<User> getUsersList() { return users; }

    public ArrayList<Stock> getStocksList() { return stocks; }
    public void setStocksList(ArrayList<Stock> sl) { stocks = sl; }

    public void addToUserList(User u) { 
        for(User user : users){
            if(user.getName().equalsIgnoreCase(u.getName())) return;
        }
        users.add(u);
     }
    public void removeFromUserList(User u) { users.remove(u); }

    public void addToStockList(Stock s) { stocks.add(s); }
    public void removeFromStockList(Stock s) { stocks.remove(s); }

    //User Action Functions
    public void handleUserAction(User currentUser, String commandLine, NetworkIO networkIO) {

        try {

                String[] userCommandParams = commandTokenizer(commandLine);

                //Stock subscribe command
                if(userCommandParams[0].equalsIgnoreCase("S")){
                    Stock subStock = null;
                    for(Stock s : stocks){
                        if(s.getName().equalsIgnoreCase(userCommandParams[1])){
                            s.userSubscribe(currentUser);
                            subStock = s; break;
                        }
                    }
                    if(subStock==null) System.out.println("No such stock found");
                }

                //Stock unsubscribe command
                else if(userCommandParams[0].equalsIgnoreCase("U")){
                    Stock unsubStock = null;
                    for(Stock s : stocks){
                        if(s.getName().equalsIgnoreCase(userCommandParams[1])){
                            s.userUnsubscribe(currentUser);
                            unsubStock = s; break;
                        }
                    }
                    if(unsubStock==null) System.out.println("No such stock found");
                }

                //View subscribed stocks command
                else if(userCommandParams[0].equalsIgnoreCase("V")){
                    //handles on clientside
                }

                // Exit command
                else if(userCommandParams[0].equalsIgnoreCase("exit")){

                    currentUser.setLoggedIn(false);
                    String command = "exit";
                    networkIO.write(command);
                }

                //View all stocks command
                else if(userCommandParams[0].equalsIgnoreCase("Show")){
                    //handles on clientside
                }

                //Other commands invalid
                else{
                    //handles on clientside
                }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void handleAdminAction(Admin currentAdmin, String commandLine, NetworkIO networkIO, HashMap<String, NetworkIO> serverUserList) {

        try {

                String[] adminCommandParams = commandTokenizer(commandLine);

                //Increase/ Decrease stock price
                if(adminCommandParams[0].equalsIgnoreCase("I") || adminCommandParams[0].equalsIgnoreCase("D")){
                    Stock modStock = null;

                    //Finding the stock with name
                    for(Stock s : stocks){
                        if(s.getName().equalsIgnoreCase(adminCommandParams[1])){

                            // recording previous and current prices
                            Float prevPrice = s.getPrice();

                            currentAdmin.changePrice(s, adminCommandParams[0], Float.parseFloat(adminCommandParams[2]));

                            Float currentPrice = s.getPrice();

                            //Notification to all subscribed users
                            String notification = "notifyPrice " + s.getName() + " " + prevPrice + " " + currentPrice;

                            String subscriberName; NetworkIO userIO; //aux
                            for(User user : s.getStockUsers()){

                                //Storing notifications for not logged in users
                                if(!user.getLoggedIn()) user.addToNotificatins("Price of stock " + s.getName() + " has been updated from " + prevPrice + " to " + currentPrice);

                                subscriberName = user.getName();
                                userIO = serverUserList.get(subscriberName);

                                if(userIO == null) System.out.println("User IO is not found. Cannot send price change notification from ServerBackend");
                                userIO.write(notification);
                            }

                            modStock = s; break;
                        }
                    }

                    if(modStock==null) System.out.println("No such stock found");
                }

                //Change stock count
                else if(adminCommandParams[0].equalsIgnoreCase("C")){

                    Stock modStock = null;

                    //Finding the stock with name
                    for(Stock s : stocks){
                        if(s.getName().equalsIgnoreCase(adminCommandParams[1])){

                            // recording previous and current counts
                            int prevCount = s.getCount();

                            currentAdmin.changeCount(s, Integer.parseInt(adminCommandParams[2]));

                            int currentCount = s.getCount();

                            //Notification to all subscribed users
                            String notification = "notifyCount " + s.getName() + " " + prevCount + " " + currentCount;
                            
                            String subscriberName; NetworkIO userIO; //aux
                            for(User user : s.getStockUsers()){

                                //Storing notifications for not logged in users
                                if(!user.getLoggedIn()) user.addToNotificatins("Count of stock " + s.getName() + " has been updated from " + prevCount + " to " + currentCount);
                                
                                subscriberName = user.getName();
                                userIO = serverUserList.get(subscriberName);

                                if(userIO == null) System.out.println("User IO is not found. Cannot send count change notification from ServerBackend");
                                userIO.write(notification);
                            }
                            
                            modStock = s; break;
                        }
                    }

                    if(modStock==null) System.out.println("No such stock found");
                }

                //View all stocks command
                else if(adminCommandParams[0].equalsIgnoreCase("Show")){
                    //handled on client side
                }

                //Exit command
                else if(adminCommandParams[0].equalsIgnoreCase("exit")){
                    String command = "exit";
                    networkIO.write(command);
                }

                //Other commands invalid
                else{
                    //handled on client side
                }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Commad tokenizer function
    public String[] commandTokenizer(String commandLine){
        String[] commandParams = commandLine.split(" ");  
        return commandParams;
    }

}
