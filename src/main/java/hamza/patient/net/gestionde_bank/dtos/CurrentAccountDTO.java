package hamza.patient.net.gestionde_bank.dtos;

import hamza.patient.net.gestionde_bank.entities.Customer;
import hamza.patient.net.gestionde_bank.enums.AccountStatus;
import lombok.Data;

import java.util.Date;

@Data
public  class CurrentAccountDTO extends BankAccountDTO{
   private String id;
   private Date createdAt;
   private double balance;
   private AccountStatus status;
   private String currency;
   private CustomerDTO customerDTO;
   private double overDraft;
}
