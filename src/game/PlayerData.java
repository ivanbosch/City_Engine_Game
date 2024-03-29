package game;

public class PlayerData {
    private Player player;
    private int score;
    private int health;

    //Data object to manage Player health and score around the game
    public PlayerData (Player player) {
        this.player = player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
