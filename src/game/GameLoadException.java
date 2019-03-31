package game;

public class GameLoadException extends Exception{
    private String reason;

    public GameLoadException(String r) {
        this.reason = r;
    }

    public String getReason() {
        return reason;
    }
}
