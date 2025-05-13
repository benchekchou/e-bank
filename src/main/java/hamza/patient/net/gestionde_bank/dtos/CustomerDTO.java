package hamza.patient.net.gestionde_bank.dtos;

import lombok.Builder;
import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
}
