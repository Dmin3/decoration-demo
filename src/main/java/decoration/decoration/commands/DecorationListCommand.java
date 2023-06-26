package decoration.decoration.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.List;


public class DecorationListCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            /**
             * 보유한 칭호 리스트
             */
            StringBuilder sb = new StringBuilder();

            List<Team> teams = player.getScoreboard().getTeams().stream().toList();

            for (int i = 0; i < teams.size(); i++) {
                sb.append(i).append(". ").append(teams.get(i).getName()).append("\n");
            }

            player.sendMessage(sb.toString());
        }


        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }


}
