package pieces;

public enum Rank {
    BOMB(11, "Bomb",7), MARSHAL(10,"Marshal",1), GENERAL(9,"General",1), COLONEL(8,"Colonel",2), MAJOR(7,"Major",4), CAPTAIN(6,"Captain",5), LUITENANT(5,"Luitenant",5), SERGEANT(4,"Sergeant",5), MINER(3,"Miner",6), SCOUT(2,"Scout",9), SPY(1,"Spy",1), FLAG(0,"Flag",1),EMPTY(0,"Empty",0);
    String name;
    int power;
    int amnt;

    Rank(int power, String name , int amnt) {
        this.power = power;
        this.name = name;
        this.amnt = amnt;

    }

    public String getName() {
        return name;
    }
}