package ir.maktab.model;

import ir.maktab.enums.BusType;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
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
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "bus")
    private List<Seat> seats=new ArrayList<>();
    @ManyToOne
    private Company company;
    @OneToMany(mappedBy = "company")
    private List<Ticket> tickets;
}
