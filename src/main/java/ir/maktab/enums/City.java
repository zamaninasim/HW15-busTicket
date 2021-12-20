package ir.maktab.enums;

public enum City {
    TEHRAN, SHIRAZ, MASHHAD, ESFAHAN, TABRIZ, SARI, RASHT, KASHAN,NONE;

    public static City getValue(String city) {
        switch (city.toUpperCase()) {
            case "TEHRAN":
                return TEHRAN;
            case "SHIRAZ":
                return SHIRAZ;
            case "MASHHAD":
                return MASHHAD;
            case "ESFAHAN":
                return ESFAHAN;
            case "TABRIZ":
                return TABRIZ;
            case "SARI":
                return SARI;
            case "RASHT":
                return RASHT;
            case "KASHAN":
                return KASHAN;
            default:
                return NONE;
        }
    }
}
