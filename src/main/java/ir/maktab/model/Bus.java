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
    @Enumerated
    private BusType type;
    private Integer availableSeat;
    @ManyToOne(cascade = CascadeType.ALL)
    private Company company;
    @OneToMany
    private List<Ticket> tickets;
}
