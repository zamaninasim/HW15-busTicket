package ir.makatb.model;

import ir.makatb.enums.BusType;
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
    private BusType type;
    private Integer availableSeat;
    @OneToMany
    private List<Ticket> tickets;
}
