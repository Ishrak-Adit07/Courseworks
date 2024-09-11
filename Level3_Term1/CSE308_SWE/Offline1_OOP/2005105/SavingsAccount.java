public class SavingsAccount extends Account{

    private float minAccountBalance;

    //Setters Getters
    void setMinAccountBalance(float newMinAccountBalance) { minAccountBalance = newMinAccountBalance; }
    float getMinAccountBalance() { return minAccountBalance; }

    //Constructor
    public SavingsAccount(String Name, String Type, float InitialDeposit){
        super(Name, Type, InitialDeposit);

        setInterestRate(10);
        setMinAccountBalance(1000);
        setMaxLoanAmountAllowed(10000);
    }

    @Override
    public float withdraw(float amountToWithdraw){
        if( (deposit-amountToWithdraw ) >= minAccountBalance){
            setDeposit(deposit-amountToWithdraw);
            return deposit;
        }
        else if( (deposit-amountToWithdraw ) < 0 ){
            System.out.println("Account balance is not sufficient");
            return -1;
        }
        else return -1;
    }

    @Override
    public Boolean isLoanAmountValid(float requestAmount){
        if(requestAmount <= maxLoanAmountAllowed) return true;
        return false;
    }

}