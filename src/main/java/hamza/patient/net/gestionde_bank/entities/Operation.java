package hamza.patient.net.gestionde_bank.entities;

import hamza.patient.net.gestionde_bank.enums.OperationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity @Data
@AllArgsConstructor @NoArgsConstructor
public class Operation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    @Enumerated(EnumType.STRING)
    private OperationType type;
    private Date date;
    @ManyToOne
    private BankAccount bankAccount;
    private String description;

}
