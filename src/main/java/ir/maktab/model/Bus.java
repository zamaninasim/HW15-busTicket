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
    @OneToMany(mappedBy = "bus",fetch = FetchType.EAGER)
    private List<Seat> seats=new ArrayList<>();
    private Integer availableSeat;
    @ManyToOne
    private Company company;

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", plaque='" + plaque + '\'' +
                ", type=" + type +
                ", availableSeat=" + availableSeat +
                ", company=" + company.getName() +
                '}';
    }
}
