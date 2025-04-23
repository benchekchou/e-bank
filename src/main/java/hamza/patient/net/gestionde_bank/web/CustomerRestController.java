package hamza.patient.net.gestionde_bank.web;

import hamza.patient.net.gestionde_bank.entities.Customer;
import hamza.patient.net.gestionde_bank.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CustomerRestController {
    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    public List<Customer> listCustomers(){
        return bankAccountService.listCustomers();
    }
}
