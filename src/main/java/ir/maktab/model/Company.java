package ir.maktab.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany
    private List<Ticket> tickets;
    @OneToMany
    private List<Bus> buses;
}
