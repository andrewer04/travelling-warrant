package hu.baranyos.utils;

public enum UserStringUtils {

    FIRST_NAME("Vezetéknév"),
    LAST_NAME("Keresztnév"),
    AGE("Kor"),
    GENDER("Nem"),
    PASSWORD("Jelszó"),
    SAVE_BUTTON("Mentés"),
    CLEAR_BUTTON("Törlés"),
    REQUIRED("Kötelező"),
    AGE_WARNING("Kérlek számot írj bele!"),
    PASSWORD_WARNING("Hibás email!"),
    AGE_RANGE_WARNING("10 - 99 között kell lennie!");

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
