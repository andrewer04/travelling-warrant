package hu.baranyos.utils;

public enum TravelStringUtils {

    VEHICLE("Jármű"),
    START_LOCATION("Honnan:"),
    END_LOCATION("Hova:"),
    START_KM("Kezdő Km:"),
    END_KM("Befejező Km:"),
    DATE("Dátum"),
    DISTANCE("Táv"),
    USERS("Utasok:");

    private final String string;

    private TravelStringUtils(final String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public static TravelStringUtils fromString(final String text) {
        for (final TravelStringUtils s : TravelStringUtils.values()) {
            if (s.string.equalsIgnoreCase(text)) {
                return s;
            }
        }
        return null;
    }

}
