package driver;

public enum BrowserType {
    FIREFOX,
    CHROME,
    CHROMIUM,
    EDGE;

    public static BrowserType from(String value) {
        if (value == null) return FIREFOX;
        String v = value.trim().toLowerCase();
        return switch (v) {
            case "firefox" -> FIREFOX;
            case "chrome" -> CHROME;
            case "chromium" -> CHROMIUM;
            case "edge" -> EDGE;
            default -> FIREFOX;
        };
    }
}
