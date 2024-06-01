import java.util.Scanner;

public class WriterThread implements Runnable{

    public NetworkIO networkIO;
    StockUserBackend userBackend;

    User currentUser;
    Admin currentAdmin;
    
    //Separate for user and admin Writer Thread
    public WriterThread(NetworkIO n, StockUserBackend sb, User user){
        networkIO = n;
        userBackend = sb;
        currentUser = user;
        currentAdmin = null;
    }
    public WriterThread(NetworkIO n, StockUserBackend sb, Admin admin){
        networkIO = n;
        userBackend = sb;
        currentUser = null;
        currentAdmin = admin;
    }

    public void userWriteThreadActions(){

        Scanner sc = new Scanner(System.in);
        Wrapper commandWrapper = new Wrapper();
        
        while(true){
            
            System.out.println("Enter command: ");
            String commandLine = sc.nextLine();

            commandWrapper.setMessage(commandLine);
            
            //Main Write*******************************
            try{
                networkIO.write(commandWrapper.clone());
            }catch(Exception e){
                e.printStackTrace();
            }

            //Userside write actions

            for(User u : userBackend.getUsersList()){
                if(u.getName().equalsIgnoreCase(currentUser.getName())) currentUser = u;
            }

            String[] userCommandParams = userBackend.commandTokenizer(commandLine);

            //Subscribing stock command
            if(userCommandParams[0].equalsIgnoreCase("S")){
                //handles on serverside
            }

            //Unbscribing stock command
            else if(userCommandParams[0].equalsIgnoreCase("U")){
                //handles on serverside
            }

            //View subscribed stocks command
            else if(userCommandParams[0].equalsIgnoreCase("V")){
                currentUser.viewSubscribedStocks();
            }

            //View all stocks command
            else if(userCommandParams[0].equalsIgnoreCase("Show")){
                System.out.println("Stocks Today..");
                for(Stock s : userBackend.getStocksList()){
                    s.printDetail();
                }
            }

            // Exit command
            else if(userCommandParams[0].equalsIgnoreCase("exit")){
                currentUser.setLoggedIn(false);
                break;
            }

            //Other commands invalid
            else{
                System.out.println("Invalid command");
            }

        }
        
    }

    public void adminWriteThreadActions(){

        Scanner sc = new Scanner(System.in);
        Wrapper commandWrapper = new Wrapper();
        
        while(true){
            
            System.out.println("Enter command: ");
            String commandLine = sc.nextLine();

            commandWrapper.setMessage(commandLine);
            
            //Main write***************
            try{
                networkIO.write(commandWrapper.clone());
            }catch(Exception e){
                e.printStackTrace();
            }

            //Admin side write actions
            String[] userCommandParams = userBackend.commandTokenizer(commandLine);

            //Increasing stock price command
            if(userCommandParams[0].equalsIgnoreCase("I")){
                //handles on serverside
            }

            //Decreasing stock price command
            else if(userCommandParams[0].equalsIgnoreCase("D")){
                //handles on serverside
            }

            //Changing stock count command
            else if(userCommandParams[0].equalsIgnoreCase("C")){
                //handles on serverside
            }

            //View all stocks command
            else if(userCommandParams[0].equalsIgnoreCase("Show")){
                System.out.println("Stocks Today..");
                for(Stock s : userBackend.getStocksList()){
                    s.printDetail();
                }
            }

            // Exit command
            else if(userCommandParams[0].equalsIgnoreCase("exit")){
                break;
            }

            //Other commands invalid
            else{
                System.out.println("Invalid command");
            }

        }
    }
    
    @Override
    public void run() {

        if(currentUser != null) userWriteThreadActions();
        else if(currentAdmin != null) adminWriteThreadActions();

    }
    
}