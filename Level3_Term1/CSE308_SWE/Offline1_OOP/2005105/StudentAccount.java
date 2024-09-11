public class StudentAccount extends Account{
 
    private float maxWithdrawAmount;

    //Setters Getters
    void setMaxWithDrawAmount(float newMaxWithDrawAmount) { maxWithdrawAmount = newMaxWithDrawAmount; }
    float getMaxWithDrawAmount() { return maxLoanAmountAllowed; }

    public StudentAccount(String Name, String Type, float InitialDeposit){
        super(Name, Type, InitialDeposit);

        setInterestRate(5);
        setMaxWithDrawAmount(10000);
        setMaxLoanAmountAllowed(1000);
    }

    @Override
    public float withdraw(float amountToWithdraw){
        if( ((deposit-amountToWithdraw )>=0) && (amountToWithdraw <= maxWithdrawAmount) ){
            setDeposit(deposit-amountToWithdraw);
            return deposit;
        }
        else if( (deposit-amountToWithdraw ) < 0 ){
            System.out.println("Account balance is not sufficient");
            return -1;
        }
        else if(amountToWithdraw > maxWithdrawAmount){
            System.out.println("Maximum withdrwal limit for Student account is " + maxWithdrawAmount);
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
