package ir.maktab.dto;

import ir.maktab.enums.BusType;
import lombok.Data;

import java.util.Date;

@Data
public class TripDto {
    private Integer id;
    private Date date;
    private Date time;
    private Long Price;
    private BusType busType;
    private Integer availableSeat;
    private String companyName;

    @Override
    public String toString() {
        return "TripDto{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", Price=" + Price +
                ", busType=" + busType +
                ", availableSeat=" + availableSeat +
                ", companyName='" + companyName +
                "}\n";
    }
}
