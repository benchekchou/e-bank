package hamza.patient.net.gestionde_bank.mappers;


import hamza.patient.net.gestionde_bank.dtos.CustomerDTO;
import hamza.patient.net.gestionde_bank.entities.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {
    public CustomerDTO  fromCustomer(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        return customerDTO;
    }

    public Customer  fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customer,customerDTO);
        return customer;
    }


}
