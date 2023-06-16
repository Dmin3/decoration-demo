package decoration.decoration.helloworld;


import decoration.decoration.file.FileApi;
import decoration.decoration.quest.PlayerQuest;
import decoration.decoration.quest.PlayerQuestUtil;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Map;
import java.util.Set;


public class EventHandle implements Listener {
    private FileApi fileApi;
    private Map<String, PlayerQuest> playerQuestMap = PlayerQuestUtil.getPlayerQuestMap();

    public EventHandle(FileApi fileApi) {
        this.fileApi = fileApi;
    }

    @EventHandler
    public void joinPlayer(PlayerJoinEvent e) {
        String playerId = e.getPlayer().getUniqueId().toString();

        if (!playerQuestMap.containsKey(playerId)) {
            ConfigurationSection section = fileApi.getConfig().getConfigurationSection(playerId);

            if (section == null) {
                PlayerQuest playerQuest = new PlayerQuest(0, 0, 0);
                PlayerQuestUtil.setPlayerQuestMap(playerId, playerQuest);
            } else {
                int dieCount = section.getInt("dieCount");
                int breakBlockCount = section.getInt("breakBlockCount");
                int woodCount = section.getInt("woodCount");

                PlayerQuest playerQuest = new PlayerQuest(dieCount, breakBlockCount, woodCount);
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

        PlayerQuest playerQuest = playerQuestMap.get(playerId);
        playerQuest.addBlockBreakCount();

        e.getPlayer().sendMessage(e.getPlayer().getDisplayName() + "가" + playerQuest.getBreakBlockCount() + " 번 째 블럭 부시는중입니다");
    }

    @EventHandler
    public void woodBlockBreak(BlockBreakEvent e) {
        if (isWoodBlock(e.getBlock())) {
            PlayerQuest playerQuest = playerQuestMap.get(e.getPlayer().getUniqueId().toString());
            playerQuest.addWoodBlockCount();

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


    public void addWoodTeam(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

        Team team;
        if (scoreboard.getTeam("wood") == null) {
            team = scoreboard.registerNewTeam("wood");
        } else {
            team = scoreboard.getTeam("wood");
        }

        team.setPrefix(ChatColor.GRAY + "[나무꾼]");
        team.addEntry(player.getDisplayName());

        player.sendMessage("[나무꾼] 칭호 획득!!");
    }


}
