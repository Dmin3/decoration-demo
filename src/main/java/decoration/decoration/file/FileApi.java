package decoration.decoration.file;

import decoration.decoration.Decoration;
import decoration.decoration.quest.PlayerQuest;
import decoration.decoration.quest.PlayerQuestUtil;
import decoration.decoration.quest.Quest;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class FileApi {
    private File file;
    private YamlConfiguration config;

    public FileApi(Decoration main, OfflinePlayer[] offlinePlayers) {
        file = new File(main.getDataFolder(), "playerData.yml");
        config = YamlConfiguration.loadConfiguration(file);

        PlayerQuestUtil.setUp();
        setPlayerQuestMap(offlinePlayers);
    }

    private void setPlayerQuestMap(OfflinePlayer[] offlinePlayers) {
        for (OfflinePlayer offlinePlayer : offlinePlayers) {
            String playerId = offlinePlayer.getUniqueId().toString();

            if (config.contains(playerId)) {
                PlayerQuest playerQuest = new PlayerQuest(
                        config.getInt(playerId + Quest.DIA_KING.getConfigName()),
                        config.getInt(playerId + Quest.WOOD_MAN.getConfigName()),
                        config.getInt(playerId + Quest.DIABLO.getConfigName()),
                        config.getInt(playerId + Quest.ZOMBIE_HUNTER.getConfigName()),
                        config.getInt(playerId + Quest.BDT.getConfigName()),
                        config.getInt(playerId + Quest.GOT_ARROW.getConfigName()),
                        config.getInt(playerId + Quest.IRON_MAN.getConfigName()),
                        config.getInt(playerId + Quest.STUPID.getConfigName())
                );

                PlayerQuestUtil.setPlayerQuestMap(playerId, playerQuest);
            }

        }
    }

    public void saveConfig() {
        Map<String, PlayerQuest> playerQuestMap = PlayerQuestUtil.getPlayerQuestMap();

        for (String playerId : playerQuestMap.keySet()) {
            PlayerQuest playerQuest = playerQuestMap.get(playerId);

            config.set(playerId + Quest.DIA_KING.getConfigName(), playerQuest.getDiamondCount());
            config.set(playerId + Quest.WOOD_MAN.getConfigName(), playerQuest.getWoodCount());
            config.set(playerId + Quest.STUPID.getConfigName(), playerQuest.getDieCount());
            config.set(playerId + Quest.DIABLO.getConfigName(), playerQuest.getKillCount());
            config.set(playerId + Quest.BDT.getConfigName(), playerQuest.getCreeperCount());
            config.set(playerId + Quest.IRON_MAN.getConfigName(), playerQuest.getIronCount());
            config.set(playerId + Quest.ZOMBIE_HUNTER.getConfigName(), playerQuest.getZombieCount());
            config.set(playerId + Quest.GOT_ARROW.getConfigName(), playerQuest.getSkeletonCount());

            try {
                config.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
