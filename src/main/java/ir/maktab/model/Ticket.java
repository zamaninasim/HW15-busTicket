package ir.maktab.model;

import ir.maktab.enums.TicketType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer seatNumber;
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;
    @ManyToOne
    private Trip trip;
    @OneToOne
    private Owner owner;
    @ManyToOne
    private Reservation reservation;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", seatNumber=" + seatNumber +
                ", ticketType=" + ticketType +
                ", trip=" + trip +
                ", owner=" + owner +
                '}';
    }
}
