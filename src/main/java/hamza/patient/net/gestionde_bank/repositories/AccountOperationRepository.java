package hamza.patient.net.gestionde_bank.repositories;

import hamza.patient.net.gestionde_bank.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<Operation,Long> {
}
