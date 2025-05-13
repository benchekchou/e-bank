package hamza.patient.net.gestionde_bank.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@DiscriminatorValue("SA")
@Entity @Data @AllArgsConstructor
@NoArgsConstructor
public class SavingAccount extends BankAccount{
    private double intersetRate;

}
