package decoration.decoration.quest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PlayerQuestUtil {
    private static Map<String, PlayerQuest> playerQuestMap;

    public static Map<String, PlayerQuest> getPlayerQuestMap() {
        return playerQuestMap;
    }

    public static void setPlayerQuestMap(String playerId, PlayerQuest playerQuest) {
        playerQuestMap.put(playerId, playerQuest);
    }

    public static void setUp(){
        playerQuestMap = new ConcurrentHashMap<>() {};
    }
}
