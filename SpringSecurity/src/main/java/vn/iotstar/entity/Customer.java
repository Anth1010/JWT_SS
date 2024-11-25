package vn.iotstar.entity;





import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Represents a customer entity with basic information.
 */
@Data
@AllArgsConstructor // Generates a constructor with all fields
@NoArgsConstructor  // Generates a no-args constructor
@ToString           // Generates a toString method
@Builder            // Enables the builder pattern
public class Customer {

    private String id;           // Unique identifier for the customer
    private String name;         // Customer's full name
    private String phoneNumber;  // Customer's phone number
    private String email;        // Customer's email address
}
