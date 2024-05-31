import java.util.ArrayList;

public class BankingSystem extends Bank{

    //List of accounts, bankEmployees and pending loans
    private ArrayList<Account> bankAccounts;
    private ArrayList<Employee> bankEmployees;
    private ArrayList<LoanClass> pendingLoans;

    //Currently executing account/employee
    private Employee currentEmployee = new Employee();
    private Account currentAccount = new Account();

    //Bank variables
    private int clock;

    //Setters Getters
    ArrayList<Account> getBankAccounts(){ return bankAccounts; }
    ArrayList<Employee> getAllEmployees(){ return bankEmployees; }
    ArrayList<LoanClass> getAllPendingLoans(){ return pendingLoans; }

    void setCurrentEmployee(Employee employee){ currentEmployee = employee; }
    Employee getCurrentEmployee(){ return currentEmployee; }

    void setCurrentAccount(Account account){ currentAccount = account; }
    Account getCurrentAccount(){ return currentAccount; }

    void setClock(int newClock){clock = newClock;}
    int getClock(){ return clock; }

    //Constructor
    BankingSystem(int O, int C, float sav, float stud, float fd){
        super(O, C, sav, stud, fd);

        //Initializing all lists
        bankAccounts = new ArrayList<Account>();
        bankEmployees = new ArrayList<Employee>();
        pendingLoans = new ArrayList<LoanClass>();

        //Adding initial stuff
        this.addManagingDirector("MD");

        for(int i=1; i<=numberOfOfficers; i++)
            this.addOfficer("O"+i);

        for(int i=1; i<=numberOfCashiers; i++)
            this.addCashier("C"+i);

        //Setting initial internal variables
        currentEmployee = null;
        currentAccount = null;

        //Bank creation announcement
        System.out.print("Bank Created, M D, ");
        for(int i=1; i<=numberOfOfficers; i++)
            System.out.print("O"+i+", ");
        for(int i=1; i<=numberOfCashiers; i++)
            System.out.print("C"+i+", ");
        System.out.println("\b\b created");
        
    }

    //Operating functions of banking system for public use \/

    //Employee related functions in bankingsystem \/

    //Adding or removing employees
    void addEmployee(Employee employee){ bankEmployees.add(employee); }
    void removeEmployee(Employee employee){ bankEmployees.remove(employee); }

    //Creating empoloyee of certain designation
    void addManagingDirector(String name){
        Employee employee = new ManagingDirector(name);
        this.addEmployee(employee);
    }
    void addOfficer(String name){
        Employee employee = new Officer(name);
        this.addEmployee(employee);
    }
    void addCashier(String name){
        Employee employee = new Cashier(name);
        this.addEmployee(employee);
    }

    //Employee related functions in bankingsystem /\


    //Accounts related functions in bankingsystem \/

    //Verification for unique username for a new account
    Boolean verifyUniqueUserName(String username){
        for(Account account : bankAccounts)
            if(account.getName().compareToIgnoreCase(username)==0)
                return false;
        
        return true;
    }

    //Adding or Removing account from existing accounts
    void addAccount(Account account) { bankAccounts.add(account); }
    void removeAccount(Account account){ bankAccounts.remove(account); }

    Account createAccount(String name, String type, Float initialBalance){

        //Creating account only when username is not used previosly
        Account newAccount = new Account();
        if(type.compareToIgnoreCase("Savings")==0){
            newAccount = new SavingsAccount(name, type, initialBalance);
            newAccount.setInterestRate(savingsInterestRate);
        }

        else if(type.compareToIgnoreCase("Student")==0){
            newAccount = new StudentAccount(name, type, initialBalance);
            newAccount.setInterestRate(studentInterestRate);
        }
        
        else if(type.compareToIgnoreCase("FixedDeposit")==0 && initialBalance >= 100000){
            newAccount = new FixedDepositAccount(name, type, initialBalance);
            newAccount.setInterestRate(fixedDepositInterestRate);
        } 
        else return null;       
        
        this.addAccount(newAccount);
        return newAccount;
    }

    //Accounts related functions in bankingsystem /\

    //Loan related functions in bankingsystem \/

    //Adding or Removing Loans from pendingList
    void addtoPendingLoan(LoanClass newLoan){ pendingLoans.add(newLoan); }
    void removePendingLoan(LoanClass loan){ pendingLoans.remove(loan); }
    LoanClass getFirstPendingLoan(){ return pendingLoans.remove(0); }

    //Loan related functions in bankingsystem /\


    //Searching for employee/account by name
    Account getAccountByName(String name){
        for(Account account : bankAccounts)
            if(account.getName().compareToIgnoreCase(name)==0)
                return account;
        return null;
    }
    Employee getEmployeeByName(String name){
        for(Employee employee : bankEmployees)
            if(employee.getName().compareToIgnoreCase(name)==0)
                return employee;
        return null;
    }


    //Bank system operations on command or reques \/

    //Loan related operations on command or request \/

    void addNewLoanRequest(String name, float amount){
        LoanClass newLoan = new LoanClass(name, amount);
        addtoPendingLoan(newLoan);
    }

    Boolean reviewLoan(LoanClass pendingLoan){
        Account account = getAccountByName(pendingLoan.getAccountName());
        if(account.isLoanAmountValid(pendingLoan.getLoanAmount())) return true;

        return false;
    }

    Boolean reviewLoan(String name, float requestAmount){
        Account account = getAccountByName(name);
        if(account.isLoanAmountValid(requestAmount)) return true;

        return false;
    }

    void approveLoan(LoanClass pendingLoan){
        Account account = getAccountByName(pendingLoan.getAccountName());

        account.credit(pendingLoan.getLoanAmount());
        account.addToLoan(pendingLoan.getLoanAmount());
    }

    int  approveLoans(String employeeName){
        Employee loanApproverEmployee = getEmployeeByName(employeeName);

        if(!loanApproverEmployee.loanApprovingPermitted()) return -1;

        for(int i=0; i<pendingLoans.size(); i++){
            LoanClass pendingLoan = pendingLoans.remove(0);
            
            if(reviewLoan(pendingLoan)){
                approveLoan(pendingLoan);
                System.out.println("Loan for "+pendingLoan.getAccountName()+" approved");
            }
            else{
                System.out.println("Loan for "+pendingLoan.getAccountName()+" not approved");
            }
        }

        return 1;
    }

    //Loan related operations on command or request /\

    //Employees' query function on account balance
    float Lookup(String accountName){
        Account account = getAccountByName(accountName);
        if(account==null){
            System.out.println("Account does not exist");
            return -2;
        }

        System.out.println(account.getName()+"'s current balance is "+account.getDeposit()+"$, loan "+account.getLoan()+"$");
        return account.getDeposit();
    }

    //Employees' function on changing interest rate
    Boolean changeInterestRate(Employee employee, String type, float newRate){
        if(employee.changeInterestRatePermitted()){

            if(type.compareToIgnoreCase("Savings")==0){
                setSavingsInterestRate(newRate);
            }
            else if(type.compareToIgnoreCase("Student")==0){
                setSavingsInterestRate(newRate);
            }
            else if(type.compareToIgnoreCase("FixedDeposit")==0){
                setFixedDepositInterestRate(newRate);
            }

            for(Account account : bankAccounts){
                if(account.getAccountType().compareToIgnoreCase("Savings")==0){
                    account.setInterestRate(newRate);
                }
                else if(account.getAccountType().compareToIgnoreCase("Student")==0){
                    account.setInterestRate(newRate);
                }
                else if(account.getAccountType().compareToIgnoreCase("FixedDeposit")==0){
                    account.setInterestRate(newRate);
                }
            }

        }

        return false;
    }

    //Employees' query function on internal fund
    float seeInternalFund(Employee employee){
        if(employee.seeInternalFundPermitted())
            return internalFund;
        else return -1;
    }

    //Clock Increment Function
    void IncClock(){
        setClock(clock+1);

        for(Account account : bankAccounts){
            float interest = account.getDeposit()*account.getInterestRate()/100;
            float loanInterest = account.getLoan()*account.getLoanInterestRate()/100;

            if( (account.getDeposit() + interest - loanInterest) <0){
                account.addToLoan(account.getDeposit()+interest-loanInterest);
                account.setDeposit(0);
            }
            else account.setDeposit(account.getDeposit()+interest-loanInterest);
            
            if(account instanceof FixedDepositAccount){
                ((FixedDepositAccount)account).setMaturityPeriod( ((FixedDepositAccount)account).getMaturityPeriod()+1 );
            }
        }
    }

    //Operating functions of banking system for public use /\


    //Function for processing command/request and calling required functions
    void actionController(String[] actionParams){
        
        if(actionParams[0].compareToIgnoreCase("Create")==0){
            if(currentEmployee != null || currentAccount != null){
                System.out.println("Invalid Instruction");
                return;
            }

            if(!this.verifyUniqueUserName(actionParams[1]))
                System.out.println("Username is already in use!");

            else{
                setCurrentAccount(createAccount(actionParams[1], actionParams[2], Float.parseFloat(actionParams[3])));
                if(currentAccount!=null) System.out.println(actionParams[2]+" account for "+actionParams[1]+" Created, Initial balance "+actionParams[3]+" $");
                else System.out.println("Credentials not valid, account not created");
            }
            return;

        }

        if(actionParams[0].compareToIgnoreCase("Deposit")==0){

            if(currentAccount == null){
                System.out.println("Invalid Instruction");
                return;
            }
            else{
                float creditValidity = currentAccount.credit(Float.parseFloat(actionParams[1]));
                if(creditValidity < 0) System.out.println("Deposit amount not valid, action denied");
                else System.out.println(actionParams[1]+" deposited; current balance "+currentAccount.queryForDeposit()+" $");
                return;
            }

        }

        if(actionParams[0].compareToIgnoreCase("Withdraw")==0){
            
            if(currentAccount == null){
                System.out.println("Invalid Instruction");
                return;
            }
            else{
                float withdrawReturn = currentAccount.withdraw(Float.parseFloat(actionParams[1]));
                if(withdrawReturn == -1) System.out.println("Invalid transaction; current balance "+currentAccount.queryForDeposit()+" $");
                else System.out.println(actionParams[1]+" withdrawn; current balance "+currentAccount.queryForDeposit()+"$");
                return;
            }

        }

        if(actionParams[0].compareToIgnoreCase("Query")==0){

            if(currentAccount == null){
                System.out.println("Invalid Instruction");
                return;
            }
            else System.out.print("Current balance " + currentAccount.queryForDeposit() + " $"); 

            if(currentAccount.getLoan() > 0) System.out.println(", loan "+currentAccount.getLoan());
            else System.out.println(" ");
            return;

        }

        if(actionParams[0].compareToIgnoreCase("Request")==0){

            if(currentAccount == null){
                System.out.println("Invalid Instruction");
                return;
            }
            if(reviewLoan(currentAccount.getName(), Float.parseFloat(actionParams[1]))){
                addNewLoanRequest(currentAccount.getName(), Float.parseFloat(actionParams[1]));
                System.out.println("Loan request successful, sent for approval");
            }
            else System.out.println("Loan request unsuccessful");
            return;

        }

        if(actionParams[0].compareToIgnoreCase("Open")==0){

            Account account = getAccountByName(actionParams[1]);
            if(account!=null){
                currentAccount = account;
                System.out.println("Welcome back, "+currentAccount.getName());
            }
            else{
                Employee employee = getEmployeeByName(actionParams[1]);
                if(employee!=null){
                    currentEmployee = employee;
                    System.out.print(currentEmployee.getName()+" active");
                    if(pendingLoans.size()>0) System.out.println(", there are loan approvals pending");
                    else System.out.println(" ");
                }
            }

        }

        if(actionParams[0].compareToIgnoreCase("Close")==0){
            if(currentEmployee != null){
                System.out.println(currentEmployee.close());
                currentEmployee = null;
            }
            else if(currentAccount != null){
                System.out.println(currentAccount.close());
                currentAccount = null;
            }   
        }

        if(actionParams[0].compareToIgnoreCase("Approve")==0){

            if(currentEmployee == null){
                System.out.println("Invalid Instruction");
                return;
            }
            approveLoans(currentEmployee.getName());

        }

        if(actionParams[0].compareToIgnoreCase("Change")==0){
            
            if(currentEmployee == null){
                System.out.println("Invalid Instruction");
                return;
            }

            Boolean changed = changeInterestRate(currentEmployee, actionParams[1], Float.parseFloat(actionParams[2]));
            if(changed) System.out.println("New "+actionParams[1]+" interest rate is "+actionParams[2]);
            else System.out.println("You don't have permission for this operation");
        }

        if(actionParams[0].compareToIgnoreCase("Lookup")==0){

            if(currentEmployee == null){
                System.out.println("Invalid Instruction");
                return;
            }
            Lookup(actionParams[1]);

        }

        if(actionParams[0].compareToIgnoreCase("See")==0){ 

            if(currentEmployee == null){
                System.out.println("Invalid Instruction");
                return;
            }

            if(currentEmployee.seeInternalFundPermitted()) System.out.println("Current internal fund "+getInternalFund());
            else System.out.println("You don't have permission for this operation");

        }


        if(actionParams[0].compareToIgnoreCase("INC")==0){         
            IncClock();
            System.out.println("1 year passed");
        }


    }
}
