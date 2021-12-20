package ir.maktab.model;

import ir.maktab.enums.BusType;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String plaque;
    @Enumerated(EnumType.STRING)
    private BusType type;
    //private Integer availableSeat;
    @OneToMany
    private List<Seat> seats;
    @ManyToOne
    private Company company;
    @OneToMany(mappedBy = "company")
    private List<Ticket> tickets;
}
