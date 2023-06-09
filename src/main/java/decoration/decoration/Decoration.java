package decoration.decoration;

import decoration.decoration.helloworld.EventHandle;
import decoration.decoration.quest.PlayerQuest;
import decoration.decoration.quest.PlayerQuestUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class Decoration extends JavaPlugin  {
    File file = new File(getDataFolder(), "playerData.yml");
    YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventHandle(this),this);

        for (OfflinePlayer offlinePlayer : getServer().getOfflinePlayers()) {
            String playerId = offlinePlayer.getPlayer().getUniqueId().toString();

            if (config.getConfigurationSection(playerId) == null){
                PlayerQuest playerQuest = new PlayerQuest(0, 0);
                PlayerQuestUtil.setPlayerQuestMap(playerId, playerQuest);
            } else {
                ConfigurationSection section = config.getConfigurationSection(playerId);
                int dieCount = section.getInt("dieCount");
                int breakBlockCount = section.getInt("breakBlockCount");

                PlayerQuest playerQuest = new PlayerQuest(dieCount, breakBlockCount);
                PlayerQuestUtil.setPlayerQuestMap(playerId, playerQuest);
            }
        }
    }

    @Override
    public void onDisable() {
        Map<String, PlayerQuest> playerQuestMap = PlayerQuestUtil.getPlayerQuestMap();

        for (String playerId : playerQuestMap.keySet()) {
            //   id:
            //    name:
            //    block: 어떻게 접근 할 것인지 알아보기
        }



    }
}
