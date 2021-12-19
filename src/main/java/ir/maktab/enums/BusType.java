package ir.maktab.enums;

public enum BusType {
    VIP, NORMAL;

    public static BusType getValue(String type) {
        switch (type.toUpperCase()) {
            case "VIP":
                return VIP;
            default:
                return NORMAL;
        }
    }
}
