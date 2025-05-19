package BusinessLogic.validators;
import Model.Client;


/**
 * Validator to check the validity of a client's phone number.
 * Assumes valid phone number length is exactly 10 characters.
 */
public class PhoneValidator implements Validator<Client> {
    /**
     * Validates the client's phone number length.
     *
     * @param t the client to validate
     * @throws IllegalArgumentException if the phone number length is not 10
     */
    public void validate(Client t) {
        if(t.getPhone().length() != 10) {
            throw new IllegalArgumentException("Phone number not valid");
        }
    }

}
