package ir.maktab.enums;

public enum ReservationType {
    PROCESSING, PAID;

    public static ReservationType getValue(String type) {
        switch (type.toUpperCase()) {
            case "PROCESSING":
                return PROCESSING;
            case "PAID":
                return PAID;
            default:
                return PROCESSING;
        }
    }
}
