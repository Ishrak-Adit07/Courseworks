public class LoanClass {
    private String accountName;
    private float loanAmount;
    private Boolean approved;

    //Setters Getters
    void setAccountName(String name) { accountName = name; }
    String getAccountName() { return accountName; }

    void setLoanAmount(float amount) { loanAmount = amount; }
    float getLoanAmount() { return loanAmount; }

    void setApproved(Boolean state) { approved = state; }
    Boolean getApproved() { return approved; }

    //Construtor
    LoanClass(String name, float amount){
        accountName = name;
        loanAmount = amount;
        approved = false;
    }
}
