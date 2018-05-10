package hu.baranyos.utils;

public enum ReportStringUtils {

    VEHICLE("Jármű"),
    DATE("Dátum"),
    CREATE("Új elszámolás"),
    VIEW("Nézet"),
    DETAILS("Részletek"),
    DISTANCE_SUM("Megtett KM:"),
    FUELING_SUM("Összes tankolás:"),
    USER_KM("Felhasználó/KM"),
    USER_FUELINGS("Felhasználó/tankolás"),
    USER_BALANCE("Egyenleg"),
    KM_COST("Ft/Km"),
    USER("Felhasználó"),
    WARNING("Nem sikerült");

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
