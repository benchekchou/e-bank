package hamza.patient.net.gestionde_bank;

import hamza.patient.net.gestionde_bank.dtos.BankAccountDTO;
import hamza.patient.net.gestionde_bank.dtos.CurrentAccountDTO;
import hamza.patient.net.gestionde_bank.dtos.CustomerDTO;
import hamza.patient.net.gestionde_bank.dtos.SavingAccountDTO;
import hamza.patient.net.gestionde_bank.entities.BankAccount;
import hamza.patient.net.gestionde_bank.entities.Customer;
import hamza.patient.net.gestionde_bank.entities.SavingAccount;
import hamza.patient.net.gestionde_bank.exceptions.BankAccountNotFoundException;
import hamza.patient.net.gestionde_bank.exceptions.BanlanceNotSufficientException;
import hamza.patient.net.gestionde_bank.exceptions.CustomerNotFoundException;
import hamza.patient.net.gestionde_bank.mappers.BankAccountMapperImpl;
import hamza.patient.net.gestionde_bank.services.BankAccountService;
import hamza.patient.net.gestionde_bank.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class GestionDeBankApplication {

    private final BankAccountService bankAccountService;

    public GestionDeBankApplication(BankAccountService bankAccountService, BankAccountMapperImpl bankAccountMapperImpl) {
        this.bankAccountService = bankAccountService;
    }

    public static void main(String[] args) {

        SpringApplication.run(GestionDeBankApplication.class, args);
        

    }
    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankService, BankAccountMapperImpl bankAccountMapperImpl){
        return args -> {
            Stream.of("Hassan","Imane","Mohammed").forEach(name->{
                CustomerDTO customer= new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");


                bankService.saveCustomer(customer);
            });
            bankService.listCustomers().forEach(customer->{
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*910000,  9000,customer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random()*10000, 5.5,customer.getId());

                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }
                List<BankAccountDTO> bankAccounts = bankAccountService.listBankAccounts();
                for(BankAccountDTO bankAccount:bankAccounts) {
                    for (int i = 0; i < 10; i++) {
                            String accountId;
                        try {
                            if(bankAccount instanceof SavingAccountDTO){
                                accountId=((SavingAccountDTO) bankAccount ).getId();
                            }else {
                                accountId=((CurrentAccountDTO) bankAccount ).getId();
                            }
                            bankAccountService.credit(accountId, 1000 + Math.random() * 120, "Credit");


                                bankAccountService.debit(accountId, 1000 + Math.random() * 120, "Debit");

                        } catch (BanlanceNotSufficientException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        };
    }

}
