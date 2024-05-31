public class Account {
    
    protected String name;
    protected String accountType;
    protected float deposit;
    protected float loan;

    protected float maxLoanAmountAllowed;
    protected float loanInterestRate;
    protected float interestRate;

    //Setters Getters
    void setName(String Name){ name = Name; }
    String getName() { return name; }

    void setAccountType(String type){ accountType = type; }
    String getAccountType(){ return accountType; }

    float setDeposit(float Deposit){
        if(Deposit < 0) return -1;
        else{
            deposit = Deposit;
            return deposit;
        }
     }
    float getDeposit(){ return deposit; }

    void setLoan(float newLoan){loan = newLoan;}
    float getLoan(){return loan;}

    void setMaxLoanAmountAllowed(float newAmount){ maxLoanAmountAllowed = newAmount; }
    float getMaxLoanAmountAllowed(){ return maxLoanAmountAllowed; }

    void setLoanInterestRate(float newRate){ loanInterestRate = newRate; }
    float getLoanInterestRate() { return loanInterestRate; }

    void setInterestRate(float newInterestRate){  interestRate = newInterestRate; }
    float getInterestRate() { return interestRate; }


    //Constructors
    public Account(){
        setName(" ");
        setAccountType(" ");
        setDeposit(0);
        setLoan(0);
        setLoanInterestRate(10);
    }

    public Account(String Name, String Type, float InitialDeposit){
        setName(Name);
        setAccountType(Type);
        setDeposit(InitialDeposit);
        setLoan(0);
        setLoanInterestRate(10);
    }

    //Account functions for public use \/

    //Credit Money to account
    public float credit(float creditSum){
        if(creditSum>0){
            setDeposit(deposit+creditSum);
            return deposit;
        }
        return -1;
    }

    //Withdraw money from account
    public float withdraw(float amountToWithdraw){
        if( (deposit-amountToWithdraw ) >= 0){
            setDeposit(deposit-amountToWithdraw);
            return deposit;
        }
        else return -1;
    }

    public void addToLoan(float amount){
        setLoan(loan+amount);
    }

    //Make a query for current account balance
    public float queryForDeposit(){
        return deposit;
    }

    public Boolean isLoanAmountValid(float requestAmount){ return false; }

    String close(){ return "Transaction closed for " + name; }

    //Account functions for public use /\
}

