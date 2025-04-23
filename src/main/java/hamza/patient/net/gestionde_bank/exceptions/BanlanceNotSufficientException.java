package hamza.patient.net.gestionde_bank.exceptions;

public class BanlanceNotSufficientException extends Exception {
    public BanlanceNotSufficientException(String balanceNotSufficient) {
        super(balanceNotSufficient);
    }
}
