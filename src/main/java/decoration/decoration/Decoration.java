package decoration.decoration;

import decoration.decoration.file.FileApi;
import decoration.decoration.helloworld.EventHandle;
import decoration.decoration.quest.PlayerQuestUtil;
import org.bukkit.plugin.java.JavaPlugin;


public final class Decoration extends JavaPlugin {
    private FileApi fileApi;

    @Override
    public void onEnable() {
        fileApi = new FileApi(this);
        PlayerQuestUtil.setUp();
        getServer().getPluginManager().registerEvents(new EventHandle(this.fileApi), this);
    }

    @Override
    public void onDisable() {
        fileApi.saveConfig();
    }
}
