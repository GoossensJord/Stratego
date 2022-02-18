package be.kdg.applicatienaam.model.pieces;

public enum Rank {
    BOMB(11, "Bomb",6), MARSHAL(10,"mArshal",1), GENERAL(9,"General",1), COLONEL(8,"Colonel",2), MAJOR(7,"MAjor",3), CAPTAIN(6,"Captain",4), LUITENANT(5,"Luitenant",4), SERGEANT(4,"Sergeant",4), MINER(3,"Miner",5), SCOUT(2,"Scout",8), SPY(1,"Spy",1), FLAG(0,"Flag",1),EMPTY(0,"  ",0);
    String name;
    int power;
    int amount;

    Rank(int power, String name , int amnt) {
        this.power = power;
        this.name = name;
        this.amount = amnt;
    }

    public int getAmount() {
        return this.amount;
    }

    public String getName() {
        return this.name;
    }
}