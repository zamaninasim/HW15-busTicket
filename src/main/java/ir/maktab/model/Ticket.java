package ir.maktab.model;

import ir.maktab.enums.City;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.TimeZone;

@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(value = TemporalType.DATE)
    private Date date;
    @Temporal(value = TemporalType.TIME)
    private Date time;
    private Long Price;
    @Enumerated(EnumType.STRING)
    private City origin;
    @Enumerated(EnumType.STRING)
    private City destination;
    @OneToOne
    private Seat seat;
}
