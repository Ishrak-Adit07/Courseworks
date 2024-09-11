//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
import java.util.Scanner;

public class DeviceApplication {

    //Commad tokenizer function
    public String[] commandTokenizer(String commandLine){
        String[] commandParams = commandLine.split(" ");  
        return commandParams;
    }

    //CommandController Function
    public Passanger commandController(Passanger newPassanger, String commandLine){

        if(commandLine.startsWith("login") && (newPassanger==null)){

            newPassanger = new Passanger();
            String[] commandParams = this.commandTokenizer(commandLine);
            if(commandParams[1].startsWith("crew")){
                newPassanger = new CrewPassanger(newPassanger);
            }
            else if(commandParams[1].startsWith("imp")){
                newPassanger = new ImposterPassanger(newPassanger);
            }
            //Show login message
            newPassanger.login();

        }

        else if(commandLine.startsWith("login") && (newPassanger!=null)){
            System.out.println("Multiple login not allowed");
        }


        else if(commandLine.equalsIgnoreCase("repair")){
            newPassanger.repair();
        }

        else if(commandLine.equalsIgnoreCase("work")){
            newPassanger.work();
        }


        else if(commandLine.equalsIgnoreCase("logout")){
            newPassanger.logout();
            newPassanger = null; //Making wrappee object empty after logout
        }

        else{
            System.out.println("Invalid Command");
        }

        return newPassanger;
    }

    public static void main(String[] args) {

        DeviceApplication DA = new DeviceApplication(); //This is to use the command tokenizer and controller
        Passanger newPassanger = null; //This passanger calls all methods for any logged in passanger
        
        try{
            /*
            //File readers
            File file = new File("input.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            //br.readLine()
            */
            Scanner sc = new Scanner(System.in);
            String commandLine;

            //Parsing file and executing commands
            while((commandLine=sc.nextLine())!=null){

                if(commandLine.equalsIgnoreCase("quit")) break;
                
                else newPassanger = DA.commandController(newPassanger, commandLine);

            }
            
            sc.close();
            //br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }//main
}

//javac DeviceApplication.java
//java DeviceApplication
