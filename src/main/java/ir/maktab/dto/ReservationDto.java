package ir.maktab.dto;

import ir.maktab.enums.BusType;
import ir.maktab.enums.City;
import ir.maktab.enums.ReservationType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReservationDto {
    private Date reserveDate;
    private ReservationType reservationType;
    private long totalPrice;
    private List<Integer> seatNumber;
    private Date tripDate;
    private Date tripTime;
    private City origin;
    private City destination;
    private BusType busType;
    private String company;

    @Override
    public String toString() {
        return "ReservationDto{" +
                "reserveDate=" + reserveDate +
                ", reservationType=" + reservationType +
                ", totalPrice=" + totalPrice +
                ", seatNumber=" + seatNumber +
                ", tripDate=" + tripDate +
                ", tripTime=" + tripTime +
                ", origin=" + origin +
                ", destination=" + destination +
                ", busType=" + busType +
                ", company='" + company + '\'' +
                "}\n";
    }
}
