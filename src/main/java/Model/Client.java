package Model;
/**
 * Represents a client in the system.
 * Contains client details such as id, first name, last name, email, phone, and address.
 */
public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    /**
     * Constructs a new Client with specified attributes.
     *
     * @param id the unique identifier of the client
     * @param firstName the client's first name
     * @param lastName the client's last name
     * @param email the client's email address
     * @param phone the client's phone number
     * @param address the client's physical address
     */
    public Client(int id, String firstName, String lastName, String email, String phone, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;

    }
    public Client() {}


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return "Client [id=" + id + ", firstname=" + firstName + ", lastname=" + lastName +", address=" + address + ", email=" + email + ", phone=" + phone
                + "]";
    }

}
