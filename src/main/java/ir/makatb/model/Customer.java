package ir.makatb.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity

public class Customer extends User {
    @OneToMany
    private List<Reservation> reservations;
}
