package hamza.patient.net.gestionde_bank.web;

import hamza.patient.net.gestionde_bank.dtos.CustomerDTO;
import hamza.patient.net.gestionde_bank.dtos.OperationDTO;
import hamza.patient.net.gestionde_bank.entities.Customer;
import hamza.patient.net.gestionde_bank.entities.Operation;
import hamza.patient.net.gestionde_bank.exceptions.CustomerNotFoundException;
import hamza.patient.net.gestionde_bank.repositories.AccountOperationRepository;
import hamza.patient.net.gestionde_bank.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {
    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    public List<CustomerDTO> listCustomers(){
        return bankAccountService.listCustomers();
    }
    @GetMapping("/customers/search{kw}")
    public List<CustomerDTO> listCustomers(@RequestParam(name="kw" ,defaultValue = "") String kw){
        return bankAccountService.searchCustomers("%"+kw+"%");
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name="id") Long customerId) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(customerId);
    }
   @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return bankAccountService.saveCustomer(customerDTO);
   }
   @PutMapping("/customers/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO){
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);

   }
   @DeleteMapping("/customers/{Id}")
   public void deleteCustomer(@PathVariable Long Id){
            bankAccountService.deleteCustomer(Id);
   }



}
