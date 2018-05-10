package hu.baranyos.utils;

public enum FuelingStringUtils {

    AMOUNT("Mennyiség"),
    VEHICLE("Jármű"),
    DATE("Dátum"),
    USER("Felhasználó"),
    VEHICLE_PLACEHOLDER("Adj meg egy járművet"),
    AMOUNT_WARNING("Helyes összeget adj meg");

    private final String string;

    private FuelingStringUtils(final String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public static FuelingStringUtils fromString(final String text) {
        for (final FuelingStringUtils s : FuelingStringUtils.values()) {
            if (s.string.equalsIgnoreCase(text)) {
                return s;
            }
        }
        return null;
    }

}
