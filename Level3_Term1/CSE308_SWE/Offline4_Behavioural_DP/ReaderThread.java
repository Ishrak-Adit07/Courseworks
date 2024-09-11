public class ReaderThread implements Runnable{

    public NetworkIO networkIO;
    StockUserBackend userBackend;

    User currentUser;
    Admin currentAdmin;

    //Separate for user and admin Reader Thread
    public ReaderThread(NetworkIO n, StockUserBackend sb, User user){
        networkIO = n;
        userBackend = sb;

        currentUser = user;
        currentAdmin = null;
    }
    public ReaderThread(NetworkIO n, StockUserBackend sb, Admin admin){
        networkIO = n;
        userBackend = sb;

        currentUser = null;
        currentAdmin = admin;
    }

    public void StartThread(){
        new Thread(this).start();
    }

    public void userReadThreadActions(){

        StockUserBackend sbe = null;
        String messageFromServer = null;

        while(true){

            sbe = null;
            messageFromServer = null;

            Object object = networkIO.read();
            if(object instanceof StockUserBackend) sbe = (StockUserBackend) object;  
            else if(object instanceof String) messageFromServer = (String) object;  
            
            try {

                    if(sbe != null){
                        userBackend.setStocksList(sbe.getStocksList());
                        userBackend.setUsersList(sbe.getUsersList());

                        for(User u : userBackend.getUsersList()){
                            if(u.getName().equalsIgnoreCase(currentUser.getName())) currentUser = u;
                        }
                    }

                    if(messageFromServer != null){

                        //System.out.println(messageFromServer);
                        String[] messageParams = userBackend.commandTokenizer(messageFromServer);

                        // Exit command
                        if(messageFromServer.equalsIgnoreCase("exit")){
                            currentUser.setLoggedIn(false);
                            break;
                        }

                        //Change Price Command
                        else if(messageParams[0].equalsIgnoreCase("notifyPrice")){
                            
                            Stock modStock = null;

                            //Updating price in userBackend StockList in real time
                            for(Stock s : userBackend.getStocksList()){
                                if(s.getName().equalsIgnoreCase(messageParams[1])) modStock = s;
                                s.updatePrice(Float.parseFloat(messageParams[3]));
                            }

                            if(modStock != null){

                                //Notification in user
                                currentUser.priceUpdate(modStock, Float.parseFloat(messageParams[2]), Float.parseFloat(messageParams[3]));
                                
                                //Updating stock price in user info in real time
                                for(Stock s : currentUser.getSubscribedStocks()){
                                    if(s.getName().equalsIgnoreCase(messageParams[1])){
                                        s.updatePrice(Float.parseFloat(messageParams[3]));
                                    }
                                }

                            }

                        }//Notify price

                        else if(messageParams[0].equalsIgnoreCase("notifyCount")){
                            
                            Stock modStock = null;

                            //Updating price in userBackend StockList in real time
                            for(Stock s : userBackend.getStocksList()){
                                if(s.getName().equalsIgnoreCase(messageParams[1])) modStock = s;
                                s.updateCount(Integer.parseInt(messageParams[3]));
                            }

                            if(modStock != null){

                                //Notification in user
                                currentUser.countUpdate(modStock, Integer.parseInt(messageParams[2]), Integer.parseInt(messageParams[3]));
                                
                                //Updating stock price in user info in real time
                                for(Stock s : currentUser.getSubscribedStocks()){
                                    if(s.getName().equalsIgnoreCase(messageParams[1])){
                                        s.updateCount(Integer.parseInt(messageParams[3]));
                                    }
                                }

                            }

                        }//Notify count

                    }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void adminReadThreadActions(){

        StockUserBackend sbe = null;
        String messageFromServer = null;

        while(true){

            sbe = null;
            messageFromServer = null;

            Object object = networkIO.read();
            if(object instanceof StockUserBackend) sbe = (StockUserBackend) object;  
            else if(object instanceof String) messageFromServer = (String) object;  
            
            try {

                    if(sbe != null){
                        userBackend.setStocksList(sbe.getStocksList());
                        userBackend.setUsersList(sbe.getUsersList());
                    }

                    if(messageFromServer != null){

                        // Exit command
                        if(messageFromServer.equalsIgnoreCase("exit")){
                            break;
                        }

                    }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    
    
    @Override
    public void run() {
        
        if(currentUser != null) userReadThreadActions();
        else if(currentAdmin != null) adminReadThreadActions();

    }
    
}