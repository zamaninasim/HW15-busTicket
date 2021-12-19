package ir.maktab.model;

import ir.maktab.enums.City;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer ticketNumber;
    @Temporal(value = TemporalType.DATE)
    private Date date;
    @Temporal(value = TemporalType.TIME)
    private Date time;
    private String ticketType;
    private Long Price;
    private Integer seatNumber;
    private City origin;
    private City destination;
    @ManyToOne
    private Company company;
    @ManyToOne
    private Bus bus;

}
