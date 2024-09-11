import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class StockUserBackend implements Serializable{
    
    ArrayList<Stock> stocks;
    ArrayList<User> users;

    StockUserBackend() {
        stocks = new ArrayList<Stock>();
        users = new ArrayList<User>();
    }

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

    public void addToStockList(Stock s) { 
        for(Stock stock : stocks){
            if(stock.getName().equalsIgnoreCase(s.getName())) return;
        }
        stocks.add(s); 
    }
    public void removeFromStockList(Stock s) { stocks.remove(s); }

    //Update functions
    public void increaseStockPrice(String stockName, Float amount){
        for(Stock s : stocks) {
            if(s.getName().equalsIgnoreCase(stockName)) s.changePrice("I", amount);
        }
    }
    public void decreaseStockPrice(String stockName, Float amount){
        for(Stock s : stocks) {
            if(s.getName().equalsIgnoreCase(stockName)) s.changePrice("D", amount);
        }
    }
    public void increaseStockCount(String stockName, int amount){
        for(Stock s : stocks) {
            if(s.getName().equalsIgnoreCase(stockName)) s.changeCount(amount);
        }
    }

    //Login and Server Connection Functions for Users and Admins
    public void handleUserLogin(){

        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter username: ");
            String ID = sc.nextLine();
        
            User currentUser = null;
            Boolean registeredUser = false;

            for(User u : users){ 
                if(u.getName().equalsIgnoreCase(ID)){
                    registeredUser = true; 
                    currentUser = u;
                    break;
                }
            }

            if(!registeredUser) currentUser = new User(ID, ID);
            
            // Connecting logged in user to Server
            Socket addressSocket = new Socket();
            addressSocket.connect(new InetSocketAddress("www.google.com", 80));

            NetworkIO networkIO = new NetworkIO(addressSocket.getLocalAddress().getHostAddress(), 12345);
            if(networkIO != null) System.out.println(currentUser.getName()  + " requested to connect");
            
            //Send client connection request
            Object userObject = (Object)currentUser;
            networkIO.write(userObject);

            //Confirmation of connection to server
            Object welComeMessage = networkIO.read();
            if(welComeMessage != null) System.out.println((String)welComeMessage);

            //Connecting to user backend
            Object backendObject = (Object)this;
            networkIO.write(backendObject);
            
            //Live Stocks and Users update at login
            backendObject = networkIO.read();
            StockUserBackend sb = (StockUserBackend)backendObject;
            this.setStocksList(sb.getStocksList());
            this.setUsersList(sb.getUsersList());
            for(User user : users){
                if(user.getName().equalsIgnoreCase(currentUser.getName())) currentUser = user;
            }

            currentUser.setLoggedIn(true);
            currentUser.printAllNotifications();

            System.out.println("Stocks Today..");
            for(Stock s : stocks){
                s.printDetail();
            }

            Thread userReadThread = new Thread(new ReaderThread(networkIO, this, currentUser));
            Thread userWriteThred = new Thread(new WriterThread(networkIO, this, currentUser));

            userReadThread.start(); userWriteThred.start();
            userReadThread.join(); userWriteThred.join();

            //Close socket
            addressSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void handleAdminLogin(){
        try {
            
            //Authentication
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter admin password: ");
            String password = sc.nextLine();

            if(!password.equals("admin")) return;

            System.out.println("Admin log in successful");

            Admin currentAdmin = new Admin("admin", "admin");

            // Connecting logged in admin to Server
            Socket addressSocket = new Socket();
            addressSocket.connect(new InetSocketAddress("www.google.com", 80));

            NetworkIO networkIO = new NetworkIO(addressSocket.getLocalAddress().getHostAddress(), 12345);
            if(networkIO != null) System.out.println("Admin " + currentAdmin.getName()  + " requested to connect");
            
            //Send admin connection request to server
            Object userObject = (Object)currentAdmin;
            networkIO.write(userObject);

            //Confirmation of connection
            Object welComeMessage = networkIO.read();
            if(welComeMessage != null) System.out.println((String)welComeMessage);

            //Connecting to userbackend
            Object backendObject = (Object)this;
            networkIO.write(backendObject);
            
            //Live Stocks and Users update at login
            backendObject = networkIO.read();
            StockUserBackend sb = (StockUserBackend)backendObject;
            this.setStocksList(sb.getStocksList());
            this.setUsersList(sb.getUsersList());

            System.out.println("Stocks Today..");
            for(Stock s : stocks){
                s.printDetail();
            }

            Thread userReadThread = new Thread(new ReaderThread(networkIO, this, currentAdmin));
            Thread userWriteThred = new Thread(new WriterThread(networkIO, this, currentAdmin));

            userReadThread.start(); userWriteThred.start();
            userReadThread.join(); userWriteThred.join();

            //Close socket
            addressSocket.close();

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
