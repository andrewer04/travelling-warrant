package hu.baranyos.utils;

public enum VehicleStringUtils {

    NAME("Név"),
    CONSUMPTION("Fogyasztás (L/100 KM)"),
    LICENCE_PLATE_NUMBER("Rendszám"),
    SPEEDOMETER("Km óra állás"),
    CONSUMPTION_WARNING("Helyes fogyasztást adj meg!"),
    SPEEDOMETER_WARNING("Helyes km óra állást adj meg!"),
    LICENCE_WARNING("Helyes rendszámot adj meg");

    private final String string;

    private VehicleStringUtils(final String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public static VehicleStringUtils fromString(final String text) {
        for (final VehicleStringUtils s : VehicleStringUtils.values()) {
            if (s.string.equalsIgnoreCase(text)) {
                return s;
            }
        }
        return null;
    }

}
