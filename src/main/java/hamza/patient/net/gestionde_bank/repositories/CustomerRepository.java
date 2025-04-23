package hamza.patient.net.gestionde_bank.repositories;

import hamza.patient.net.gestionde_bank.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
