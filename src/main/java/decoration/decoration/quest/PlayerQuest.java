package decoration.decoration.quest;

import java.util.Objects;

public class PlayerQuest {
    private String playerName;

    public PlayerQuest(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerQuest that = (PlayerQuest) o;
        return Objects.equals(playerName, that.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }
}
