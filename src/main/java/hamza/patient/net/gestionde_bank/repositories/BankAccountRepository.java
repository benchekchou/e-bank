package hamza.patient.net.gestionde_bank.repositories;

import hamza.patient.net.gestionde_bank.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
