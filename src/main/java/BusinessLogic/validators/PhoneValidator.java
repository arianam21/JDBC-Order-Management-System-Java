package BusinessLogic.validators;
import Model.Client;



public class PhoneValidator implements Validator<Client> {
    public void validate(Client t) {
        if(t.getPhone().length() != 10) {
            throw new IllegalArgumentException("Phone number not valid");
        }
    }

}
