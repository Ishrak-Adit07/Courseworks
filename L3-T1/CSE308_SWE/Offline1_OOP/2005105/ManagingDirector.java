public class ManagingDirector extends Employee{
    
    //Constructor
    ManagingDirector(String name){
        super(name, "ManagingDirector");
    }

    @Override
    Boolean loanApprovingPermitted(){ return true; }

    @Override
    Boolean seeInternalFundPermitted(){ return true; }
    
    @Override
    Boolean changeInterestRatePermitted() {return true; }

}
