package ir.maktab.model;

import ir.maktab.enums.City;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(value = TemporalType.DATE)
    private Date date;
    @Temporal(value = TemporalType.TIME)
    private Date time;
    @Enumerated(EnumType.STRING)
    private City origin;
    @Enumerated(EnumType.STRING)
    private City destination;
    private Long price;
    @OneToMany(mappedBy = "trip")
    private List<Ticket> tickets=new ArrayList<>();
    @ManyToOne
    private Bus bus;

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", origin=" + origin +
                ", destination=" + destination +
                ", company=" + bus.getCompany().getName() +
                ", busType=" + bus.getType()+
                ", busNumberOfSeats=" + bus.getNumberOfSeats()+
                ", AvailableSeat=" + bus.getAvailableSeat() +
                "}\n";
    }
}
