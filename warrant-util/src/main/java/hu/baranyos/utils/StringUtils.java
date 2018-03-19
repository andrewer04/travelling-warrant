package hu.baranyos.utils;

public enum StringUtils {

    LOGIN("Bejelentkezés"),
    TRAVELS("Utazások"),
    FUELING("Tankolások"),
    REPORTS("Elszámolások"),
    USERS("Felhasználók");

    private final String string;

    private StringUtils(final String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public static StringUtils fromString(final String text) {
        for (final StringUtils s : StringUtils.values()) {
            if (s.string.equalsIgnoreCase(text)) {
                return s;
            }
        }
        return null;
    }

}
