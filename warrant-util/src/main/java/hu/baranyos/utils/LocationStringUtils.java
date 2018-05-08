package hu.baranyos.utils;

public enum LocationStringUtils {

    NAME("Név");

    private final String string;

    private LocationStringUtils(final String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public static LocationStringUtils fromString(final String text) {
        for (final LocationStringUtils s : LocationStringUtils.values()) {
            if (s.string.equalsIgnoreCase(text)) {
                return s;
            }
        }
        return null;
    }

}
