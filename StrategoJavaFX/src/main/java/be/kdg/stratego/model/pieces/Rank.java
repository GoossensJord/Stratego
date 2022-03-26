package be.kdg.stratego.model.pieces;

import javafx.scene.image.Image;

/**
 * Enum class that holds all the differnt kinds of pieces, their power level, name, amount per player and their respective image.
 */
public enum Rank {
    BOMB(11, "Bomb", 6, new Image("bomb.png")),
    MARSHAL(10, "Marshal", 1, new Image("Marshal.png")),
    GENERAL(9, "General", 1, new Image("General.png")),
    COLONEL(8, "Colonel", 2, new Image("Colonel.png")),
    MAJOR(7, "Major", 3, new Image("Major.png")),
    CAPTAIN(6, "Captain", 4, new Image("Captain.png")),
    LUITENANT(5, "Luitenant", 4, new Image("Luitenant.png")),
    SERGEANT(4, "Sergeant", 4, new Image("Sergeant.png")),
    MINER(3, "Miner", 5, new Image("Miner.png")),
    SCOUT(2, "Scout", 8, new Image("Scout.png")),
    SPY(1, "Spy", 1, new Image("Spy.png")),
    FLAG(0, "Flag", 1, new Image("Flag.png")),
    EMPTY(0, null, 0, null);

    String name;
    int power;
    int amount;
    Image image;
    static int counter;

    /**
     * Constructor for Rank, initialises the power level, name of the rank, the amount you have and the image
     */
    Rank(int power, String name, int amnt, Image image) {
        this.power = power;
        this.name = name;
        this.amount = amnt;
        this.image = image;
    }

    public int getAmount() {
        return this.amount;
    }

    public String getName() {
        return this.name;
    }

    public Image getImage() {
        return image;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}