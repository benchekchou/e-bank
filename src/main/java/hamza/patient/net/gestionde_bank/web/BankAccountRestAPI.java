package hamza.patient.net.gestionde_bank.web;

import hamza.patient.net.gestionde_bank.dtos.AccountHistoryDTO;
import hamza.patient.net.gestionde_bank.dtos.BankAccountDTO;
import hamza.patient.net.gestionde_bank.dtos.OperationDTO;
import hamza.patient.net.gestionde_bank.exceptions.BankAccountNotFoundException;
import hamza.patient.net.gestionde_bank.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")

public class BankAccountRestAPI {

    private BankAccountService bankAccountService;
    public BankAccountRestAPI(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccountById(accountId);
    }
    @GetMapping("/accounts/")
    public List<BankAccountDTO> listBankAccounts(){
        return bankAccountService.listBankAccounts();
    }
    @GetMapping("/accounts/{accountId}/operations")
    public List<OperationDTO> getHistory(@PathVariable String accountId){
        return bankAccountService.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(@PathVariable String accountId,
                                               @RequestParam(name = "page",defaultValue = "0") int page,
                                               @RequestParam(name = "size",defaultValue = "5")int size) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(accountId,page,size);
    }




}
