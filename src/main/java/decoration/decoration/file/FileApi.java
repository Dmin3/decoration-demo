package decoration.decoration.file;

import decoration.decoration.Decoration;
import decoration.decoration.quest.PlayerQuest;
import decoration.decoration.quest.PlayerQuestUtil;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class FileApi {
    private File file;
    private YamlConfiguration config;

    public YamlConfiguration getConfig() {
        return config;
    }

    public FileApi(Decoration main) {
        file = new File(main.getDataFolder(), "playerData.yml");
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void saveConfig() {
        Map<String, PlayerQuest> playerQuestMap = PlayerQuestUtil.getPlayerQuestMap();

        for (String playerId : playerQuestMap.keySet()) {
            String dieCount = playerId + ".dieCount";
            String breakBlockCount = playerId + ".breakBlockCount";
            String woodCount = playerId + ".woodCount";

            PlayerQuest playerQuest = playerQuestMap.get(playerId);

            config.set(dieCount, playerQuest.getDieCount());
            config.set(breakBlockCount, playerQuest.getBreakBlockCount());
            config.set(woodCount, playerQuest.getWoodCount());

            try {
                config.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
