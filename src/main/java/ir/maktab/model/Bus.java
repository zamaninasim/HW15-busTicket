package ir.maktab.model;

import ir.maktab.enums.BusType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String plaque;
    @Enumerated(EnumType.STRING)
    private BusType type;
    private Integer availableSeat;
    private Integer numberOfSeats;
    @ManyToOne
    private Company company;

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", plaque='" + plaque + '\'' +
                ", type=" + type +
                ", availableSeat=" + availableSeat +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }
}
