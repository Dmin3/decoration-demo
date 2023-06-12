package decoration.decoration;

import decoration.decoration.file.FileApi;
import decoration.decoration.helloworld.EventHandle;
import decoration.decoration.quest.PlayerQuestUtil;
import org.bukkit.plugin.java.JavaPlugin;


public final class Decoration extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventHandle(), this);
        FileApi.setUp(this);
        PlayerQuestUtil.setUp();
    }

    @Override
    public void onDisable() {
        FileApi.getFileApi().saveConfig();
    }
}
