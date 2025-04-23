package hamza.patient.net.gestionde_bank.services;

import hamza.patient.net.gestionde_bank.entities.BankAccount;
import hamza.patient.net.gestionde_bank.entities.CurrentAccount;
import hamza.patient.net.gestionde_bank.entities.Customer;
import hamza.patient.net.gestionde_bank.entities.SavingAccount;
import hamza.patient.net.gestionde_bank.exceptions.BankAccountNotFoundException;
import hamza.patient.net.gestionde_bank.exceptions.BanlanceNotSufficientException;
import hamza.patient.net.gestionde_bank.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    Customer saveCustomer(Customer customer);
    CurrentAccount saveCurrentBankAccount(double initialBalance,  double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;

    List<Customer> listCustomers();
    BankAccount getBankAccountById(String id) throws BankAccountNotFoundException;
    void debit(String id,double amount,String description) throws BankAccountNotFoundException, BanlanceNotSufficientException;
    void credit(String id,double amount,String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource,String accountIdDestination,double amount) throws BankAccountNotFoundException, BanlanceNotSufficientException;

    List<BankAccount> listBankAccounts();
}
