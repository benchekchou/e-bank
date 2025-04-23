package hamza.patient.net.gestionde_bank.dtos;

import hamza.patient.net.gestionde_bank.enums.OperationType;
import lombok.Data;

import java.util.Date;

@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private String description;
    private OperationType type;
}
