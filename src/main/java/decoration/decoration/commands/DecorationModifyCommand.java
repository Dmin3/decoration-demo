package decoration.decoration.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.List;

public class DecorationModifyCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        /**
         * 보유한 칭호로 변경
         */
        if (args.length == 0){
            return false;
        } else if (args.length == 1){
            String teamName = args[0];

            Player player = (Player) sender;

            Team team = player.getScoreboard().getTeam(teamName);
            team.addEntry(player.getDisplayName());
            player.sendMessage(teamName + " 으로 칭호 변경 완료!!");
            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            Player player = (Player) sender;
            return player.getScoreboard().getTeams().stream().map(team -> team.getName()).toList();
        }

        return null;
    }
}
