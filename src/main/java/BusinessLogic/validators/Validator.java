package BusinessLogic.validators;
/**
 * Generic interface for validators that check the validity of objects of type T.
 *
 * @param <T> the type of object to be validated
 */
public interface Validator<T> {

    public void validate(T t);
}
