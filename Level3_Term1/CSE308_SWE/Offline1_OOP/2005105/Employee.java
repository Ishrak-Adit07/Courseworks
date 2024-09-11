public class Employee {
    
    protected String name;
    protected String designation;

    //Setters Getters
    void setName(String Name){ name = Name; }
    String getName(){ return name; }

    void setDesignation(String Designation){ designation = Designation; }
    String getDesignation() { return designation; }

    //Constructor
    Employee(String Name, String Designation){
        setName(Name);
        setDesignation(Designation);
    }
    Employee(){
        setName(" ");
        setDesignation(" ");
    }

    Boolean loanApprovingPermitted(){ return false; }
    Boolean seeInternalFundPermitted(){ return false; }
    Boolean changeInterestRatePermitted() {return false; }

    String close(){
        return "Operations for " + name + " closed";
    }

}
