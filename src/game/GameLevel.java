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
     * @param playerHealth int player's amount of health
     * @param game the game in which the player is in
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
            writer.write(name + "\t" + level + "\t" + score + "\t" + level2WasCompleted + "\t" + level3WasCompleted + "\t" + level4WasCompleted + "\n");
            System.out.println("data inputted into file");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * Loads game from a txt file
     * <p>
     *     Loads the game from a txt file where the data was saved, the data is in a sentence and is split over tabs
     *     then the data is setted at the end of the method
     * </p>
     * @param fileName from which file is the data read
     * @param game to which game is the information loaded
     * @throws GameLoadException since there may be no file with the fileName it can throw an exception if there is a typo in the
     * file name or the file doesn't exist to begin with
     */
    //load the most recent save
    public void loadFile(String fileName, Game game) throws GameLoadException{
        FileReader fr = null;
        try {
            fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            String previousLine = null;
            String line = br.readLine();
            while (line != null) { //read last line
                previousLine = line;
                line = br.readLine();
            }
            if (previousLine == null) {
                throw new GameLoadException("no data in the file");
            } else {
                String[] split = previousLine.split("\t");
                if (split.length != 6) {
                    System.out.println("algo2 " + split.length);
                    throw new GameLoadException("line needs six items");
                } else {
                    System.out.println("algo3");
                    String playerName = split[0];
                    String level = split[1];
                    int score;
                    boolean level2Completion;
                    boolean level3Completion;
                    boolean level4Completion;
                    try {
                        score = Integer.parseInt(split[2]);
                    }
                    catch (NumberFormatException e) {
                        throw new GameLoadException("third item need to be number");
                    }
                    try {
                        level2Completion = Boolean.parseBoolean(split[3]);
                    }
                    catch (NumberFormatException e){
                        throw new GameLoadException("Fourth item needs to be a boolean");
                    }
                    try {
                        level3Completion = Boolean.parseBoolean(split[4]);
                    }
                    catch (NumberFormatException e){
                        throw new GameLoadException("Fifth item needs to be a boolean");
                    }
                    try {
                        level4Completion = Boolean.parseBoolean(split[5]);
                    }
                    catch (NumberFormatException e){
                        throw new GameLoadException("Sixth item needs to be a boolean");
                    }
                    GameLevel returnLevel = null;
                    if (level.equals("1")) {
                        returnLevel = new Level1();
                    } else if (level.equals("2")) {
                        returnLevel = new Level2();
                    } else if (level.equals("3")) {
                        returnLevel = new Level3();
                    } else if (level.equals("4")) {
                        returnLevel = new Level4();
                    } else {
                        throw new GameLoadException("error: item two need to specify a level");
                    }
                    //setts the data into the game
                    game.getData().setScore(score);
                    game.setLevel2WasCompleted(level2Completion);
                    game.setLevel3WasCompleted(level3Completion);
                    game.setLevel4WasCompleted(level4Completion);
                    game.setLevel(Integer.parseInt(level));
                    game.setWorld(returnLevel);
                    game.levelPopulation();
                    System.out.println("load complete");
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
