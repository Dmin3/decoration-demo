package decoration.decoration.quest;

import org.bukkit.ChatColor;
import org.bukkit.Color;

/**
 * 칭호 종류 - 요구사항
 * 다이아킹 - 다이아 20개
 * 아이언맨 - 철 64개
 * 나무꾼 - 나무 100개
 * 좀비사냥꾼 - 좀비 30마리
 * 디아블로 - 플레이어 5번 죽이기
 * 좆병신 - 3번 죽기
 * 신궁 - 스켈레톤 30마리
 * 폭탄처리반 - 크리퍼 30마리
 */
public enum Quest {
    DIA_KING("다이아킹", ".diaCount", 20, ChatColor.AQUA),
    IRON_MAN("아이어맨", ".ironCount", 64, ChatColor.WHITE),
    WOOD_MAN("나무꾼", ".woodCount", 100, ChatColor.DARK_GRAY),
    ZOMBIE_HUNTER("좀비사냥꾼", ".killCount.zombie", 30, ChatColor.GREEN),
    DIABLO("디아블로", ".killCount.player", 5, ChatColor.RED),
    STUPID("바보", ".killCount.die", 3, ChatColor.GRAY),
    GOT_ARROW("신궁", ".killCount.skeleton", 30, ChatColor.DARK_BLUE),
    BDT("폭탄처리반", ".killCount.creeper", 30, ChatColor.DARK_PURPLE),
    ;

    private final String teamName;
    private final String configName;
    private final int requirement;

    private final ChatColor color;

    Quest(String teamName, String configName, int requirement, ChatColor color) {
        this.teamName = teamName;
        this.configName = configName;
        this.requirement = requirement;
        this.color = color;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getRequirement() {
        return requirement;
    }

    public ChatColor getColor() {
        return color;
    }

    public String getConfigName() {
        return configName;
    }
}
