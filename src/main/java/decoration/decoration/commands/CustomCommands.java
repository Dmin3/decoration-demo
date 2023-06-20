package decoration.decoration.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class CustomCommands implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            /**
             * 칭호 변경, 보유한 칭호 리스트
             */
            String cmd = getCmd(command.getName());

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
        String cmd = getCmd(command.getName());

        if (args.length == 1) {
            Player player = (Player) sender;
            return player.getScoreboard().getTeams().stream().map(team -> team.getName()).toList();
        }

        return null;
    }

    private String getCmd(String cmd) {
        return cmd.substring(1);
    }
}
