package ir.maktab.model;

import ir.maktab.enums.ReservationType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "reservation", fetch = FetchType.EAGER)
    private List<Ticket> tickets;
    @ManyToOne
    private Customer customer;
    @CreationTimestamp
    private Date reserveDate;
    @Enumerated(EnumType.STRING)
    private ReservationType reservationType;
    private long totalPrice;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reserveDate=" + reserveDate +
                ", reservationType=" + reservationType +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
