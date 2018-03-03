package hu.baranyos.utils;

public enum StringUtils {
    
    BASE_MENU("Base"),
    CHILDREN_MENU("Children"),
    CHILDREN_MENU2("Children2")
    ;

    private final String string;
    
    private StringUtils(String string) {
        this.string = string;
    }
    
    public String getString() {
        return this.string;
    }
    
}
