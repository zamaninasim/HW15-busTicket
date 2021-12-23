package ir.maktab.model;

import ir.maktab.enums.BusType;
import lombok.Data;

import java.util.Date;

@Data
public class Condition {
    private String CompanyName;
    private BusType busType;
    private Long minPrice;
    private Long maxPrice;
    private Date minTime;
    private Date maxTime;
    private Date date;
}
