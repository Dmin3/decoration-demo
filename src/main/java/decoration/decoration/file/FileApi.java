package decoration.decoration.file;

import decoration.decoration.Decoration;
import decoration.decoration.quest.PlayerQuest;
import decoration.decoration.quest.PlayerQuestUtil;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class FileApi {
    private static FileApi fileApi;
    private Decoration main;
    private static File file;
    private static YamlConfiguration config;

    public YamlConfiguration getConfig() {
        return getFileApi().getConfig();
    }

    public static FileApi getFileApi() {
        return fileApi;
    }

    public static void setUp(Decoration main){
        fileApi = new FileApi(main);
        file = new File(fileApi.main.getDataFolder(), "playerData.yml");
        config = YamlConfiguration.loadConfiguration(file);
    }

    private FileApi(Decoration main) {
        this.main = main;
    }

    public void saveConfig() {
        Map<String, PlayerQuest> playerQuestMap = PlayerQuestUtil.getPlayerQuestMap();

        for (String playerId : playerQuestMap.keySet()) {
            String dieCount = playerId + ".dieCount";
            String breakBlockCount = playerId + ".breakBlockCount";

            PlayerQuest playerQuest = playerQuestMap.get(playerId);

            config.set(dieCount, playerQuest.getDieCount());
            config.set(breakBlockCount, playerQuest.getBreakBlockCount());

            try {
                config.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
