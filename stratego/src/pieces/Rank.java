package pieces;

public enum Rank {
    BOMB(11, "Bomb"), MARSHAL(10,"Marshal"), GENERAL(9,"General"), COLONEL(8,"Colonel"), MAJOR(7,"Major"), CAPTAIN(6,"Captain"), LUITENANT(5,"Luitenant"), SERGEANT(4,"Sergeant"), MINER(3,"Miner"), SCOUT(2,"Scout"), SPY(1,"Spy"), FLAG(0,"Flag");
    String name;
    int power;

    Rank(int power, String name) {
        this.power = power;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}