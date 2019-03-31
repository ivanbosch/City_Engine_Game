package game;

import city.cs.engine.World;
import org.jbox2d.common.Vec2;
import java.io.*;
import java.util.Timer;

/**
 * @author  Ivan, Bosch. Ivan.bosch@city.ac.uk
 * @version Version 28
 * @since Version 5
 */
public abstract class GameLevel extends World {
    private Player spaceship;
    private Timer timer;

    /**
     * GameLevel constructor which is used to tie the timer to the gameLevel
     */
    public GameLevel() {
        this.timer = new Timer();
    }

    /**
     * Player getter
     * @return Player type spaceship
     */
    public Player getPlayer() {
        return spaceship;
    }

    /**
     * Timer getter
     * @return Timer timer
     */
    public Timer getTimer() {
        return timer;
    }
    /**
     * Method used to populate the levels with the player's spaceship
     *
     * <p>
     *     This method is used typically by all level classes that extend this class, they are super called
     *     to create the spaceship and they are able to add any other extra bodies that they want for that level.
     *
     * @return returns a spaceship which is controlled by the player
     */
    public void populate(Game game, int playerHealth) {
        spaceship = new Player(this , playerHealth, game);
        spaceship.setPosition(playerStartPosition());
        spaceship.addCollisionListener(new PlayerCollision(spaceship, game));
    }

    /**
     * Sets the player starting position
     * <p>
     *     The levels that extend from this class can set their own starting position for the spaceship created.
     *
     * @return The position of a Player type object in the form of a Vec2
     */
    //player start position is declared in each different level
    public abstract Vec2 playerStartPosition();

    /**
     * Level completion variable
     * <p>
     *     The requirements to complete a level are set in this method, if the player does not meet this
     *     requirement it is not able to further advance to the next level
     * </p>
     * @return A boolean that calls on if the level is completed or not
     */
    public abstract boolean isCompleted();

    /**
     * Saves game data into a save file
     * <p>
     *     Saves different data into the save.txt found in the data directory, the data being saved
     *     are player name(user), level, score, boolean level2WasCompleted, boolean level3WasCompleted,
     *     level4WasCompleted.
     * </p>
     * @param fileName file being saved to.
     * @param name user name by default user.
     * @param level level in which the player is.
     * @param score score that the player has.
     * @param level2WasCompleted first level was completed.
     * @param level3WasCompleted second level was completed.
     * @param level4WasCompleted third level was completed.
     * @throws IOException since it saves into a file errors may occur if the file is not found.
     */
    // Save into save.txt the players data and the completion of levels
    public void save(String fileName, String name, int level, int score, boolean level2WasCompleted,
                     boolean level3WasCompleted, boolean level4WasCompleted) throws IOException {
        boolean append = true;
        FileWriter writer = null;
        try{
            writer = new FileWriter(fileName, append);
            writer.write(name + "   " + level + "   " + score + "   " + level2WasCompleted + "  " + level3WasCompleted + "  " + level4WasCompleted + "\n");
            System.out.println("data inputted into file");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    //load the most recent save
    public static int loadFile(String fileName) throws GameLoadException{
        FileReader fr = null;
        try {
            fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            if (line == null) {
                throw new GameLoadException("no data in the file");
            } else {
                String[] split = line.split("\t");
                if (split.length != 6) {
                    throw new GameLoadException("line needs three items");
                } else {
                    String playerName = split[0];
                    String level = split[1];
                    int score;
                    try {
                        score = Integer.parseInt(split[2]);
                    }
                    catch (NumberFormatException e) {
                        throw new GameLoadException("third item need to be number");
                    }
                    try {
                        boolean level2Completion = Boolean.parseBoolean(split[3]);
                    }
                    catch (NumberFormatException e){
                        throw new GameLoadException("Fourth item needs to be a boolean");
                    }
                    try {
                        boolean level3Completion = Boolean.parseBoolean(split[4]);
                    }
                    catch (NumberFormatException e){
                        throw new GameLoadException("Fifth item needs to be a boolean");
                    }
                    try {
                        boolean level4Completion = Boolean.parseBoolean(split[5]);
                    }
                    catch (NumberFormatException e){
                        throw new GameLoadException("Sixth item needs to be a boolean");
                    }
                    int returnLevel = 0;
                    if (level.equals("1")) {
                        returnLevel = 1;
                    } else if (level.equals("2")) {
                        returnLevel = 2;
                    } else if (level.equals("3")) {
                        returnLevel = 3;
                    } else if (level.equals("4")) {
                        returnLevel = 4;
                    } else {
                        throw new GameLoadException("error: item two need to specify a level");
                    }
                    return returnLevel;
                }
            }
        }
        catch (FileNotFoundException e) {
            throw new GameLoadException("file does not exist");
        }
        catch (IOException e) {
            throw  new GameLoadException("problem reading file");
        }

    }
}
