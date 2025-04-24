package hamza.patient.net.gestionde_bank.repositories;

import hamza.patient.net.gestionde_bank.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<Operation,Long> {
    List<Operation> findByBankAccount_Id(String accountId);
    Page<Operation> findByBankAccount_Id(String accountId, Pageable pageable);
}
