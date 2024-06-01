//This is the concrete decorator class for crewmate Passangers
public class CrewPassanger extends Passanger{

    Passanger passanger;

    CrewPassanger(Passanger newPassanger){
        passanger = newPassanger;
    }

    @Override
    public void login(){
        passanger.login();
    }
    @Override
    public void repair(){
        passanger.repair();
    }
    @Override
    public void work(){
        passanger.work();
    }
    @Override
    public void logout(){
        passanger.logout();
    }

}
