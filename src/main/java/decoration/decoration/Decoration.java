package decoration.decoration;

import decoration.decoration.file.FileApi;
import decoration.decoration.helloworld.EventHandle;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class Decoration extends JavaPlugin {
    private FileApi fileApi;

    @Override
    public void onEnable() {
        fileApi = new FileApi(this, Bukkit.getOfflinePlayers());

        getServer().getPluginManager().registerEvents(new EventHandle(), this);
    }

    @Override
    public void onDisable() {
        fileApi.saveConfig();
    }
}
