package decoration.decoration.helloworld;


import decoration.decoration.Decoration;
import decoration.decoration.quest.PlayerQuest;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EventHandle implements Listener {
    private Decoration main;
    static Map<String, PlayerQuest> playerQuestMap = new HashMap<>();

    public EventHandle(Decoration main) {
        this.main = main;
    }

//    @EventHandler
//    public void loginPlayer(PlayerJoinEvent e) {
//        File file = new File(main.getDataFolder(), "playerData.yml");
//        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
//
//        String playerId = e.getPlayer().getUniqueId().toString();
//        if (config.getString(playerId) != null){
//            playerQuestMap.put(playerId, new PlayerQuest(config.getString(playerId)));
//            System.out.println("나는 안들어가고 여기있어!!");
//        } else {
//            config.set(playerId, e.getPlayer().getDisplayName());
//            System.out.println("난 새로운 유저야!!");
//        }
//
//        try {
//            config.save(file);
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//
//
//    @EventHandler
//    public void sendTitle(PlayerJoinEvent event) {
//        Player player = event.getPlayer();
//        player.sendTitle("Welcome 봉하시티", "By JINKI3", 10,100,20);
//    }
//
//    @EventHandler
//    public void touchBlock(BlockBreakEvent e)   {
//        File file = new File(main.getDataFolder(), "test2.yml");
//        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
//        Player player = e.getPlayer();
//
//        config.set(player.getUniqueId().toString(), player.getDisplayName());
//
//        try {
//            config.save(file);
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }
//        player.sendMessage("블록 파괴 하고 config파일에 저장");
//
//        player.sendMessage(config.getString(player.getUniqueId().toString()) + "키가 정말 잘 가져와지는군!");
//
//    }
}
