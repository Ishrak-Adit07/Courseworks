import java.util.Scanner;

public class cmd {

    //Commad tokenizer function
    public String[] commandTokenizer(String commandLine){
        String[] commandParams = commandLine.split(" ");  
        return commandParams;
    }

    //CommandController Function
    public IComponent commandController(IComponent currentDirectory, String commandLine){

        String[] commandParams = this.commandTokenizer(commandLine);

        if(commandLine.startsWith("mkdrive")){
            IComponent component = new Drive(commandParams[1]);
            currentDirectory.addToComponentList(component);
        }

        else if(commandLine.startsWith("mkdir")){
            IComponent component = new Folder(commandParams[1]);
            currentDirectory.addToComponentList(component);
        }

        else if(commandLine.startsWith("touch")){
            IComponent component = new File(commandParams[1], Float.parseFloat(commandParams[2]));
            currentDirectory.addToComponentList(component);
        }

        else if(commandLine.startsWith("cd")){
            IComponent newDirectory = currentDirectory.changeDirectory(commandParams[1]);
            if(newDirectory != null){ currentDirectory = newDirectory; }
        }

        else if(commandLine.startsWith("ls")){
            currentDirectory.showDetailsForComponent(commandParams[1]);
        }

        else if(commandLine.equalsIgnoreCase("list")){
            currentDirectory.showListOfComponents();
        }

        else if(commandLine.startsWith("delete")){
            if(commandParams[1].equalsIgnoreCase("-r")) currentDirectory.recursiveDelete(commandParams[2]);
            else currentDirectory.delete(commandParams[1]);
        }

        else{
            System.out.println("Invalid Command");
        }

        return currentDirectory;
    }
    
    public static void main(String[] args) {

        cmd prompt = new cmd();    //This is to use the command tokenizer and controller
        IComponent rootDirectory = new Root();
        IComponent currentDirectory = rootDirectory;    //Inititalizing current directory as root directory
        
        try{
            Scanner sc = new Scanner(System.in);
            String commandLine;

            //Parsing input and executing commands
            while((commandLine=sc.nextLine())!=null){

                //Easier to do these two operation here
                if(commandLine.equalsIgnoreCase("quit")) break; 
                
                else if(commandLine.equalsIgnoreCase("cd ~")) currentDirectory = rootDirectory;

                //Rest are hanlded by command controller
                else currentDirectory = prompt.commandController(currentDirectory, commandLine);

            }

            sc.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }//main
}

//javac cmd.java
//java cmd