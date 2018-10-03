package hu.baranyos.utils;

public enum StatisticStringUtils {

    NAME("Helyszín");

    private final String string;

    private StatisticStringUtils(final String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public static StatisticStringUtils fromString(final String text) {
        for (final StatisticStringUtils s : StatisticStringUtils.values()) {
            if (s.string.equalsIgnoreCase(text)) {
                return s;
            }
        }
        return null;
    }

}
