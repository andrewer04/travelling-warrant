package hu.baranyos.utils;

public enum Gender {

    FEMALE("Nő"), MALE("Férfi");

    private final String string;

    private Gender(final String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public static Gender fromString(final String text) {
        for (final Gender s : Gender.values()) {
            if (s.string.equalsIgnoreCase(text)) {
                return s;
            }
        }
        return null;
    }

}
