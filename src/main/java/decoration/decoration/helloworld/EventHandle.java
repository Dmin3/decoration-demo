package decoration.decoration.helloworld;


import decoration.decoration.file.FileApi;
import decoration.decoration.quest.PlayerQuest;
import decoration.decoration.quest.PlayerQuestUtil;
import decoration.decoration.quest.Quest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Map;


public class EventHandle implements Listener {
    private Map<String, PlayerQuest> playerQuestMap = PlayerQuestUtil.getPlayerQuestMap();

    @EventHandler
    public void joinPlayer(PlayerJoinEvent e) {
        String playerId = e.getPlayer().getUniqueId().toString();

        // config파일에도 없는 신규 유저임
        if (!playerQuestMap.containsKey(playerId)) {
            PlayerQuest playerQuest = new PlayerQuest();
            playerQuestMap.put(playerId, playerQuest);

        }

        sendTitle(e.getPlayer());
    }

    private void sendTitle(Player event) {
        Player player = event.getPlayer();
        player.sendTitle("Welcome 봉하시티", "By JINKI3", 10, 100, 20);
    }

    @EventHandler
    public void woodBlockBreak(BlockBreakEvent e) {
        if (isWoodBlock(e.getBlock())) {
            PlayerQuest playerQuest = playerQuestMap.get(e.getPlayer().getUniqueId().toString());
            playerQuest.addWoodCount();

            e.getPlayer().sendMessage("나무를 " + playerQuest.getWoodCount() + "개째 캐는 중");

            if (playerQuest.getWoodCount() == 10) {
                addWoodTeam(e.getPlayer());
            }
        }
    }

    private boolean isWoodBlock(Block block) {
        Material type = block.getType();
        return type == Material.OAK_LOG || type == Material.BIRCH_LOG || type == Material.SPRUCE_LOG
                || type == Material.JUNGLE_LOG || type == Material.ACACIA_LOG || type == Material.DARK_OAK_LOG;
    }

    private void addWoodTeam(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

        Team team = scoreboard.getTeam(Quest.WOOD_MAN.getTeamName());

        team.addEntry(player.getDisplayName());

        player.sendMessage(team.getPrefix() + " 칭호 획득하셨습니다!!");
    }
}
