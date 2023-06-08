package decoration.decoration;

import decoration.decoration.helloworld.EventHandle;
import decoration.decoration.quest.PlayerQuest;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class Decoration  {


//    @Override
//    public void onEnable() {
//        getServer().getPluginManager().registerEvents(new EventHandle(this),this);
//
//        File file = new File(getDataFolder(), "test.yml");
//        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
//
//        File playerFile = new File(getDataFolder(), "playerData.yml");
//        YamlConfiguration config2 = YamlConfiguration.loadConfiguration(playerFile);
//
//        try {
//            config.save(file);
//            config2.save(playerFile);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void onDisable() {
//
//    }
}
