package decoration.decoration.quest;

import java.util.HashMap;
import java.util.Map;

public class PlayerQuestUtil {
    private static Map<String, PlayerQuest> playerQuestMap = new HashMap<>();

    public static Map<String, PlayerQuest> getPlayerQuestMap() {
        return playerQuestMap;
    }

    public static void setPlayerQuestMap(String playerId, PlayerQuest playerQuest) {
        playerQuestMap.put(playerId, playerQuest);
    }
}
