import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.HashMap;

public class StockServer{

    public static void main(String[] args) {
        
        try{

            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started");

            StockServerBackend serverBackend = new StockServerBackend();
            HashMap<String, NetworkIO> serverUserList = new HashMap<String, NetworkIO>();
        
            // init_stocks.txt
            try{
                // File readers
                File file = new File("init_stocks.txt");
                FileReader fileReader = new FileReader(file);
                BufferedReader br = new BufferedReader(fileReader);
            
                String commandLine;
                while((commandLine = br.readLine()) != null){

                    String[] stockParams = serverBackend.commandTokenizer(commandLine);
                    Stock newStock = new Stock(stockParams[0], Integer.parseInt(stockParams[1]), Float.parseFloat(stockParams[2]));

                    serverBackend.addToStockList(newStock);

                }


                br.close();
            }catch(Exception e){
                e.printStackTrace();
            }// while for file read

            // stock details in cmd
            System.out.println("Stocks Today..");

            for(Stock s : serverBackend.getStocksList()){
                s.printDetail();
            }
            
            // waiting for and accepting client, creating a new thread
            while(true){

                Socket socket = serverSocket.accept();
                System.out.println("Client accepted");

                //Serverside networkIO for user/admin
                NetworkIO networkIO = new NetworkIO(socket);

                //new server thread start
                new ServerThread(socket, networkIO, serverUserList, serverBackend);
            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }

}

class ServerThread implements Runnable{

    Socket clientSocket;
    Thread thread;

    User client;
    Admin admin;

    StockServerBackend serverBackend; // handles serverside info on stocks and users
    StockUserBackend userBackend; // helps to update clientside info on stocks and users

    NetworkIO networkIO; // serverside IO for client
    HashMap<String, NetworkIO> serverUserList;

    ServerThread(Socket clientSock, NetworkIO nio, HashMap<String, NetworkIO> uList, StockServerBackend sb){
        clientSocket = clientSock;
        serverBackend = sb;
        userBackend = null;

        client = null; admin = null;

        networkIO = nio;
        serverUserList = uList;

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run(){

        try{

            //Connecting to new client
            Object newClient = networkIO.read();
            if( newClient instanceof User) client = (User)newClient;
            else if (newClient instanceof Admin) admin = (Admin)newClient;

            //Accepting connection to client
            String newClientName = " ";
            if(admin == null) newClientName = client.getName();
            else if(client == null) newClientName = admin.getName();
            System.out.println(newClientName+" accepted");

            //Confirmation of connection to client
            String welcomeMessage = "Welcome "+newClientName;
            networkIO.write(welcomeMessage);

            //Connecting to stockUserBackend
            Object userBackendObject = networkIO.read();
            userBackend = (StockUserBackend)userBackendObject;

            //Updating client side stocks and users info
            userBackend.setStocksList(serverBackend.getStocksList());
            userBackend.setUsersList(serverBackend.getUsersList());

            // Returning live data
            userBackendObject = (Object)userBackend;
            networkIO.write(userBackendObject);

            // Creating new user/ Fetching registerd user
            // Storing corresponding NetworkIO Object
            Boolean registeredUser = false;
            if(admin == null){

                for(User u : serverBackend.getUsersList()){
                    if(u.getName().equalsIgnoreCase(client.getName())){

                        System.out.println("Registered user. Log in successful");
                        client = u;

                        //Updating networkIO object for client
                        serverUserList.put(newClientName, networkIO);

                        client.setLoggedIn(true);
                        serverBackend.addToUserList(client);
                        registeredUser = true; break;
                    }
                }

                if(!registeredUser){

                    System.out.println("New user. Log in successful");

                    //Adding networkIO object for client
                    serverUserList.put(newClientName, networkIO);

                    client.setLoggedIn(true);
                    serverBackend.addToUserList(client);
                    registeredUser = true;
                }
            }            

            //Live listener and responder loop
            Boolean clientConnected = true;
            Wrapper wrapper = new Wrapper();
            while(clientConnected){

                //Receiving client request
                Object clientMessage = networkIO.read();
                if(clientMessage != null) wrapper = (Wrapper)clientMessage;
                else clientConnected = false;

                //Handling client request
                String commandLine = wrapper.getMessage();
                System.out.println(commandLine);
                if(admin == null) serverBackend.handleUserAction(client, commandLine, networkIO);
                else if(client == null) serverBackend.handleAdminAction(admin, commandLine, networkIO, serverUserList);

                //Updating client user in userList
                if(admin == null){
                    for(User u : serverBackend.getUsersList()){
                        if(u.getName().equalsIgnoreCase(client.getName())){
                            u = client; break;
                        }
                    }
                }

                //Sending updated stock and user info to clientside
                userBackend.setStocksList(serverBackend.getStocksList());
                userBackend.setUsersList(serverBackend.getUsersList());

                //for(Stock s : userBackend.getStocksList()) s.printDetail();
                userBackendObject = (Object)userBackend;
                if(!commandLine.equalsIgnoreCase("exit")) networkIO.write(userBackendObject);

            }// client request handling while loop

        }catch(Exception e){
            //e.printStackTrace();
        }

        try{
            clientSocket.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

}

//javac StockServer.java
//java StockServer