package hu.baranyos.utils;

public enum ReportStringUtils {

    AMOUNT("Mennyiség"),
    VEHICLE("Jármű"),
    DATE("Dátum"),
    CREATE("Új elszámolás"),
    USER("Felhasználó");

    private final String string;

    private ReportStringUtils(final String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public static ReportStringUtils fromString(final String text) {
        for (final ReportStringUtils s : ReportStringUtils.values()) {
            if (s.string.equalsIgnoreCase(text)) {
                return s;
            }
        }
        return null;
    }

}
