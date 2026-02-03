package config;

public class Config {
    private final String baseUrl;
    private final int defaultTimeoutSeconds;

    public Config(String baseUrl, int defaultTimeoutSeconds) {
        this.baseUrl = baseUrl;
        this.defaultTimeoutSeconds = defaultTimeoutSeconds;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public int getDefaultTimeoutSeconds() {
        return defaultTimeoutSeconds;
    }
}
