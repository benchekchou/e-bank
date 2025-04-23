package hamza.patient.net.gestionde_bank.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DiscriminatorValue("CA")
@Entity @Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentAccount extends BankAccount {
    private double overDraft;
}
