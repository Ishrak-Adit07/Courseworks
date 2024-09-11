import java.util.Scanner;

public class StockUI {
    
    public static void main(String[] args) {

        StockUserBackend platform = new StockUserBackend();

        try{
            Scanner sc = new Scanner(System.in);
            String commandLine;

            // handling log in requests and executing commands
            System.out.println("Login as - (type \'user\' or \'admin\'), type exit to exit");
            while(sc.hasNextLine()){
                commandLine = sc.nextLine();

                //Possible commands in log in window
                if(commandLine.equalsIgnoreCase("exit")) break;

                else if(commandLine.equalsIgnoreCase("user")) platform.handleUserLogin(); 
                
                else if(commandLine.equalsIgnoreCase("admin")) platform.handleAdminLogin();

                else System.out.println("Invalid Log in attempted. Not successful.");

                System.out.println("Login as - (type \'user\' or \'admin\'), type exit to exit");
            }

            sc.close();
        }catch(Exception e){
            
            e.printStackTrace();

        }// while for log in attempt

    }//main

}


//javac StockServerBackend.java
//javac StockServer.java
//java StockServer

//javac StockUserBackend.java
//javac NetworkIO.java
//javac ReaderThread.java
//javac WriterThread.java
//javac StockUI.java
//java StockUI


