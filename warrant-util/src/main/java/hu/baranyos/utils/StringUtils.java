package hu.baranyos.utils;

public enum StringUtils {

    LOGIN("Bejelentkezés"),
    LOGOUT("Kijelentkezés"),
    TRAVELS("Utazások"),
    FUELING("Tankolások"),
    REPORTS("Elszámolások"),
    USERS("Felhasználók"),
    NEW_USER("Új felhasználó"),
    NEW_TRAVEL("Új utazás"),
    ADD_FUELING("Új tankolás");

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
