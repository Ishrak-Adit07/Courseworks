public class Officer extends Employee{

    //Constructor
    Officer(String name){
        super(name, "Officer");
    }

    @Override
    Boolean loanApprovingPermitted(){ return true; }
    
}
