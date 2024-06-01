//This is the base decorator class that implements passanger interface
public class Passanger implements iPassanger{

    public void login(){
        System.out.println("Welcome Crewmate!");
    }

    public void repair(){
        System.out.println("Reparing the spaceship.");
    }

    public void work(){
        System.out.println("Doing research");
    }
    
    public void logout(){
        System.out.println("Bye Bye crewmate.");
    }

}