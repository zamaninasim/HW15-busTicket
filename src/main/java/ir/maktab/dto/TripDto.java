package ir.maktab.dto;

import ir.maktab.enums.BusType;
import lombok.Data;

import java.util.Date;
@Data
public class TripDto {
    private Date date;
    private Date time;
    private Long Price;
    private BusType busType;
    private Integer availableSeat;
    private String companyName;
}
