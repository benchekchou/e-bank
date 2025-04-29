package hamza.patient.net.gestionde_bank.services;

import hamza.patient.net.gestionde_bank.dtos.*;
import hamza.patient.net.gestionde_bank.entities.*;
import hamza.patient.net.gestionde_bank.enums.OperationType;
import hamza.patient.net.gestionde_bank.exceptions.BankAccountNotFoundException;
import hamza.patient.net.gestionde_bank.exceptions.BanlanceNotSufficientException;
import hamza.patient.net.gestionde_bank.exceptions.CustomerNotFoundException;
import hamza.patient.net.gestionde_bank.mappers.BankAccountMapperImpl;
import hamza.patient.net.gestionde_bank.repositories.AccountOperationRepository;
import hamza.patient.net.gestionde_bank.repositories.BankAccountRepository;
import hamza.patient.net.gestionde_bank.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor @NoArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {



    @Autowired
    private CustomerRepository customerRepository;
    @Autowired

    private BankAccountRepository bankAccountRepository;
    @Autowired

    private AccountOperationRepository accountOperationRepository;
    @Autowired

    private BankAccountMapperImpl dtoMapperImpl;


    private  Logger LOGGER = Logger.getLogger(BankAccountServiceImpl.class.getName());

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        LOGGER.info("Saving new Customer");
        Customer customer = dtoMapperImpl.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return dtoMapperImpl.fromCustomer(savedCustomer);
    }

    @Override
    public CurrentAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
        LOGGER.info("Saving new Bank Account");
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        if(customer==null){
            throw new CustomerNotFoundException("Customer not found");
        }
        CurrentAccount currentAccount=new CurrentAccount();

        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setBalance(initialBalance);
        currentAccount.setCustomer(customer);
        currentAccount.setCreatedAt(new Date());
        currentAccount.setOverDraft(overDraft);
        currentAccount.setCustomer(customer);

        CurrentAccount save = bankAccountRepository.save(currentAccount);

        return dtoMapperImpl.fromCurrentAccount(save);




    }

    @Override
    public SavingAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
        LOGGER.info("Saving new Bank Account");
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        if(customer==null){
            throw new CustomerNotFoundException("Customer not found");
        }
        SavingAccount savingAccount=new SavingAccount();

        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setBalance(initialBalance);
        savingAccount.setCustomer(customer);
        savingAccount.setCreatedAt(new Date());
        savingAccount.setIntersetRate(interestRate);
        savingAccount.setCustomer(customer);
        SavingAccount save = bankAccountRepository.save(savingAccount);
        return dtoMapperImpl.fromSavingAccount(save);
    }



    @Override
    public List<CustomerDTO> listCustomers() {

        List<Customer> all = customerRepository.findAll();

        return all.stream().map(customer -> dtoMapperImpl.fromCustomer(customer)).collect(Collectors.toList());
    }

    @Override
    public BankAccountDTO getBankAccountById(String id) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(() -> new BankAccountNotFoundException("Bank Account not found"));
        if (bankAccount instanceof CurrentAccount) {
            CurrentAccount currentAccount = (CurrentAccount) bankAccount;
            return dtoMapperImpl.fromCurrentAccount(currentAccount);
        }else {
            SavingAccount savingAccount = (SavingAccount) bankAccount;
            return dtoMapperImpl.fromSavingAccount(savingAccount);
        }

    }

    @Override
    public void debit(String id, double amount, String description) throws BankAccountNotFoundException, BanlanceNotSufficientException {
       BankAccount bankAccount=bankAccountRepository.findById(id).orElseThrow(()->new BankAccountNotFoundException("Bank Account not found"));
       if(bankAccount.getBalance() < amount)
           throw new BanlanceNotSufficientException("Balance not sufficient");
        Operation operation = new Operation();
        operation.setType(OperationType.DEBIT);
        operation.setAmount(amount);
        operation.setDescription(description);
        operation.setDate(new Date());
        operation.setBankAccount(bankAccount);
        accountOperationRepository.save(operation);
        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepository.save(bankAccount);



    }

    @Override
    public void credit(String id, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount=bankAccountRepository.findById(id).orElseThrow(()->new BankAccountNotFoundException("Bank Account not found"));
        Operation operation = new Operation();
        operation.setType(OperationType.CREDIT);
        operation.setAmount(amount);
        operation.setDescription(description);
        operation.setDate(new Date());
        operation.setBankAccount(bankAccount);
        accountOperationRepository.save(operation);
        bankAccount.setBalance(bankAccount.getBalance()+amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BanlanceNotSufficientException {
             debit(accountIdSource,amount,"transfer to "+accountIdDestination);
             credit(accountIdDestination,amount,"transfer from "+accountIdSource);
    }
    @Override
    public List<BankAccountDTO> listBankAccounts(){
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        List<BankAccountDTO> bankAccountDTO = bankAccounts.stream().map(Account -> {
            if (Account instanceof SavingAccount) {
                SavingAccount savingAccount = (SavingAccount) Account;
                return dtoMapperImpl.fromSavingAccount(savingAccount);
            } else {
                CurrentAccount currentAccount = (CurrentAccount) Account;
                return dtoMapperImpl.fromCurrentAccount(currentAccount);
            }

        }).collect(Collectors.toList());


        return bankAccountDTO;
    }

    @Override
    public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException {
         Customer customer=customerRepository.findById(customerId).orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));
        return dtoMapperImpl.fromCustomer(customer);
    }
    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        LOGGER.info("Saving new Customer");
        Customer customer = dtoMapperImpl.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return dtoMapperImpl.fromCustomer(savedCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId){
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<OperationDTO> accountHistory(String accountId){
        List<Operation> operations = accountOperationRepository.findByBankAccount_Id(accountId);
        return operations.stream().map(operation ->dtoMapperImpl.fromOperation(operation)).collect(Collectors.toList());
    }

    @Override
    public AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException {
        BankAccount bankAccount=bankAccountRepository.findById(accountId).orElse(null);
        if(bankAccount==null) throw new BankAccountNotFoundException("Account not Found");
        Page<Operation> AccountOperations = accountOperationRepository.findByBankAccount_Id(accountId, PageRequest.of(page,size));
        AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO();
        List<OperationDTO> operationDTOS = AccountOperations.getContent().stream().map(operation ->dtoMapperImpl.fromOperation(operation)).toList();
        accountHistoryDTO.setAccountOperationDTOS(operationDTOS);
        accountHistoryDTO.setAccountId(bankAccount.getId());
        accountHistoryDTO.setBalance(bankAccount.getBalance());
        accountHistoryDTO.setPageSize(size);
        accountHistoryDTO.setCurrentPage(page);
        accountHistoryDTO.setTotalPages(AccountOperations.getTotalPages());


        return accountHistoryDTO ;
    }

    @Override
    public List<CustomerDTO> searchCustomers(String kw) {
        List<Customer> customers = customerRepository.sreachCustomer(kw);
        List<CustomerDTO> customerDTOS = customers.stream().map(customer -> dtoMapperImpl.fromCustomer(customer)).toList();
        return customerDTOS ;
    }


}
