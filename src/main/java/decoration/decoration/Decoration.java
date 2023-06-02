package decoration.decoration;

import decoration.decoration.helloworld.EventHandle;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Decoration extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventHandle(this),this);

        File file = new File(getDataFolder(), "test.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {

    }
}
