package hu.baranyos.utils;

public enum UserStringUtils {

    FIRST_NAME("Vezetéknév"),
    LAST_NAME("Keresztnév"),
    AGE("Kor"),
    GENDER("Nem"),
    PASSWORD("Jelszó"),
    SAVE_BUTTON("Mentés"),
    CLEAR_BUTTON("Törlés");

    private final String string;

    private UserStringUtils(final String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public static UserStringUtils fromString(final String text) {
        for (final UserStringUtils s : UserStringUtils.values()) {
            if (s.string.equalsIgnoreCase(text)) {
                return s;
            }
        }
        return null;
    }

}
