package hu.baranyos.utils;

public enum UserStringUtils {

    FIRST_NAME("Vezetéknév"),
    LAST_NAME("Keresztnév"),
    AGE("Kor"),
    GENDER("Nem"),
    PASSWORD("Jelszó"),
    PASSWORD_AGAIN("Jelszó mégegyszer"),
    USERNAME("Felhasználónév"),
    LOGIN("Bejelentkezés"),
    CANCEL_BUTTON("Mégse"),
    SIGNUP_BUTTON("Regisztráció"),
    SAVE_BUTTON("Mentés"),
    CLEAR_BUTTON("Törlés"),
    REQUIRED("Kötelező"),
    AGE_WARNING("Kérlek számot írj bele!"),
    PASSWORD_WARNING("Minimum 5 karakter hosszúnak kell lennie!"),
    AGE_RANGE_WARNING("10 - 99 között kell lennie!"),
    LOGIN_FAILED("Hibás felhasználónév vagy jelszó! Próbáld újra");

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
