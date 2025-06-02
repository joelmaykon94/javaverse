package data.cart;

import data.AbstractEntity;
import data.customer.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor(force = true)
@ToString(callSuper = true)
@Entity
@Table(name = "carts")
public class Cart extends AbstractEntity {
    @ManyToOne
    private final Customer customer;
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CartStatus status;
    public Cart(Customer customer, @NotNull CartStatus status) {
        this.customer = customer;
        this.status = status;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(customer, cart.customer) &&
                status == cart.status;
    }
    @Override
    public int hashCode() {
        return Objects.hash(customer, status);
    }

}