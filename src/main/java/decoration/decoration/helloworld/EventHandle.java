package decoration.decoration.helloworld;


import decoration.decoration.file.FileApi;
import decoration.decoration.quest.PlayerQuest;
import decoration.decoration.quest.PlayerQuestUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Map;


public class EventHandle implements Listener {
    FileApi fileApi = FileApi.getFileApi();

    @EventHandler
    public void joinPlayer(PlayerJoinEvent e) {
        String playerId = e.getPlayer().getUniqueId().toString();

        Map<String, PlayerQuest> playerQuestMap = PlayerQuestUtil.getPlayerQuestMap();

        if (!playerQuestMap.containsKey(playerId)) {
            ConfigurationSection section = fileApi.getConfig().getConfigurationSection(playerId);

            if (section == null) {
                PlayerQuest playerQuest = new PlayerQuest(0, 0);
                PlayerQuestUtil.setPlayerQuestMap(playerId, playerQuest);
            } else {
                int dieCount = section.getInt("dieCount");
                int breakBlockCount = section.getInt("breakBlockCount");

                PlayerQuest playerQuest = new PlayerQuest(dieCount, breakBlockCount);
                PlayerQuestUtil.setPlayerQuestMap(playerId, playerQuest);
            }
        }

        sendTitle(e.getPlayer());
    }

    private void sendTitle(Player event) {
        Player player = event.getPlayer();
        player.sendTitle("Welcome 봉하시티", "By JINKI3", 10, 100, 20);
    }

    @EventHandler
    public void touchBlock(BlockBreakEvent e) {
        String playerId = e.getPlayer().getUniqueId().toString();

        Map<String, PlayerQuest> playerQuestMap = PlayerQuestUtil.getPlayerQuestMap();
        PlayerQuest playerQuest = playerQuestMap.get(playerId);
        playerQuest.addBlockBreakCount(1);

        e.getPlayer().sendMessage(e.getPlayer().getDisplayName() + "가" + playerQuest.getBreakBlockCount() + " 번 째 블럭 부시는중입니다");
    }
}
