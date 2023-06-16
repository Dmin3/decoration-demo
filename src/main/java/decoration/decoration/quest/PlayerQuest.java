package decoration.decoration.quest;


import java.util.Objects;

public class PlayerQuest {
    private int dieCount;
    private int breakBlockCount;
    private int woodCount;

    public PlayerQuest(int dieCount, int breakBlockCount, int woodCount) {
        this.dieCount = dieCount;
        this.breakBlockCount = breakBlockCount;
        this.woodCount = woodCount;
    }

    public void addBlockBreakCount() {
        this.breakBlockCount += 1;
    }

    public void addWoodBlockCount() {
        this.woodCount += 1;
    }

    public int getWoodCount() {
        return woodCount;
    }

    public void setWoodCount(int woodCount) {
        this.woodCount = woodCount;
    }

    public int getDieCount() {
        return dieCount;
    }

    public void setDieCount(int dieCount) {
        this.dieCount = dieCount;
    }

    public int getBreakBlockCount() {
        return breakBlockCount;
    }

    public void setBreakBlockCount(int breakBlockCount) {
        this.breakBlockCount = breakBlockCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerQuest that = (PlayerQuest) o;
        return dieCount == that.dieCount && breakBlockCount == that.breakBlockCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dieCount, breakBlockCount);
    }
}
