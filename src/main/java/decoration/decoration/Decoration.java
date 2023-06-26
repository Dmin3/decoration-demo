package decoration.decoration;

import decoration.decoration.commands.DecorationListCommand;
import decoration.decoration.commands.DecorationModifyCommand;
import decoration.decoration.file.FileApi;
import decoration.decoration.helloworld.EventHandle;
import decoration.decoration.quest.Quest;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;


public final class Decoration extends JavaPlugin {
    private FileApi fileApi;

    @Override
    public void onEnable() {
        fileApi = new FileApi(this, Bukkit.getOfflinePlayers());

        getServer().getPluginManager().registerEvents(new EventHandle(), this);
        getCommand("decoration-list").setExecutor(new DecorationListCommand());
        getCommand("decoration-modify").setExecutor(new DecorationModifyCommand());
        setUpDecoration();
    }

    @Override
    public void onDisable() {
        fileApi.saveConfig();
    }

    private void setUpDecoration() {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

        for (Team team : scoreboard.getTeams()) {
            team.unregister();
        }

        for (Quest quest : Quest.values()) {
            if (scoreboard.getTeam(quest.getTeamName()) == null) {
                Team team = scoreboard.registerNewTeam(quest.getTeamName());
                team.setPrefix(quest.getColor() + quest.getTeamName());
            }
        }
    }

}
