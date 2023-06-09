package decoration.decoration.quest;


import java.util.Objects;

public class PlayerQuest {
    private int dieCount;
    private int breakBlockCount;

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

    public PlayerQuest(int dieCount, int breakBlockCount) {
        this.dieCount = dieCount;
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
