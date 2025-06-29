package data.customer;

import data.AbstractEntity;
import data.cart.Cart;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "customers")
public class Customer extends AbstractEntity {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Email
    @Column(name = "email")
    private String email;
    @Column(name = "telephone")
    private String telephone;
    @OneToMany(mappedBy = "customer")
    private Set<Cart> carts;
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;
    public Customer(String firstName, String lastName, @Email String email,
                    String telephone, Set<Cart> carts, Boolean enabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.carts = carts;
        this.enabled = enabled;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(telephone, customer.telephone) &&
                Objects.equals(carts, customer.carts) &&
                Objects.equals(enabled, customer.enabled);
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, telephone, enabled);
    }
}