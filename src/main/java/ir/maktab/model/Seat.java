package ir.maktab.model;

import ir.maktab.enums.SeatType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer seatNumber;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    @ManyToOne
    private Bus bus;

    @Override
    public String toString() {
        return ", seatNumber=" + seatNumber +
                ", seatType=" + seatType +
                ", busType=" + bus.getType() +
                ", busAvailableSeat=" + bus.getAvailableSeat() +
                ", company=" + bus.getCompany().getName();
    }
}
