package decoration.decoration;

import decoration.decoration.helloworld.HelloWorld;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Decoration extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new HelloWorld(),this);
    }

    @Override
    public void onDisable() {

    }
}
