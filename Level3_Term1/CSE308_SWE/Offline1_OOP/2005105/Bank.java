import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Bank {

    protected int numberOfOfficers;
    protected int numberOfCashiers;

    //Bank interest rates
    protected float savingsInterestRate;
    protected float studentInterestRate;
    protected float fixedDepositInterestRate;

    protected float internalFund;

    //Setters Getters
    void setNumberOfOfficers(int n){numberOfOfficers = n;};
    int getNumberOfOfficers(){return numberOfOfficers;}

    void setNumberOfCashiers(int n){numberOfCashiers = n;};
    int getNumberOfCashiers(){return numberOfCashiers;}

    void setSavingsInterestRate(float newSavingsInterestRate){  savingsInterestRate = newSavingsInterestRate; }
    float getSavingsInterestRate(){ return savingsInterestRate; }

    void setStudentInterestRate(float newStudentInterestRate){  studentInterestRate = newStudentInterestRate; }
    float getStudentInterestRate(){ return studentInterestRate; }

    void setFixedDepositInterestRate(float newFixedDepositInterestRate){  fixedDepositInterestRate = newFixedDepositInterestRate; }
    float getFixedDepositInterestRate() { return fixedDepositInterestRate; }

    void setInternalFund(float newInternalFund) {internalFund = newInternalFund;}
    float getInternalFund() { return internalFund; }
    
    //Constructor
    Bank(int O, int C, float sav, float stud, float fd){
        setNumberOfOfficers(O);
        setNumberOfCashiers(C);

        setSavingsInterestRate(sav);
        setStudentInterestRate(stud);
        setFixedDepositInterestRate(fd);
    }

    //Commad tokenizer function
    public String[] commandController(String commandLine){
        String[] commandParams = commandLine.split(" ");  
        return commandParams;
    }
    
    //Main Function------------------------------------------
    public static void main(String[] args) {

        //Initiating bank with banksystem
        int officers = 2, cashiers = 5;
        int savingsRate = 10, studendRate = 5, fixedDepositRate = 15;

        BankingSystem bankingSystem = new BankingSystem(officers, cashiers ,savingsRate, studendRate, fixedDepositRate);

        try{
            //File readers
            File file = new File("input.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String commandLine;

            //Parsing file and executing commands
            while((commandLine=br.readLine())!=null){
                //Tokenizing command/request and passing it to bankingSystem
                String[] actionParams = bankingSystem.commandController(commandLine);
                bankingSystem.actionController(actionParams);
            }

            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}