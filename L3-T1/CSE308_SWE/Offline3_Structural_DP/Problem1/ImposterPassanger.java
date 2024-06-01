//This is the concrete decorator class for imposter Passangers
public class ImposterPassanger extends Passanger{

    Passanger passanger;

    ImposterPassanger(Passanger newPassanger){
        passanger = newPassanger;
    }
    @Override
    public void login(){
        passanger.login();
        System.out.println("(We won\'t tell anyone; you are an imposter.)");
    }
    @Override
    public void repair(){
        passanger.repair();
        System.out.println("(Damaging the spaceship.)");
    }
    @Override
    public void work(){
        passanger.work();
        System.out.println("(Trying to kill a crewmate.");
        System.out.println("Successfully killed a crewmate.)");
    }
    @Override
    public void logout(){
        passanger.logout();
        System.out.println("(See you again Comrade Imposter.)");
    }

}

