public class FixedDepositAccount extends Account{

    private float minInitialDepositAmount;
    private float minDepositAmount = 50000;
    private float maturityPeriod;

    //Setters Getters
    void setMinInitialDepositAmount(float newMinInitialDepositAmount){ minInitialDepositAmount = newMinInitialDepositAmount; }
    float getMinInitialDepositAmount(){ return minInitialDepositAmount; }

    void setMinDepositAmount(float newMinDepositAmount){ minDepositAmount = newMinDepositAmount; }
    float getMinDepositAmount(){ return minDepositAmount; }

    void setMaturityPeriod(float newMaturityPeriod) { maturityPeriod = newMaturityPeriod; }
    float getMaturityPeriod() { return maturityPeriod; }

    //Constructor
    public FixedDepositAccount(String Name, String Type, float InitialDeposit){
        super(Name, Type, InitialDeposit);

        setInterestRate(15);
        setMinInitialDepositAmount(100000);
        setMinDepositAmount(50000);
        setMaxLoanAmountAllowed(100000);
        setMaturityPeriod(0);
    }

    @Override
    public float credit(float creditSum){
        if(creditSum >= minDepositAmount){
            setDeposit(deposit+creditSum);
            return deposit;
        }
        else return -1;
    }

    @Override
    public float withdraw(float amountToWithdraw){
        if( ((deposit-amountToWithdraw )>=0) && (maturityPeriod>=1)){
            setDeposit(deposit-amountToWithdraw);
            return deposit;
        }
        else if( (deposit-amountToWithdraw ) < 0 ){
            System.out.println("Account balance is not sufficient");
            return -1;
        }
        else if(maturityPeriod < 1){
            System.out.println("Cannot withdraw money for fixed deposit account with maturity period less than "+maturityPeriod);
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
