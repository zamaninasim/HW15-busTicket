package ir.makatb.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@ToString(callSuper = true)
public class Customer extends User {
    @OneToMany
    private List<Reservation> reservations;
}
