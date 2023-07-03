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
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Map;


public class EventHandle implements Listener {
    private static Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
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

    // 다이아킹 칭호
    @EventHandler
    public void diaBlockBreak(BlockBreakEvent e) {
        Material type = e.getBlock().getType();
        if (type == Material.DIAMOND_ORE || type == Material.DEEPSLATE_DIAMOND_ORE) {
            PlayerQuest playerQuest = playerQuestMap.get(e.getPlayer().getUniqueId().toString());
            playerQuest.addDiamondCount();
            e.getPlayer().sendMessage(playerQuest.getDiamondCount() + " 개 캐는중!");

            if (playerQuest.getDiamondCount() == Quest.DIA_KING.getRequirement()) {
                addDiamondTeam(e.getPlayer());
            }
        }
    }

    private void addDiamondTeam(Player player) {
        Team team = scoreboard.getTeam(Quest.DIA_KING.getTeamName());
        team.addEntry(player.getDisplayName());

        player.sendMessage(team.getPrefix() + " 칭호를 획득하셨습니다!");
    }

    @EventHandler
    public void ironBlockBreak(BlockBreakEvent e) {
        Material type = e.getBlock().getType();
        if (type == Material.IRON_ORE || type == Material.DEEPSLATE_IRON_ORE) {
            PlayerQuest playerQuest = playerQuestMap.get(e.getPlayer().getUniqueId().toString());
            playerQuest.addIronCount();

            e.getPlayer().sendMessage(playerQuest.getIronCount() + " 개 캐는중!");

            if (playerQuest.getIronCount() == Quest.IRON_MAN.getRequirement()) {
                addIronManTeam(e.getPlayer());
            }
        }
    }

    private void addIronManTeam(Player player) {
        Team team = scoreboard.getTeam(Quest.IRON_MAN.getTeamName());
        team.addEntry(player.getDisplayName());
        player.sendMessage(team.getPrefix() + " 칭호 획득하셨습니다!");
    }

    // 나무꾼 칭호
    @EventHandler
    public void woodBlockBreak(BlockBreakEvent e) {
        if (isWoodBlock(e.getBlock())) {
            PlayerQuest playerQuest = playerQuestMap.get(e.getPlayer().getUniqueId().toString());
            playerQuest.addWoodCount();

            e.getPlayer().sendMessage("나무를 " + playerQuest.getWoodCount() + "개째 캐는 중");

            if (playerQuest.getWoodCount() == Quest.WOOD_MAN.getRequirement()) {
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
        Team team = scoreboard.getTeam(Quest.WOOD_MAN.getTeamName());

        team.addEntry(player.getDisplayName());

        player.sendMessage(team.getPrefix() + " 칭호 획득하셨습니다!!");
    }

    // 좀비사냥꾼
    @EventHandler
    public void killZombie(EntityDeathEvent e) {
        EntityType entityType = e.getEntity().getType();
        Player player = e.getEntity().getKiller();

        if (entityType == EntityType.ZOMBIE && player != null) {
            PlayerQuest playerQuest = playerQuestMap.get(player.getUniqueId().toString());
            playerQuest.addZombieCount();

            player.sendMessage("좀비를 " + playerQuest.getZombieCount() + " 마리 잡으셨습니다.");

            if (playerQuest.getZombieCount() == Quest.ZOMBIE_HUNTER.getRequirement()) {
                addZombieHunterTeam(player);
            }
        }
    }

    private void addZombieHunterTeam(Player player) {
        Team team = scoreboard.getTeam(Quest.ZOMBIE_HUNTER.getTeamName());
        team.addEntry(player.getDisplayName());
        player.sendMessage(team.getPrefix() + " 칭호를 획득하셨습니다!");
    }

    // 디아블로
    @EventHandler
    public void killPlayer(EntityDeathEvent e) {
        EntityType entityType = e.getEntity().getType();
        Player killer = e.getEntity().getKiller();

        if (entityType == EntityType.PLAYER && killer != null) {
            PlayerQuest playerQuest = playerQuestMap.get(killer.getUniqueId().toString());
            playerQuest.addKillCount();

            if (playerQuest.getKillCount() == Quest.DIABLO.getRequirement()) {
                addDiabloTeam(killer);
            }
        }
    }

    private void addDiabloTeam(Player player) {
        Team team = scoreboard.getTeam(Quest.DIABLO.getTeamName());
        team.addEntry(player.getDisplayName());
        player.sendMessage(team.getPrefix() + " 칭호를 획득하셨습니다.!!");

    }

    // 바보
    @EventHandler
    public void selfKill(PlayerDeathEvent e) {
        Player player = e.getEntity();

        if (player != null) {
            PlayerQuest playerQuest = playerQuestMap.get(player.getUniqueId().toString());
            playerQuest.addDieCount();

            player.sendMessage(playerQuest.getDieCount() + "번 죽었습니다");

            if (playerQuest.getDieCount() == Quest.STUPID.getRequirement()) {
                addStupidTeam(player);
            }
        }
    }

    private void addStupidTeam(Player player) {
        Team team = scoreboard.getTeam(Quest.STUPID.getTeamName());
        team.addEntry(player.getDisplayName());
        player.sendMessage(team.getPrefix() + " 칭호를 획득하셨습니다.!!");
    }

    // 신궁
    @EventHandler
    public void killSkeletonArrow(EntityDamageByEntityEvent e) {
        // 데미지를 받은 엔티티
        Entity entity = e.getEntity();

        // 데미지를 준 엔티티
        Entity entity1 = e.getDamager();

        if (entity instanceof Skeleton && entity1 instanceof Arrow){
            Skeleton skeleton = (Skeleton) entity;
            Arrow arrow = (Arrow) entity1;

            // 스켈레톤 잡았다는 검증이 필요함 !!!
            if (arrow.getShooter() instanceof Player){
                Player arrowShooter = (Player) arrow.getShooter();

                PlayerQuest playerQuest = playerQuestMap.get(arrowShooter.getUniqueId().toString());
                playerQuest.addSkeletonCount();

                arrowShooter.sendMessage("스켈레톤을 " + playerQuest.getSkeletonCount() + "마리 잡았습니다!");

                if (playerQuest.getSkeletonCount() == Quest.GOT_ARROW.getRequirement()){
                    addGotArrowTeam(arrowShooter);
                }
            }

        }



    }

    private void addGotArrowTeam(Player player) {
        Team team = scoreboard.getTeam(Quest.GOT_ARROW.getTeamName());
        team.addEntry(player.getDisplayName());

        player.sendMessage(team.getPrefix() + " 칭호를 획득하셨습니다!!");

    }

}
