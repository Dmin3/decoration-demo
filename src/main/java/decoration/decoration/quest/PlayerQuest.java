package decoration.decoration.quest;


import java.util.Objects;

public class PlayerQuest {
    private int diamondCount;
    private int woodCount;
    private int killCount;
    private int zombieCount;
    private int creeperCount;
    private int skeletonCount;
    private int ironCount;
    private int dieCount;

    public void addWoodCount(){
        this.woodCount += 1;
    }

    public void addDiamondCount(){
        this.diamondCount += 1;
    }


    public PlayerQuest() {
    }

    public PlayerQuest(
            int diamondCount,
            int woodCount,
            int killCount,
            int zombieCount,
            int creeperCount,
            int skeletonCount,
            int ironCount,
            int dieCount) {
        this.diamondCount = diamondCount;
        this.woodCount = woodCount;
        this.killCount = killCount;
        this.zombieCount = zombieCount;
        this.creeperCount = creeperCount;
        this.skeletonCount = skeletonCount;
        this.ironCount = ironCount;
        this.dieCount = dieCount;
    }

    public int getDiamondCount() {
        return diamondCount;
    }

    public int getWoodCount() {
        return woodCount;
    }

    public int getKillCount() {
        return killCount;
    }

    public int getZombieCount() {
        return zombieCount;
    }

    public int getCreeperCount() {
        return creeperCount;
    }

    public int getSkeletonCount() {
        return skeletonCount;
    }

    public int getIronCount() {
        return ironCount;
    }

    public int getDieCount() {
        return dieCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerQuest that = (PlayerQuest) o;
        return diamondCount == that.diamondCount && woodCount == that.woodCount && killCount == that.killCount && zombieCount == that.zombieCount && creeperCount == that.creeperCount && skeletonCount == that.skeletonCount && ironCount == that.ironCount && dieCount == that.dieCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(diamondCount, woodCount, killCount, zombieCount, creeperCount, skeletonCount, ironCount, dieCount);
    }

    public void addIronCount() {
        this.ironCount += 1;
    }

    public void addZombieCount() {
        this.zombieCount += 1;
    }

    public void addKillCount() {
        this.killCount += 1;
    }

    public void addDieCount() {
        this.dieCount += 1;
    }

    public void addSkeletonCount() {
        this.skeletonCount += 1;
    }
}
