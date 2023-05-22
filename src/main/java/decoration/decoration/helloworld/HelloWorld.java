package decoration.decoration.helloworld;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class HelloWorld implements Listener {
    @EventHandler
    public void sendTitle(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendTitle("Welcome 봉하시티", "By JINKI3", 10,100,20);
    }
}
